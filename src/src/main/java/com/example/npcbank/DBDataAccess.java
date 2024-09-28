package com.example.npcbank;

import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import com.example.npcbank.models.Checking;
import com.example.npcbank.models.SharedDataModel;
import javafx.application.Platform;


public class DBDataAccess
{
    public static volatile boolean threadCompleted = false;

    String jdbcURL = "jdbc:mysql://localhost:/testdb";
    String username = "root";
    String password = ""; //

    public void persist(ArrayList<Object> accounts) throws SQLException
    {

        if (accounts.get(0) instanceof Checking) {
            ArrayList<Checking> checkingAccounts = new ArrayList<Checking>();
            //convert 'objects' to 'checking'
            for (Object obj : accounts) {
                if (obj instanceof Checking) {
                    checkingAccounts.add((Checking) obj);
                }
            }

            String insertSQL = "INSERT INTO accounts (name, number, email, balance) VALUES (?, ?, ?, ?)";

            try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

                Checking account = null;

//              for (Checking account : checkingAccounts) {
                for (Object obj : accounts)
                {
                    if (obj instanceof Checking)
                    {
                         account = (Checking) obj;
                    }

                    preparedStatement.setString(1, account.getName());
                    preparedStatement.setString(2, account.getNumber());
                    preparedStatement.setString(3, account.getEmail());
                    preparedStatement.setDouble(4, account.getBalance());

                    preparedStatement.executeUpdate();
                } //end of for loop
            } catch (SQLException e)
            {
                throw new RuntimeException(e);
            }

            for(int count = 0; count < 100; count++)
            {
                try {
                    Thread.sleep(10);
                }
                catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
                SharedDataModel.getInstance().setPercentCompleted(count);
            }
            SharedDataModel.getInstance().setThreadCompleted(true);
        }

    } //end of method

} //end of class