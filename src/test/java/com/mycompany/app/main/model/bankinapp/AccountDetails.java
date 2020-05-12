package com.mycompany.app.main.model.bankinapp;

import com.mycompany.app.main.enums.AccountType;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AccountDetails {
    private AccountType account;
    private int balance;

    public AccountDetails(AccountType account, int balance) {
        this.account = account;
        this.balance = balance;
    }

    public AccountDetails() {
    }

    public AccountType getAccount() {
        return account;
    }

    public int getBalance() {
        return balance;
    }
}
