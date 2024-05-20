package com.banking;

import java.util.Scanner;


import com.banking.model.Account;
import com.banking.model.Bank;
import com.banking.model.SavingsAccount;

public class BankingApplication {
	public static void main(String[] args) {
		Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create an account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Display balance");
            System.out.println("5. Calculate interest for a savings account");
            System.out.println("6. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter your name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter the initial balance: ");
                        double initialBalance = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Choose the account type (1 for checking, 2 for savings): ");
                        int type = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        String accountType = (type == 1) ? "checking" : "savings";
                        String accountId = bank.createAccount(name, initialBalance, accountType);
                        System.out.println(accountType + " account created successfully. Account identifier: " + accountId);
                        break;
                    case 2:
                        System.out.print("Enter your account identifier: ");
                        String depositAccountId = scanner.nextLine();
                        System.out.print("Enter the amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        bank.getAccount(depositAccountId).deposit(depositAmount);
                        System.out.println(depositAmount + " euros have been deposited into your account.");
                        break;
                    case 3:
                        System.out.print("Enter your account identifier: ");
                        String withdrawAccountId = scanner.nextLine();
                        System.out.print("Enter the amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        bank.getAccount(withdrawAccountId).withdraw(withdrawAmount);
                        System.out.println(withdrawAmount + " euros have been withdrawn from your account.");
                        break;
                    case 4:
                        System.out.print("Enter your account identifier: ");
                        String displayAccountId = scanner.nextLine();
                        bank.getAccount(displayAccountId).displayBalance();
                        break;
                    case 5:
                        System.out.print("Enter your account identifier: ");
                        String interestAccountId = scanner.nextLine();
                        Account account = bank.getAccount(interestAccountId);
                        if (account instanceof SavingsAccount) {
                            ((SavingsAccount) account).calculateMonthlyInterest();
                        } else {
                            System.out.println("Interest calculation is only applicable to savings accounts.");
                        }
                        break;
                    case 6:
                        System.out.println("Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
	}
}
