# UML Class Diagram — Banking System

```
┌─────────────────────────────────────────────────────────────────────┐
│                         <<abstract>>                                │
│                          BankAccount                                │
├─────────────────────────────────────────────────────────────────────┤
│ - accountNumber : String                                            │
│ - holderName    : String                                            │
│ # balance       : double                                            │
│ - transactions  : List<Transaction>                                 │
├─────────────────────────────────────────────────────────────────────┤
│ + BankAccount(accountNumber, holderName, initialDeposit)            │
│ + getAccountNumber() : String                                       │
│ + getHolderName()    : String                                       │
│ + getBalance()       : double                                       │
│ + getTransactions()  : List<Transaction>                            │
│ + deposit(amount)    : void  throws InvalidAmountException          │
│ + withdraw(amount)   : void  throws InvalidAmountException,         │
│                               InsufficientFundsException  {abstract}│
│ # addTransaction(type, amount) : void                               │
│ + getStatement()     : void                                         │
│ + getAccountType()   : String  {abstract}                           │
└──────────────────────────────┬──────────────────────────────────────┘
                               │
               ┌───────────────┴───────────────┐
               │                               │
               ▼                               ▼
┌──────────────────────────┐   ┌──────────────────────────────┐
│      SavingsAccount      │   │       CurrentAccount         │
├──────────────────────────┤   ├──────────────────────────────┤
│ - MINIMUM_BALANCE: 1000  │   │ - OVERDRAFT_LIMIT: 5000      │
├──────────────────────────┤   ├──────────────────────────────┤
│ + withdraw(amount): void │   │ + withdraw(amount): void     │
│ + getAccountType():String│   │ + getAccountType(): String   │
│ + getMinimumBalance():   │   │ + getOverdraftLimit():       │
│     double               │   │     double                   │
└──────────────────────────┘   └──────────────────────────────┘


┌──────────────────────────────────────────────────────┐
│                      Transaction                     │
├──────────────────────────────────────────────────────┤
│ - date   : LocalDateTime                             │
│ - type   : String                                    │
│ - amount : double                                    │
├──────────────────────────────────────────────────────┤
│ + Transaction(type, amount)                          │
│ + getDate()   : LocalDateTime                        │
│ + getType()   : String                               │
│ + getAmount() : double                               │
│ + toString()  : String                               │
└──────────────────────────────────────────────────────┘
         ▲ (composed in BankAccount.transactions)


┌──────────────────────────────────────────────────────────────────┐
│                             Bank                                 │
├──────────────────────────────────────────────────────────────────┤
│ - accounts       : Map<String, BankAccount>                      │
│ - accountCounter : int                                           │
├──────────────────────────────────────────────────────────────────┤
│ + createSavingsAccount(holderName, initialDeposit) : String      │
│ + createCurrentAccount(holderName, initialDeposit) : String      │
│ + getAccount(accountNumber) : BankAccount                        │
│   throws AccountNotFoundException                                │
│ + deposit(accountNumber, amount) : void                          │
│   throws AccountNotFoundException, InvalidAmountException        │
│ + withdraw(accountNumber, amount) : void                         │
│   throws AccountNotFoundException, InvalidAmountException,       │
│          InsufficientFundsException                              │
│ + transfer(from, to, amount) : void                              │
│   throws AccountNotFoundException, InvalidAmountException,       │
│          InsufficientFundsException                              │
│ + displayAllAccounts() : void                                    │
└──────────────────────────────────────────────────────────────────┘
         │ uses ▲
         └──────┘


┌──────────────────────────────────────────────────────┐
│                   <<exception>>                      │
│              AccountNotFoundException                │
│              InsufficientFundsException              │
│              InvalidAmountException                  │
│                  (all extend Exception)              │
└──────────────────────────────────────────────────────┘


Relationships:
  BankAccount  <|──  SavingsAccount      (inheritance)
  BankAccount  <|──  CurrentAccount      (inheritance)
  BankAccount  *───  Transaction         (composition)
  Bank         o───  BankAccount         (aggregation)
  Bank         ..>   AccountNotFoundException        (throws)
  Bank         ..>   InvalidAmountException          (throws)
  Bank         ..>   InsufficientFundsException      (throws)
```
