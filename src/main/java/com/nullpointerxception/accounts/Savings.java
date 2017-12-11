package com.nullpointerxception.accounts;

public class Savings extends Account{

    public Savings(int accountNum){
        super(accountNum);
    }

    public Savings(int accountNum, int amount){
        super(accountNum, amount);
    }

    @Override
    public boolean deposit(int amount){
        if(amount < 0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean withdraw(int amount){
        if((getBalance() - amount) < 0){
            return false;
        }else{
            setBalance(getBalance() - amount);
            return true;
        }
    }


}
