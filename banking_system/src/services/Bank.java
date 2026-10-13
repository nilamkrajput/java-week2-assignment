package services;

import exceptions.AccountNotFoundException;
import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;
import models.BankAccount;
import models.CurrentAccount;
import models.SavingsAccount;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<String, BankAccount> accounts;

    private int accountCounter = 1001;

    public Bank() {
        accounts = new HashMap<>();
    }

    private String generateAccountNumber() {
        return "ACC" + accountCounter++;
    }

    public String createSavingsAccount(
            String holderName,
            double initialDeposit) {

        String accountNumber = generateAccountNumber();

        SavingsAccount account =
                new SavingsAccount(
                        accountNumber,
                        holderName,
                        initialDeposit
                );

        accounts.put(accountNumber, account);

        return accountNumber;
    }

    public String createCurrentAccount(
            String holderName,
            double initialDeposit) {

        String accountNumber = generateAccountNumber();

        CurrentAccount account =
                new CurrentAccount(
                        accountNumber,
                        holderName,
                        initialDeposit
                );

        accounts.put(accountNumber, account);

        return accountNumber;
    }

    public BankAccount getAccount(String accountNumber)
            throws AccountNotFoundException {

        BankAccount account = accounts.get(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException(
                    "Account not found: " + accountNumber
            );
        }

        return account;
    }

    public void deposit(String accountNumber, double amount)
            throws AccountNotFoundException,
            InvalidAmountException {

        BankAccount account = getAccount(accountNumber);

        account.deposit(amount);
    }

    public void withdraw(String accountNumber, double amount)
            throws AccountNotFoundException,
            InvalidAmountException,
            InsufficientFundsException {

        BankAccount account = getAccount(accountNumber);

        account.withdraw(amount);
    }
//    https://towardsdev.com/bank-management-system-project-in-java-using-oop-concepts-11659464ce38
    public void transfer(
            String fromAccountNumber,
            String toAccountNumber,
            double amount)
            throws AccountNotFoundException,
            InvalidAmountException,
            InsufficientFundsException {

        if (fromAccountNumber.equals(toAccountNumber)) {
            throw new InvalidAmountException(
                    "Source and destination accounts cannot be the same."
            );
        }

        BankAccount source =
                getAccount(fromAccountNumber);

        BankAccount destination =
                getAccount(toAccountNumber);

        // First withdraw from source
        source.withdraw(amount);

        // Then deposit into destination
        destination.deposit(amount);

        // Add transfer-specific transaction records
        source.getTransactions().remove(
                source.getTransactions().size() - 1
        );

        destination.getTransactions().remove(
                destination.getTransactions().size() - 1
        );

        source.getTransactions().add(
                new models.Transaction(
                        "TRANSFER OUT",
                        amount
                )
        );

        destination.getTransactions().add(
                new models.Transaction(
                        "TRANSFER IN",
                        amount
                )
        );
    }

    public void displayAllAccounts() {

        System.out.println("\n==========================================================");
        System.out.println("                    ALL ACCOUNTS");
        System.out.println("==========================================================");

        System.out.printf(
                "%-12s %-20s %-12s %15s%n",
                "Account",
                "Holder",
                "Type",
                "Balance"
        );

        System.out.println("----------------------------------------------------------");

        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }

        for (BankAccount account : accounts.values()) {

            System.out.printf(
                    "%-12s %-20s %-12s %15.2f%n",
                    account.getAccountNumber(),
                    account.getHolderName(),
                    account.getAccountType(),
                    account.getBalance()
            );
        }

        System.out.println("==========================================================");
    }
}