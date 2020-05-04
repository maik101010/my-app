package com.mycompany.app.main.questions;

import com.mycompany.app.main.model.UserToken;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class GetTokenQuestion implements Question<UserToken> {

	@Override
	public UserToken answeredBy(Actor actor) {
		return SerenityRest.lastResponse().as(UserToken.class);
	}

}
