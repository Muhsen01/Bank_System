package com.example.npcbank.models;

import com.example.npcbank.models.Account;

public class Checking extends Account
{
    String overDraftAccount;
    int overDraftLimit;
    String owningBranch;

    public Checking(String name, String number, String email, double balance, String overDraftAccount, int overDraftLimit, String owningBranch)
    {
        super(name, number, email, balance);
        this.overDraftAccount = overDraftAccount;
        this.overDraftLimit = overDraftLimit;
        this.owningBranch = owningBranch;
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

    //region *** Getters/Setters
    public String getOverDraftAccount()
    {
        return overDraftAccount;
    }

    public void setOverDraftAccount(String overDraftAccount)
    {
        this.overDraftAccount = overDraftAccount;
    }

    public int getOverDraftLimit()
    {
        return overDraftLimit;
    }

    public void setOverDraftLimit(int overDraftLimit)
    {
        this.overDraftLimit = overDraftLimit;
    }

    public String getOwningBranch()
    {
        return owningBranch;
    }

    public void setOwningBranch(String owningBranch)
    {
        this.owningBranch = owningBranch;
    }
    //endregion


    @Override
    public String toString()
    {
        return getName() + ": " + getNumber() + ": " + getEmail() + ": " + getBalance();
        //return "\nChecking{" + "\noverDraftAccount='" + overDraftAccount + '\'' + ", \noverDraftLimit=" + overDraftLimit + ", \nowningBranch='" + owningBranch + '\'' + '}';
    }

    public String getCSV(){
        String result="";
        result += getName() + ',';
        result += getNumber() + ',';
        result += getEmail() + ',';

        Double bal = getBalance();
        result += bal.toString() + ',';

        result += getOverDraftAccount() + ',';
        result += getOverDraftLimit() + ',';
        result += getOwningBranch();
        return result;
    }
}
