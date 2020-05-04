package com.mycompany.app.main.facts;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.facts.Fact;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.HashMap;

public class UsersData implements Fact {
	private String dataInfo;
	private int id;
	public UsersData(int id) {
		this.id = id;
	}
	
	public static UsersData toViewUsers(int id) {
		return new UsersData(id);
	}

	@Override
	public void setup(Actor actor) {
		actor.attemptsTo(
				Get.resource("/users/"+id)
				);
		HashMap<String, String> data = SerenityRest.lastResponse().path("data");
		actor.remember("users", data);
		
		dataInfo = data.toString();
	}

	@Override
	public String toString() {
		return "El user con id "+ id+ " tiene " +dataInfo;
	}
	
	

}
