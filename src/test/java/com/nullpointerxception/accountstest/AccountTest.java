package com.nullpointerxception.accountstest;

import org.junit.Test;
import static org.junit.Assert.*;
import com.nullpointerxception.accounts.*;

public class AccountTest{

    @Test
    public void newCheckingsTest(){
        Account a = new Checkings(1);
        assertEquals(1, a.getAccountNum());
        assertEquals(0, a.getBalance());
    }

    @Test
    public void newCheckingsWithBalanceTest(){
        Account a = new Checkings(500, 500);
        assertEquals(500, a.getAccountNum());
        assertEquals(500, a.getBalance());
    }

    @Test
    public void newSavingsTest(){
        Account a = new Savings(1);
        assertEquals(1, a.getAccountNum());
        assertEquals(0, a.getBalance());

    }

    @Test
    public void newSavingsWithBalanceTest(){
        Account a = new Savings(500, 500);
        assertEquals(500, a.getAccountNum());
        assertEquals(500, a.getBalance());

    }

    @Test
    public void CantWithdrawMoreThanBalance(){
        Account a = new Checkings(10, 500);
        assertFalse(a.withdraw(1000));
        assertFalse(a.withdraw(501));
        
    }

    @Test
    public void CantDepositNegtiveNumber(){
        Account a = new Checkings(10, 500);
        assertFalse(a.deposit(-500));
        assertFalse(a.deposit(-1));
    }

}