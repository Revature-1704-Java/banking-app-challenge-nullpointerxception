package com.nullpointerxception;

import java.awt.ScrollPaneAdjustable;
import java.util.Scanner;

import com.nullpointerxception.accounts.*;


public class App 
{
    static Scanner scanner;
    static AccountsManager accountsManager;
    public static void main( String[] args )
    {
        accountsManager = AccountsManager.getInstance();
        scanner = new Scanner(System.in);
        
        if(enterId()){
            viewAccount();
        }else{
            accountCreation();
            viewAccount();
        }

        scanner.close();
        
        
            
        
    }

    public static boolean enterId(){
        System.out.println("Hello. Please enter your Account Number. If you want to create an account, just press Enter.");
        String input;
    
        while(true){
            input = scanner.nextLine();
            try{

                if(input.equals("")){
                    return false;
                }

                int i = Integer.valueOf(input);
                if(i < 0){
                    System.out.println("Invalid format of account number. Please try again.");
                    continue;
                }

                if(accountsManager.logIn(i)){
                    return true;
                }else{
                    System.out.println("Account does not exist. Please try again.");
                }
            }catch(NumberFormatException e){
                System.out.println("Invalid format of account number. Please try again.");
                continue;
            }
        }
    }

    public static void viewAccount(){
        String input;
        System.out.println("Account # " + accountsManager.getAccountNum() + "\n1.Deposit\n2.Withdraw\n3.View Balance\n4.Quit");
        while(true){
            input = scanner.nextLine();
            if(input.equals("1")){
                System.out.println("Enter deposit amount:");
                while(true){
                    input = scanner.nextLine();
                    try{
                        Integer.valueOf(input);
                    }catch(NumberFormatException e){
                        System.out.println("Not a number. Enter deposit amount:");
                    }
                    int i = Integer.valueOf(input);
                    if(accountsManager.deposit(i)){
                        System.out.println("Success.\nAccount # " + accountsManager.getAccountNum() + "\n1.Deposit\n2.Withdraw\n3.View Balance\4.Quit");
                        break;
                    }else{
                        System.out.println("Unable to deposit. Account # " + accountsManager.getAccountNum() + "\n1.Deposit\n2.Withdraw\n3.View Balance\n4.Quit");
                        break;
                    }
                }

            }else if(input.equals("2")){
                System.out.println("Enter withdraw amount:");
                while(true){
                    input = scanner.nextLine();
                    try{
                        Integer.valueOf(input);
                    }catch(NumberFormatException e){
                        System.out.println("Not a number. Enter withdraw amount:");
                    }
                    int i = Integer.valueOf(input);
                    if(accountsManager.withdraw(i)){
                        System.out.println("Success.\nAccount # " + accountsManager.getAccountNum() + "\n1.Deposit\n2.Withdraw\n3.View Balance\n4.Quit");
                        break;
                    }else{
                        System.out.println("Unable to withdraw.\nAccount # " + accountsManager.getAccountNum() + "\n1.Deposit\n2.Withdraw\n3.View Balance\n4.Quit");
                        break;
                    }
                
                }
            }else if(input.equals("3")){
                System.out.println("Current Balance: $" + accountsManager.getAccount().getBalance());
                System.out.println("Account # " + accountsManager.getAccountNum() + "\n1.Deposit\n2.Withdraw\n3.View Balance\n4.Quit");
            }else if(input.equals("4")){
                return;
            }else{
                System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static void accountCreation(){
        String input;
        System.out.println("Enter what kind of account you want to open.\n\n1.Checkings\n2.Savings");
        while(true){
            input = scanner.nextLine();
            if(input.equals("1")){
                System.out.println("Enter initial deposit:");
                while(true){
                    input = scanner.nextLine();
                    try{
                        Integer.valueOf(input);
                    }catch(NumberFormatException e){
                        System.out.println("Invalid number. Try again.");
                    }

                    int i = Integer.valueOf(input);
                    if(i < 0){
                        System.out.println("Initial deposit cannot be negative. Try again.");
                    }else{
                        AccountsManager.createAccount(AccountsEnum.CHECKINGS, i);
                        return;
                    }
                }

            }else if(input.equals("2")){
                System.out.println("Enter initial deposit:");
                while(true){
                    input = scanner.nextLine();
                    try{
                        Integer.valueOf(input);
                    }catch(NumberFormatException e){
                        System.out.println("Invalid number. Try again.");
                    }

                    int i = Integer.valueOf(input);
                    if(i < 0){
                        System.out.println("Initial deposit cannot be negative. Try again.");
                    }else{
                        AccountsManager.createAccount(AccountsEnum.SAVINGS, i);
                        return;
                    }
                }
            }else{
                System.out.println("Invalid option. Try again.");
            }
        }
    }
}
