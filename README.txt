Overview
This Java application simulates basic operations for a banking system, supporting both checking and savings accounts. Users can create accounts, deposit money, withdraw money, display account balances, and calculate monthly interest for savings accounts.

Features
Account Creation: Create a checking or savings account with a unique identifier.
Deposit: Deposit money into an account.
Withdrawal: Withdraw money from an account, with specific rules for checking and savings accounts.
Display Balance: Display the balance of an account.
Interest Calculation: Calculate and add monthly interest to a savings account.
Error Handling: Proper handling of non-existent accounts, insufficient balances, and exceeded withdrawal limits.

Project Structure
Account.java: Abstract class representing a generic bank account.
CheckingAccount.java: Class for checking account operations.
SavingsAccount.java: Class for savings account operations including interest calculation.
Bank.java: Class for managing accounts.
BankingApplication.java: Main application class providing the user interface.
BankTest.java: JUnit test class for unit tests.

Unit Tests
Unit tests are written using JUnit to ensure the proper functioning of the application.
