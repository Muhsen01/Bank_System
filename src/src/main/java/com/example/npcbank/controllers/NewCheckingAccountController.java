package com.example.npcbank.controllers;

import com.example.npcbank.NPCBank;
import com.example.npcbank.models.Checking;
import com.example.npcbank.models.SharedDataModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NewCheckingAccountController
{
    ArrayList<Object> accounts = new ArrayList<Object>();
    @FXML private TextField tfOwnerName;
    @FXML private TextField tfBalance;
    @FXML private TextField tfAccount;
    @FXML private TextField tfEmail;
    @FXML private Label lblStatus;
    @FXML private ImageView ivAccount;
    @FXML private TextField tfFileName;



    @FXML
    protected void onSaveDataClick()
    {
        double balance = 0;

        try {
            //validate account number
            String accountNumber = tfAccount.getText();
            String regXAccount = "^C\\d{9}$";
            Pattern patternAccount = Pattern.compile(regXAccount);
            Matcher matcherAccount = patternAccount.matcher(accountNumber);
            if (!matcherAccount.matches()) {
                throw new InvalidAccountNumberException("Acccount Number is Invalid");
            }
            else {
                //does not work
//                Class<?> clazz = NewCheckingAccountController.class;
//                ClassLoader classLoader = clazz.getClassLoader();
//                Image image = new Image(classLoader.getResourceAsStream("checkmark.png"));
//                ivAccount.setImage(image);
            }
            //validate email
            String email = tfEmail.getText();
            String regXEmail = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern patternEmail = Pattern.compile(regXEmail);
            Matcher matcherEmail = patternEmail.matcher(email);
            if (!matcherEmail.matches()) {
                throw new InvalidEmailException("Email is Invalid");
            }

            //validate balance
            balance = Double.parseDouble(tfBalance.getText());
            if (balance < 0) throw new NegativeBalanceException("Balance can't be Negative");

            Checking checking = new Checking(tfOwnerName.getText(), "12345", "rfi@gmail.com", balance, "100ABC", 100, "Central");
            //accounts.add(checking);
            SharedDataModel.getInstance().getData().add(checking);

        } catch (InvalidEmailException e) {
            lblStatus.setText(e.getMessage());
            tfEmail.getStyleClass().add("red-border");
        } catch (NegativeBalanceException e) {
            lblStatus.setText(e.getMessage());
            tfBalance.getStyleClass().add("red-border");
        } catch (InvalidAccountNumberException e) {
            lblStatus.setText(e.getMessage());
            tfAccount.getStyleClass().add("red-border");
        } catch (NumberFormatException e) {
            lblStatus.setText("Balance must be a number");
            tfBalance.getStyleClass().add("red-border");
       }
    }


    public void onBalanceClicked(MouseEvent mouseEvent)
    {
        tfBalance.getStyleClass().clear();
        tfBalance.getStyleClass().addAll("text-field", "text-input");
        lblStatus.setText("");
    }

    public void onAccountClicked(MouseEvent mouseEvent)
    {
        tfAccount.getStyleClass().clear();
        tfAccount.getStyleClass().addAll("text-field", "text-input");
        lblStatus.setText("");
    }

    public void onEmailClicked(MouseEvent mouseEvent)
    {
        tfEmail.getStyleClass().clear();
        tfEmail.getStyleClass().addAll("text-field", "text-input");
        lblStatus.setText("");
    }

    public void onArchiveDataClick(ActionEvent actionEvent)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(NPCBank.class.getResource("ArchivePage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 600);
            Stage stage = new Stage();
            stage.setTitle("Archive Data");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

class NegativeBalanceException extends Exception
{
    public NegativeBalanceException(String message)
    {
        super(message);
    }
}
class InvalidAccountNumberException extends Exception
{
    public InvalidAccountNumberException(String message)
    {
        super(message);
    }
}
class InvalidEmailException extends Exception
{
    public InvalidEmailException(String message)
    {
        super(message);
    }
}
