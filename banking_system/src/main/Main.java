package main;

import exceptions.AccountNotFoundException;
import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;
import models.BankAccount;
import services.Bank;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static Bank bank = new Bank();

    public static void main(String[] args) {

        int choice;

        do {

            displayMenu();

            choice = readInt("Enter your choice: ");

            try {

                switch (choice) {

                    case 1:
                        createSavingsAccount();
                        break;

                    case 2:
                        createCurrentAccount();
                        break;

                    case 3:
                        deposit();
                        break;

                    case 4:
                        withdraw();
                        break;

                    case 5:
                        transfer();
                        break;

                    case 6:
                        checkBalance();
                        break;

                    case 7:
                        showStatement();
                        break;

                    case 8:
                        bank.displayAllAccounts();
                        break;

                    case 0:
                        System.out.println(
                                "Thank you for using the banking system."
                        );
                        break;

                    default:
                        System.out.println(
                                "Invalid choice."
                        );
                }

            } catch (Exception e) {

                System.out.println(
                        "\nERROR: " + e.getMessage()
                );
            }

        } while (choice != 0);

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println("\n==========================================");
        System.out.println("          BANKING SYSTEM");
        System.out.println("==========================================");
        System.out.println("1. Create Savings Account");
        System.out.println("2. Create Current Account");
        System.out.println("3. Deposit Money");
        System.out.println("4. Withdraw Money");
        System.out.println("5. Transfer Money");
        System.out.println("6. Check Balance");
        System.out.println("7. Account Statement");
        System.out.println("8. Display All Accounts");
        System.out.println("0. Exit");
        System.out.println("==========================================");
    }

    private static void createSavingsAccount() {

        System.out.println("\n--- Create Savings Account ---");

        String name = readString("Enter account holder name: ");

        double amount =
                readDouble("Enter initial deposit: ");

        String accountNumber =
                bank.createSavingsAccount(name, amount);

        System.out.println(
                "Savings account created successfully."
        );

        System.out.println(
                "Account Number: " + accountNumber
        );
    }

    private static void createCurrentAccount() {

        System.out.println("\n--- Create Current Account ---");

        String name = readString("Enter account holder name: ");

        double amount =
                readDouble("Enter initial deposit: ");

        String accountNumber =
                bank.createCurrentAccount(name, amount);

        System.out.println(
                "Current account created successfully."
        );

        System.out.println(
                "Account Number: " + accountNumber
        );
    }

    private static void deposit()
            throws AccountNotFoundException,
            InvalidAmountException {

        System.out.println("\n--- Deposit Money ---");

        String accountNumber =
                readString("Enter account number: ");

        double amount =
                readDouble("Enter deposit amount: ");

        bank.deposit(accountNumber, amount);

        System.out.println(
                "Money deposited successfully."
        );
    }

    private static void withdraw()
            throws AccountNotFoundException,
            InvalidAmountException,
            InsufficientFundsException {

        System.out.println("\n--- Withdraw Money ---");

        String accountNumber =
                readString("Enter account number: ");

        double amount =
                readDouble("Enter withdrawal amount: ");

        bank.withdraw(accountNumber, amount);

        System.out.println(
                "Money withdrawn successfully."
        );
    }

    private static void transfer()
            throws AccountNotFoundException,
            InvalidAmountException,
            InsufficientFundsException {

        System.out.println("\n--- Transfer Money ---");

        String from =
                readString("From account: ");

        String to =
                readString("To account: ");

        double amount =
                readDouble("Transfer amount: ");

        bank.transfer(from, to, amount);

        System.out.println(
                "Money transferred successfully."
        );
    }

    private static void checkBalance()
            throws AccountNotFoundException {

        System.out.println("\n--- Check Balance ---");

        String accountNumber =
                readString("Enter account number: ");

        BankAccount account =
                bank.getAccount(accountNumber);

        System.out.println(
                "Account Holder : "
                        + account.getHolderName()
        );

        System.out.println(
                "Account Type   : "
                        + account.getAccountType()
        );

        System.out.printf(
                "Balance        : %.2f%n",
                account.getBalance()
        );
    }

    private static void showStatement()
            throws AccountNotFoundException {

        System.out.println("\n--- Account Statement ---");

        String accountNumber =
                readString("Enter account number: ");

        BankAccount account =
                bank.getAccount(accountNumber);

        account.getStatement();
    }

    private static String readString(String message) {

        System.out.print(message);

        return scanner.nextLine();
    }

    private static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }

    private static double readDouble(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Double.parseDouble(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid amount."
                );
            }
        }
    }
}