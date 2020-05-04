package com.mycompany.app.main.task;

import com.mycompany.app.main.interactions.Post;
import com.mycompany.app.main.model.UserRegisterUpdate;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class RegisterUser implements Task {
	
	private final UserRegisterUpdate userInfo;

    public RegisterUser(UserRegisterUpdate userInfo) {
        this.userInfo = userInfo;
    }

    public static UserRegisterUpdate with() {
        return new UserRegisterUpdate();
    }

	@Override
	public <T extends Actor> void performAs(T actor) {
		actor.attemptsTo(Post.to("/register")
				.with(requestSpecification -> requestSpecification.contentType(ContentType.JSON)
				.body(userInfo)));
	}

}
