package com.mycompany.app.main.task;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import com.mycompany.app.main.model.UserRegisterUpdate;

public class UpdateUser implements Task {
	
	private final UserRegisterUpdate userInfo;

    public UpdateUser(UserRegisterUpdate userInfo) {
        this.userInfo = userInfo;
    }

    public static Performable withInfo(UserRegisterUpdate userInfo) {
        return instrumented(UpdateUser.class, userInfo);
    }

	@Override
	public <T extends Actor> void performAs(T actor) {
		actor.attemptsTo(Put.to("/users/2")
				.with(requestSpecification -> requestSpecification.contentType(ContentType.JSON)
				.body(userInfo)));
	}

}
