package com.example.npcbank.models;

import java.util.Comparator;

public abstract class Account
{
    private String name;
    private String number;
    private String email;
    protected double balance;


    public Account(String name, String number, String email, double balance)
    {
        this.name = name;
        this.number = number;
        this.email = email;
        this.balance = balance;
    }

    public static Comparator<Account> CompareAccountBal = Comparator.comparing(Account::getBalance);

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }


    abstract void  deposit(double amount);

    abstract void withdraw(double amount);

    @Override
    public String toString()
    {
        return "Account{" + "\nname='" + name + '\'' + ", \nnumber='" + number + '\'' + ", \nemail='" + email + '\'' + ", \nbalance=" + balance + '}';
    }
}

