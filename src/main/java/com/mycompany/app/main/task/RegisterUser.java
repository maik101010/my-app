package com.mycompany.app.main.task;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import com.mycompany.app.main.interactions.Post;
import com.mycompany.app.main.model.UserRegisterUpdate;

public class RegisterUser implements Task {
	
	private final UserRegisterUpdate userInfo;

    public RegisterUser(UserRegisterUpdate userInfo) {
        this.userInfo = userInfo;
    }

    public static Performable withInfo(UserRegisterUpdate userInfo) {
        return instrumented(RegisterUser.class, userInfo);
    }

	@Override
	public <T extends Actor> void performAs(T actor) {
		actor.attemptsTo(Post.to("/register")
				.with(requestSpecification -> requestSpecification.contentType(ContentType.JSON)
				.body(userInfo)));
	}

}
