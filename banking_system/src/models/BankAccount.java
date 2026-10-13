package models;

import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;

import java.util.ArrayList;
import java.util.List;

public abstract class BankAccount {

    private String accountNumber;
    private String holderName;
    protected double balance;

    private List<Transaction> transactions;

    public BankAccount(String accountNumber, String holderName,
                       double initialDeposit) {

        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialDeposit;

        this.transactions = new ArrayList<>();

        if (initialDeposit > 0) {
            transactions.add(
                    new Transaction("ACCOUNT OPENED", initialDeposit)
            );
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void deposit(double amount)
            throws InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Deposit amount must be greater than zero."
            );
        }

        balance += amount;

        transactions.add(
                new Transaction("DEPOSIT", amount)
        );
    }

    public abstract void withdraw(double amount)
            throws InvalidAmountException,
            InsufficientFundsException;

    protected void addTransaction(String type, double amount) {
        transactions.add(
                new Transaction(type, amount)
        );
    }

    public void getStatement() {

        System.out.println("\n==============================================");
        System.out.println("              ACCOUNT STATEMENT");
        System.out.println("==============================================");

        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + holderName);
        System.out.printf("Current Balance: %.2f%n", balance);

        System.out.println("----------------------------------------------");
        System.out.printf(
                "%-20s %-15s %s%n",
                "Date",
                "Transaction",
                "Amount"
        );
        System.out.println("----------------------------------------------");

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }

        System.out.println("----------------------------------------------");
        System.out.printf("Available Balance: %.2f%n", balance);
        System.out.println("==============================================");
    }

    public abstract String getAccountType();
}