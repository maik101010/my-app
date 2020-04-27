package com.mycompany.app.main.questions;

import com.mycompany.app.main.model.UserResponseUpdate;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class GetUserUpdateQuestion implements Question<UserResponseUpdate>{

	@Override
	public UserResponseUpdate answeredBy(Actor actor) {
		return SerenityRest.lastResponse().as(UserResponseUpdate.class);
	}

}
