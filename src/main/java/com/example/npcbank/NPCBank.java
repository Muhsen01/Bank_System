package com.example.npcbank;

import com.example.npcbank.models.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NPCBank extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NPCBank.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("NPC Bank");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        loadAccounts("BankData.csv");
        loadTransactions("Transactions.csv");
        applyTransactions();
        launch();

    }


    private static void loadAccounts(String filePath)
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Checking account = new Checking(data[0], data[1], data[2], Double.parseDouble(data[3]), data[4], Integer.parseInt(data[5]), data[6]);
                SharedDataModel.getInstance().getData().add(account);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadTransactions(String filePath)
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                //File data:
                // TransactionType, amount, date, sourceAccount, destinationAccount
                Transaction transaction = new Transaction(TransactionType.valueOf(data[0]), Double.parseDouble(data[1]),
                        LocalDate.parse(data[2], formatter), data[3], data[4]);

                SharedDataModel.getInstance().getTransactions().add(transaction);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static void applyTransactions()
    {
        //apply all transaction to the account
        for (Object objT : SharedDataModel.getInstance().getTransactions()) {
            boolean transactionComplete = false;
            Transaction transaction = (Transaction) objT;

            switch (transaction.getType()) {
                case DEPOSIT:
                    //find the target account in the accounts list, add the amount to its balance
                    for (Object objA : SharedDataModel.getInstance().getData()) {
                        Account account = (Account) objA;
                        String x = account.getNumber();
                        if (account.getNumber().equals(transaction.getDestinationAccountNumber())) {
                            account.setBalance(account.getBalance() + transaction.getAmount());
                            break;
                        }
                    }
                    break;
                case WITHDRAWAL:
                    //find the target account in the accounts list, subtract the amount from its balance
                    for (Object objA : SharedDataModel.getInstance().getData()) {
                        Account account = (Account) objA;
                        if (account.getNumber().equals(transaction.getSourceAccountNumber())) {
                            account.setBalance(account.getBalance() - transaction.getAmount());
                            break;
                        }
                    }
                    break;
                case TRANSFER:
                    //find the target account in the accounts list, subtract the amount from its balance
                    boolean withdrawalPart = false;
                    boolean depositPart = false;
                    for (Object obj : SharedDataModel.getInstance().getData()) {
                        Account account = (Account) obj;
                        if (account.getNumber().equals(transaction.getSourceAccountNumber())) {
                            account.setBalance(account.getBalance() - transaction.getAmount());
                            withdrawalPart = true;
                            if(depositPart)
                                break;
                        }
                        if (account.getNumber().equals(transaction.getDestinationAccountNumber())) {
                            account.setBalance(account.getBalance() + transaction.getAmount());
                            depositPart = true;
                            if (withdrawalPart)
                                break;
                        }
                    }
            }
        }
    }

}
