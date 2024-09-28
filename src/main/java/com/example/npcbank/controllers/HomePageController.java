package com.example.npcbank.controllers;

import com.example.npcbank.AccountsSummary;
import com.example.npcbank.NPCBank;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomePageController
{
    @FXML
    private AccountsSummary summaryDisplay;

    @FXML
    protected void onNewCheckingAccountButtonClick()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(NPCBank.class.getResource("NewCheckingAccount.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 300);
            scene.getStylesheets().add(NPCBank.class.getResource("styles.css").toExternalForm());

            Stage stage = new Stage();
            stage.setTitle("New Checking Account");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onNewSavingsAccountButtonClick()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(NPCBank.class.getResource("NewSavingsAccount.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            Stage stage = new Stage();
            stage.setTitle("New Savings Account");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onNewTransactionButtonClick()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(NPCBank.class.getResource("TransactionsPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 350, 300);
            Stage stage = new Stage();
            stage.setTitle("New Transaction");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDisplay(MouseEvent mouseEvent)
    {
        summaryDisplay.update();
    }

    public void onDetailsPage(ActionEvent actionEvent)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(NPCBank.class.getResource("DetailsPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 820, 840);
            Stage stage = new Stage();
            stage.setTitle("Account Details");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}