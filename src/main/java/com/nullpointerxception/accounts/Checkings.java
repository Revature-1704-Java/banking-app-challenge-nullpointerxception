package com.nullpointerxception.accounts;



public class Checkings extends Account{

    public Checkings(int accountNum){
        super(accountNum);
    }

    public Checkings(int accountNum, int amount){
        super(accountNum, amount);
    }

    @Override
    public boolean deposit(int amount){
        if(amount < 0){
            return false;
        }else{
            setBalance(getBalance() + amount);
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