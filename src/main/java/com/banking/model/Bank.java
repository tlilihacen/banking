package com.banking.model;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;
    private int accountCounter;

    public Bank() {
        accounts = new HashMap<>();
        accountCounter = 1;
    }

    public String createAccount(String accountHolder, double initialBalance, String accountType, ChronoUnit periodUnit, long periodLength) {
        String accountId = String.valueOf(accountCounter++);
        Account account;
        if (accountType.equalsIgnoreCase("checking")) {
            account = new CheckingAccount(accountHolder, initialBalance, accountId);
        } else if (accountType.equalsIgnoreCase("savings")) {
            account = new SavingsAccount(accountHolder, initialBalance, accountId, periodUnit, periodLength);
        } else {
            throw new IllegalArgumentException("Unknown account type");
        }
        accounts.put(accountId, account);
        return accountId;
    }

    public String createAccount(String accountHolder, double initialBalance, String accountType) {
        return createAccount(accountHolder, initialBalance, accountType, ChronoUnit.MONTHS, 1);
    }

    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }
}