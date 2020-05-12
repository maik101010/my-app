package com.mycompany.app.main.task;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class UnSuccesFullLogin implements Task {

    private String email;

    public UnSuccesFullLogin(String email) {
        this.email = email;
    }

    public static Performable fromPage(String email) {
        return instrumented(UnSuccesFullLogin.class, email);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/login")
                        .with(requestSpecification -> requestSpecification.contentType(ContentType.JSON)
                                .body(email)
                        )
        );
    }
}
