package com.nullpointerxception.accounts;

import java.io.Serializable;

public abstract class Account implements Serializable{

    private int accountNum;
    private int balance;


    public Account(int accountNum){
        this.accountNum = accountNum;
    }

    public Account(int accountNum, int balance){
        this.accountNum = accountNum;
        this.balance = balance;
    }


    public int getAccountNum(){
        return accountNum;
    }

    public int getBalance(){
        return balance;
    }

    public void setBalance(int balance){
        this.balance = balance;
    }


    public abstract boolean deposit(int amount);

    public abstract boolean withdraw(int amount);

}