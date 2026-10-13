package models;

import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;

public class SavingsAccount extends BankAccount {

    private static final double MINIMUM_BALANCE = 1000.00;

    public SavingsAccount(String accountNumber,
                          String holderName,
                          double initialDeposit) {

        super(accountNumber, holderName, initialDeposit);

        if (initialDeposit < MINIMUM_BALANCE) {
            throw new IllegalArgumentException(
                    "Savings account requires minimum initial deposit of "
                            + MINIMUM_BALANCE
            );
        }
    }

    @Override
    public void withdraw(double amount)
            throws InvalidAmountException,
            InsufficientFundsException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Withdrawal amount must be greater than zero."
            );
        }

        if (balance - amount < MINIMUM_BALANCE) {
            throw new InsufficientFundsException(
                    "Withdrawal denied. Minimum balance of "
                            + MINIMUM_BALANCE
                            + " must be maintained."
            );
        }

        balance -= amount;

        addTransaction("WITHDRAW", amount);
    }

    @Override
    public String getAccountType() {
        return "SAVINGS";
    }

    public double getMinimumBalance() {
        return MINIMUM_BALANCE;
    }
}