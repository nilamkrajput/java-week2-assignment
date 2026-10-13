# Banking System

A Java console application demonstrating OOP concepts: inheritance, polymorphism, encapsulation, and custom exceptions.

## Project Structure

```
banking_system/
└── src/
    ├── exceptions/
    │   ├── AccountNotFoundException.java
    │   ├── InsufficientFundsException.java
    │   └── InvalidAmountException.java
    ├── models/
    │   ├── BankAccount.java       (abstract)
    │   ├── SavingsAccount.java
    │   ├── CurrentAccount.java
    │   └── Transaction.java
    ├── services/
    │   └── Bank.java
    └── main/
        └── Main.java
```

## Compilation

From the `banking_system/` directory:

```bash
mkdir -p out
javac -d out src/exceptions/*.java src/models/*.java src/services/*.java src/main/*.java
```

On Windows:

```cmd
mkdir out
javac -d out src\exceptions\*.java src\models\*.java src\services\*.java src\main\*.java
```

## Run

### Interactive mode (menu-driven):
```bash
java -cp out main.Main
```

### Demo mode (automated console demo of all operations):
```bash
java -cp out main.Main --demo
```

## Features

| Operation              | Description                                      |
|------------------------|--------------------------------------------------|
| Create Savings Account | Minimum initial deposit: 1000.00                 |
| Create Current Account | Overdraft limit: 5000.00                         |
| Deposit                | Add funds to any account                         |
| Withdraw               | Enforces minimum balance / overdraft rules       |
| Transfer               | Move funds between two accounts                  |
| Check Balance          | View current balance and account type            |
| Account Statement      | Full transaction history with timestamps         |
| Display All Accounts   | Summary table of all accounts                    |

## Custom Exceptions

- `InvalidAmountException` — negative or zero amount
- `InsufficientFundsException` — below minimum balance or overdraft limit
- `AccountNotFoundException` — account number does not exist
