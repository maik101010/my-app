package com.mycompany.app.main.questions;

import com.mycompany.app.main.model.User;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class GetUsersQuestion implements Question<User>{

	@Override
	public User answeredBy(Actor actor) {
		//Nos devuelve un objeto de tipo user, representando las propiedades del json de la api
		return SerenityRest.lastResponse().as(User.class);
	}

}
