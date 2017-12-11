package com.nullpointerxception.accounts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.io.File;
import java.util.Map.Entry;
import java.util.Random;

import com.nullpointerxception.accounts.*;

public class AccountsManager{

    private static AccountsManager accountsManager;
    private static HashMap<Integer,Account> accounts = null;
    private static int accountNum;


    @SuppressWarnings("unchecked")
    private AccountsManager(){

        File f = new File("accountsDatabase");
        if(f.exists()) { 
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("accountsDatabase"))){
                accounts = (HashMap<Integer,Account>) ois.readObject();
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("accountsDatabase"))){
                accounts = new HashMap<Integer,Account>();
                oos.writeObject(accounts);
            }catch(Exception e){
            e.printStackTrace();
            }
        }

    }

    public static AccountsManager getInstance(){
        if(accountsManager == null){
            accountsManager = new AccountsManager();
            return accountsManager;
        }else{
            return accountsManager;
        }
    }

    public boolean logIn(int accountNum){
        if(accounts.containsKey(accountNum)){
            this.accountNum = accountNum;
            return true;
        }else{
            return false;
        }
    }


    public static Account createAccount(AccountsEnum typeOfAccount){
        Account newAccount;
        int newAccountNum;
        switch(typeOfAccount){

            case CHECKINGS:
                newAccountNum = generateAccountNum();
                newAccount = new Checkings(newAccountNum);
                break;
            case SAVINGS:
                newAccountNum = generateAccountNum();
                newAccount = new Savings(newAccountNum);
                break;
            default:
            return null;
        }

        accounts.put(newAccountNum, newAccount);
        commitAccounts();
        accountNum = newAccountNum;

        return newAccount;
    }

    public static Account createAccount(AccountsEnum typeOfAccount, int amount){
        Account newAccount;
        int newAccountNum;
        switch(typeOfAccount){

            case CHECKINGS:
                newAccountNum = generateAccountNum();
                newAccount = new Checkings(newAccountNum, amount);
                break;
            case SAVINGS:
                newAccountNum = generateAccountNum();
                newAccount = new Savings(newAccountNum, amount);
                break;
            default:
            return null;
        }

        accounts.put(newAccountNum, newAccount);
        commitAccounts();
        accountNum = newAccountNum;

        return newAccount;
    }

    public int getAccountNum(){
        return accountNum;
    }

    public Account getAccount(){
        return accounts.get(accountNum);
    }

    public boolean deposit(int amount){
        Account account = accounts.get(accountNum);
        if(account.deposit(amount)){
            accounts.put(accountNum, account);
            commitAccounts();
            return true;
        }else{
            return false;
        }
    }

    public boolean withdraw(int amount){
        Account account = accounts.get(accountNum);
        if(account.withdraw(amount)){
            accounts.put(accountNum, account);
            commitAccounts();
            return true;
        }else{
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    private static void commitAccounts(){
       try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("accountsDatabase"))){
            oos.writeObject(accounts);
       }catch(Exception e){
        e.printStackTrace();
       }
    }

    private static int generateAccountNum(){
        Random generator = new Random();
        int i = generator.nextInt();
        i = Math.abs(i);
        while(accounts.containsKey(i)){
            i = generator.nextInt();
            i  = Math.abs(i);

        }
        return i;
    }

    



}