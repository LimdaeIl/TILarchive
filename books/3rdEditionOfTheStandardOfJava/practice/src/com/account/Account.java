package com.account;

public class Account {
    private final String accountNumber;
    private final String name;
    private int deposit;

    public Account(String accountNumber, String name, int deposit) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.deposit = deposit;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public int getDeposit() {
        return deposit;
    }

    public void addDeposit(int deposit) {
        this.deposit += deposit;
    }

    public void subDeposit(int deposit) {
        this.deposit -= deposit;
    }

}
