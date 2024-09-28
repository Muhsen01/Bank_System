package com.example.npcbank.controllers;

import com.example.npcbank.models.Account;
import com.example.npcbank.models.SharedDataModel;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class DetailsPageController
{

    @FXML
    private ComboBox<String> accountComboBox;
    @FXML
    private Label accountNameLabel;
    @FXML
    private Label accountNumberLabel;
    @FXML
    private Label accountBalanceLabel;
    @FXML
    TextField tfFilter;
    @FXML
    TextArea taTop5;
    @FXML
    TextArea taBot5;
    @FXML
    Label lblAverage;

    private ObservableList<String> accountNumbers = FXCollections.observableArrayList();
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();

    @FXML
    private void initialize()
    {
        for (Object obj : SharedDataModel.getInstance().getData()) {
            Account account = (Account) obj;
            accountNumbers.add(account.getNumber());
        }
        Collections.sort(accountNumbers);
        accountComboBox.setItems(accountNumbers);
        taTop5.setText(getTop5Bal());
        taBot5.setText(getBot5Bal());
        lblAverage.setText(currencyFormatter.format(getAverage()));
    }

    private double getAverage()
    {
        double total = 0.0;
        int count = SharedDataModel.getInstance().getData().size();
        for (Object obj : SharedDataModel.getInstance().getData()) {
            Account account = (Account) obj;
            total += account.getBalance();
        }
        return total/count;
    }

    @FXML
    private void handleAccountSelection()
    {
        // Get the selected account
        String accountNum = accountComboBox.getSelectionModel().getSelectedItem();

        // Find the selected account and display its details
        for (Object obj : SharedDataModel.getInstance().getData()) {
            Account account = (Account) obj;
            if (account.getNumber().equals(accountNum)) {
                accountNameLabel.setText(account.getName());
                accountNumberLabel.setText(account.getNumber());
                accountBalanceLabel.setText(currencyFormatter.format(account.getBalance()));
            }
        }
    }

    public void updateFilter(KeyEvent keyEvent)
    {
        String filter = tfFilter.getText();
        accountComboBox.getItems().clear();
        accountNumbers.clear();
        for (Object obj : SharedDataModel.getInstance().getData()) {
            Account account = (Account) obj;
            if (account.getNumber().startsWith(filter)) accountNumbers.add(account.getNumber());
        }
        Collections.sort(accountNumbers);
        accountComboBox.setItems(accountNumbers);
        accountComboBox.setPromptText("... FILTERED ...");
    }

    private String getTop5Bal()
    {
        Object ArrayList;
        ArrayList<Account> accounts = new ArrayList<>();
        String result = "";
        for (Object obj : SharedDataModel.getInstance().getData()) {
            Account account = (Account) obj;
            accounts.add(account);
        }
        Collections.sort(accounts, Collections.reverseOrder(Account.CompareAccountBal));
        for (int i = 0; i < 5; i++) {
            result += accounts.get(i).getName() + ": \t";
            result += currencyFormatter.format(accounts.get(i).getBalance()) + "\n";
        }
        return result;
    }

    private String getBot5Bal()
    {
        //Object ArrayList;
        ArrayList<Account> accounts = new ArrayList<>();
        String result = "";
        for (Object obj : SharedDataModel.getInstance().getData()) {
            Account account = (Account) obj;
            accounts.add(account);
        }
        Collections.sort(accounts, Account.CompareAccountBal);
        for (int i = 0; i < 5; i++) {
            result += accounts.get(i).getName() + ": \t";
            result += currencyFormatter.format(accounts.get(i).getBalance()) + "\n";
        }
        return result;
    }
}
