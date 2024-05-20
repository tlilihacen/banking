package com.banking;

import static org.junit.jupiter.api.Assertions.*;

import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.banking.model.Account;
import com.banking.model.Bank;
import com.banking.model.SavingsAccount;

public class BankTest {
    private Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
    }

    @Test
    public void testCreateAccount() {
        String accountId = bank.createAccount("Alice Smith", 5000, "savings", ChronoUnit.MONTHS, 1);
        assertNotNull(accountId);
        Account account = bank.getAccount(accountId);
        assertTrue(account instanceof SavingsAccount);
        assertEquals(5000, account.getBalance());
    }

    @Test
    public void testDeposit() {
        String accountId = bank.createAccount("Alice Smith", 5000, "checking");
        bank.getAccount(accountId).deposit(1000);
        assertEquals(6000, bank.getAccount(accountId).getBalance());
    }

    @Test
    public void testWithdraw() {
        String accountId = bank.createAccount("Alice Smith", 5000, "checking");
        bank.getAccount(accountId).withdraw(1000);
        assertEquals(4000, bank.getAccount(accountId).getBalance());
    }

    @Test
    public void testInterestCalculation() {
        String accountId = bank.createAccount("Alice Smith", 5000, "savings", ChronoUnit.MONTHS, 1);
        SavingsAccount account = (SavingsAccount) bank.getAccount(accountId);
        account.calculateMonthlyInterest();
        assertEquals(5250, account.getBalance());
    }

    @Test
    public void testWithdrawExceedingBalance() {
        String accountId = bank.createAccount("Alice Smith", 5000, "checking");
        assertThrows(IllegalArgumentException.class, () -> {
            bank.getAccount(accountId).withdraw(6000);
        });
    }

    @Test
    public void testWithdrawExceedingMaxForSavings() {
        String accountId = bank.createAccount("Alice Smith", 5000, "savings", ChronoUnit.MONTHS, 1);
        assertThrows(IllegalArgumentException.class, () -> {
            bank.getAccount(accountId).withdraw(1500);
        });
    }

    @Test
    public void testWithdrawWithinMaxForSavings() {
        String accountId = bank.createAccount("Alice Smith", 5000, "savings", ChronoUnit.MONTHS, 1);
        SavingsAccount account = (SavingsAccount) bank.getAccount(accountId);
        account.withdraw(1000);
        assertEquals(4000, account.getBalance());
    }

    @Test
    public void testWithdrawExceedingMaxAmountPerPeriod() {
        String accountId = bank.createAccount("Alice Smith", 5000, "savings", ChronoUnit.MONTHS, 1);
        SavingsAccount account = (SavingsAccount) bank.getAccount(accountId);

        // First withdrawal within the limit
        account.withdraw(500);
        assertEquals(4500, account.getBalance());

        // Second withdrawal exceeding the monthly limit
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(600);
        });

        // The balance should remain the same after the failed withdrawal
        assertEquals(4500, account.getBalance());
    }
}