package models;

import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;

public class CurrentAccount extends BankAccount {

    private static final double OVERDRAFT_LIMIT = 5000.00;

    public CurrentAccount(String accountNumber,
                          String holderName,
                          double initialDeposit) {

        super(accountNumber, holderName, initialDeposit);

        if (initialDeposit < 0) {
            throw new IllegalArgumentException(
                    "Initial deposit cannot be negative."
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

        if (balance - amount < -OVERDRAFT_LIMIT) {
            throw new InsufficientFundsException(
                    "Withdrawal denied. Overdraft limit of "
                            + OVERDRAFT_LIMIT
                            + " exceeded."
            );
        }

        balance -= amount;

        addTransaction("WITHDRAW", amount);
    }

    @Override
    public String getAccountType() {
        return "CURRENT";
    }

    public double getOverdraftLimit() {
        return OVERDRAFT_LIMIT;
    }
}