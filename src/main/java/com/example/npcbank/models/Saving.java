package com.example.npcbank.models;

import com.example.npcbank.models.Account;

public class Saving extends Account
{

    public float interestRate;
    public float transferLimit;


    public Saving(String name, String number, String email, double balance, float interestRate, float transferLimit)
    {
        super(name, number, email, balance);
        this.interestRate = interestRate;
        this.transferLimit = transferLimit;
    }

    public float getInterestRate()
    {
        return interestRate;
    }

    public void setInterestRate(float interestRate)
    {
        this.interestRate = interestRate;
    }

    public float getTransferLimit()
    {
        return transferLimit;
    }

    public void setTransferLimit(float transferLimit)
    {
        this.transferLimit = transferLimit;
    }


    @Override
    void deposit(double amount)
    {
        this.balance += amount;
    }

    @Override
    void withdraw(double amount)
    {
        this.balance -= amount;
    }


    @Override
    public String toString()
    {
        return super.toString() + "Saving{" + "interestRate=" + interestRate + ", transferLimit=" + transferLimit + ", balance=" + balance + '}';
    }
}
