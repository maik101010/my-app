Feature: Cash Withdrawals

  Scenario: Cash withdrawal
    Given Clive has the following accounts:
      | account | balance |
      | Current | 1000    |
      | Savings | 2000    |
    When he withdraws $100 in cash
    Then his remaining balance should be $900