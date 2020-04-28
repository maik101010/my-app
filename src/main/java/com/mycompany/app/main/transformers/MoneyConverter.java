package com.mycompany.app.main.transformers;

import com.mycompany.app.main.model.Money;
import cucumber.api.Transformer;

public class MoneyConverter extends Transformer<Money> {

    public Money transform(String amount) {
        String[] numbers = amount.split("\\.");
        int pesos = Integer.parseInt(numbers[0]);
        int cents = Integer.parseInt(numbers[1]);
        return new Money(pesos, cents);
    }
}