package com.banking.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.05;
    private static final double MAX_WITHDRAWAL_PER_PERIOD = 1000;
    private double currentPeriodWithdrawal;
    private LocalDate lastWithdrawalDate;
    private ChronoUnit periodUnit;
    private long periodLength;

    public SavingsAccount(String accountHolder, double initialBalance, String accountId, ChronoUnit periodUnit, long periodLength) {
        super(accountHolder, initialBalance, accountId);
        this.currentPeriodWithdrawal = 0;
        this.lastWithdrawalDate = LocalDate.now();
        this.periodUnit = periodUnit;
        this.periodLength = periodLength;
    }

    @Override
    public void withdraw(double amount) {
        LocalDate today = LocalDate.now();
        long periodsBetween = periodUnit.between(lastWithdrawalDate, today);

        if (periodsBetween >= periodLength) {
            // Reset withdrawal count if a new period has started
            currentPeriodWithdrawal = 0;
            lastWithdrawalDate = today;
        }

        if (amount > 0 && amount <= balance && (currentPeriodWithdrawal + amount) <= MAX_WITHDRAWAL_PER_PERIOD) {
            currentPeriodWithdrawal += amount;
            super.withdraw(amount);
        } else {
            throw new IllegalArgumentException("Invalid withdrawal amount. Exceeds maximum allowed per period or insufficient balance.");
        }
    }

    public void calculateMonthlyInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        System.out.println("Interest for this month is " + interest + " euros.");
    }

    @Override
    public void displayBalance() {
        System.out.println("Savings Account Balance: " + balance);
    }
}