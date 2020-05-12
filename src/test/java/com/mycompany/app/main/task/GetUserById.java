package com.mycompany.app.main.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetUserById implements Task {
    private final int id;

    public GetUserById(int id) {
        this.id = id;
    }

    public static GetUserById withId(int id){
        return instrumented(GetUserById.class, id);
    }

    @Override
    @Step("{0} fetches the user with id #id")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/users/{id}")
                .with(requestSpecification -> requestSpecification.pathParam("id", id))
        );
    }
}
