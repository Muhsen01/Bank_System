package com.example.npcbank.models;

import java.util.ArrayList;
import java.util.Objects;

public class SharedDataModel
{
    private static SharedDataModel instance = new SharedDataModel();

    private ArrayList<Object> data = new ArrayList<Object>();

    private ArrayList<Object> transactions = new ArrayList<>();

    public ArrayList<Object> getTransactions()
    {
        return transactions;
    }

    public void setTransactions(ArrayList<Object> transactions)
    {
        this.transactions = transactions;
    }

    private boolean  isThreadCompleted;

    public int getPercentCompleted()
    {
        return percentCompleted;
    }

    public void setPercentCompleted(int percentCompleted)
    {
        this.percentCompleted = percentCompleted;
    }

    public boolean isThreadCompleted()
    {
        return isThreadCompleted;
    }

    public void setThreadCompleted(boolean threadCompleted)
    {
        isThreadCompleted = threadCompleted;
    }

    private int percentCompleted;

    private SharedDataModel() {

    }

    public static SharedDataModel getInstance()
    {
        return instance;
    }

    public ArrayList<Object> getData()
    {
        return data;
    }

    public void setData(ArrayList<Object> data)
    {
        this.data = data;
    }
}