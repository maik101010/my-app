package com.mycompany.app.main.task;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class FindEmployes implements Task {

    public static Performable fromPage() {
        return instrumented(FindEmployes.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/unknown")
                        .with(requestSpecification -> requestSpecification.contentType(ContentType.JSON)
                        )
        );
    }
}
