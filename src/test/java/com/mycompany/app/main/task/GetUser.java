package com.mycompany.app.main.task;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetUser implements Task {
	private final int page;
	public GetUser(int page) {
		this.page = page;
	}
	
	public static Performable fromPage(int page) {
		return instrumented(GetUser.class, page);
	}
	@Override
	public <T extends Actor> void performAs(T actor) {
		actor.attemptsTo(
				Get.resource("/users?page="+this.page)
				.with(requestSpecification -> requestSpecification.contentType(ContentType.JSON)
						.header("header1", "value1")
						)
		);
	}
}
