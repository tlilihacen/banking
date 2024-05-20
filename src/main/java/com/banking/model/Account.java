package com.banking.model;

public abstract class Account {
    protected String accountHolder;
    protected double balance;
    protected String accountId;

    public Account(String accountHolder, double initialBalance, String accountId) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Invalid withdrawal amount.");
        }
    }

    public abstract void displayBalance();
}
