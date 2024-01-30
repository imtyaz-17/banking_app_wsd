package com.wsd.banking;
enum AccountType {
    CURRENT, SAVINGS, SALARY, DEFAULT
}

public class BankAccount {
    private AccountType accountType;
    private String name;
    private int number;
    private String creationDate;
    protected double balance;
    
    public static final double MIN_ACCOUNT_BALANCE = 500.0;
    
    // Constructor using the minimum balance from the account type
    public BankAccount(AccountType accountType, String name, int number, String creationDate, double balance) {
        this.accountType = accountType;
        this.name = name;
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
    }

	// Validation for withdrawal considering minimum balance for each account type
    public boolean canWithdraw(double amount) {
        return balance - amount >= MIN_ACCOUNT_BALANCE;
    }

    // Getters and setters
    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account [Type=" + accountType + ", name=" + name + ", number=" + number +
                ", creationDate=" + creationDate + ", balance=" + balance + "]";
    }
}