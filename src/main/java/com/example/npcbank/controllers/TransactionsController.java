package com.example.npcbank.controllers;

import com.example.npcbank.models.Account;
import com.example.npcbank.models.SharedDataModel;
import com.example.npcbank.models.Transaction;
import com.example.npcbank.models.TransactionType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.time.LocalDate;

public class TransactionsController
{
    @FXML private ComboBox transactionComboBox;

    @FXML private DatePicker datePicker;

    @FXML TextField amountField;

    @FXML private TextField sourceAccountField;

    @FXML private TextField destinationAccountField;


    public void submitTransaction(ActionEvent actionEvent)
    {
        TransactionType transactionType = TransactionType.valueOf(transactionComboBox.getValue().toString());
        LocalDate date = datePicker.getValue();
        double amount = Double.parseDouble(amountField.getText());
        String sourceAccount = sourceAccountField.getText();
        String destinationAccount = destinationAccountField.getText();

        switch (transactionType)
        {
            case DEPOSIT:
                for (Object obj : SharedDataModel.getInstance().getData())
                {
                    Account account = (Account) obj;
                    if (account.getNumber() == destinationAccount)
                    {
                        double currentBalance = account.getBalance();
                        account.setBalance(currentBalance + amount);
                    }
                }
            case WITHDRAWAL:
                for (Object obj : SharedDataModel.getInstance().getData())
                {
                    Account account = (Account) obj;
                    if (account.getNumber() == sourceAccount)
                    {
                        double currentBalance = account.getBalance();
                        account.setBalance(currentBalance - amount);
                    }
                }
            case TRANSFER:
                for (Object obj : SharedDataModel.getInstance().getData())
                {
                    Account account = (Account) obj;
                    if (account.getNumber() == sourceAccount)
                    {
                        double currentBalance = account.getBalance();
                        account.setBalance(currentBalance - amount);
                    }
                }

                for (Object obj : SharedDataModel.getInstance().getData())
                {
                    Account account = (Account) obj;
                    if (account.getNumber() == destinationAccount)
                    {
                        double currentBalance = account.getBalance();
                        account.setBalance(currentBalance + amount);
                    }
                }
        }
        SharedDataModel.getInstance().getTransactions().add(new Transaction(transactionType, amount, date, sourceAccount, destinationAccount));
    }
    public void configureButtons(ActionEvent actionEvent)
    {
        if (transactionComboBox.getValue().equals("DEPOSIT"))
        {
            sourceAccountField.setDisable(true);
            destinationAccountField.setDisable(false);
        }
        else if (transactionComboBox.getValue().equals("WITHDRAWAL"))
        {
            sourceAccountField.setDisable(false);
            destinationAccountField.setDisable(true);
        }
        else
        {
            sourceAccountField.setDisable(false);
            destinationAccountField.setDisable(false);
        }
    }
}