package com.mycompany.app.main.stepdefinitions.bankinapp;

import com.mycompany.app.main.enums.AccountType;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CashWithdrawalsStepDefinitions {

    @Given("^(.*) has \\$(\\d+) in his (.*) account$")
    public void in_his_current_account(String customer, int balance, AccountType accountType) {

    }

    @When("^he withdraws \\$(\\d+) in cash$")
    public void he_withdraws_$_in_cash(int arg1) {
    }

    @Then("^his remaining balance should be \\$(\\d+)$")
    public void his_remaining_balance_should_be_$(int arg1) {
    }

}
