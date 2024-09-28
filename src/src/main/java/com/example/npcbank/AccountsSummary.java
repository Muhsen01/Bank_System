package com.example.npcbank;

import com.example.npcbank.models.Account;
import com.example.npcbank.models.SharedDataModel;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class AccountsSummary extends StackPane
{
    private double accountsTotal;
    private int count;
    Label label;


    public AccountsSummary()
    {
        label = new Label();
        count = SharedDataModel.getInstance().getData().size();
        accountsTotal = getAccountsTotal();
        label.setText("Accounts: " + count + " Total: $" + accountsTotal);
        getChildren().add(label);
    }

    private double getAccountsTotal()
    {
        double total = 0;
        for(Object obj : SharedDataModel.getInstance().getData())
        {
            Account account = (Account) obj;
            total += account.getBalance();
        }
        return total;
    }

    public void update()
    {
        count = SharedDataModel.getInstance().getData().size();
        accountsTotal = getAccountsTotal();
        label.setText("Accounts: " + count + " Total: $" + accountsTotal);
    }
}
