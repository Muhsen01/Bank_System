package com.example.npcbank.models;

import java.time.LocalDate;

public class Transaction
{
    private TransactionType type;
    private double amount;
    private LocalDate date;
    private String sourceAccountNumber;
    private String destinationAccountNumber;

    public Transaction(TransactionType type, double amount, LocalDate date, String sourceAccountNumber, String destinationAccountNumber)
    {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.sourceAccountNumber = sourceAccountNumber;
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public TransactionType getType()
    {
        return type;
    }

    public void setType(TransactionType type)
    {
        this.type = type;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public String getSourceAccountNumber()
    {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber)
    {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public String getDestinationAccountNumber()
    {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber)
    {
        this.destinationAccountNumber = destinationAccountNumber;
    }
}
