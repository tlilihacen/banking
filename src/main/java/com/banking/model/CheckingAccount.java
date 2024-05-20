package com.banking.model;

public class CheckingAccount extends Account {
    public CheckingAccount(String accountHolder, double initialBalance, String accountId) {
        super(accountHolder, initialBalance, accountId);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            super.withdraw(amount);
        } else {
            throw new IllegalArgumentException("Invalid withdrawal amount. Balance cannot be negative.");
        }
    }

    @Override
    public void displayBalance() {
        System.out.println("Checking Account Balance: " + balance);
    }
}