package com.mycompany.app.main.stepdefinitions;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;


import com.mycompany.app.main.model.UserRegisterUpdate;
import com.mycompany.app.main.model.UserToken;
import com.mycompany.app.main.questions.GetTokenQuestion;
import com.mycompany.app.main.questions.ResponseCode;
import com.mycompany.app.main.task.RegisterUser;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;

public class RegisterUserStepDefinitions {
	private static final String API_URL = "https://reqres.in/api";
	private static final int STATUS_CODE_200 = 200;
	Actor michael = new Actor("michael el trainer");

	@Given("^Michael es un cliente que quiere poder administrar sus productos bancarios$")
	public void michael_es_un_cliente_que_quiere_poder_administrar_sus_productos_bancarios() {
		michael.whoCan(CallAnApi.at(API_URL));

	}


	@When("^el envia la informacion requerida para el registro$")
	public void el_envia_la_informacion_requerida_para_el_registro() {
		UserRegisterUpdate userRegister = new UserRegisterUpdate("morpheus", "leader", "tracey.ramos@reqres.in",
				"serenity");
//		String userRegister = "{\r\n" + 
//				"	\"name\":\"morpheus\",\r\n" + 
//				"	\"job\":\"leader\",\r\n" + 
//				"    \"email\": \"tracey.ramos@reqres.in\",\r\n" + 
//				"    \"password\": \"serenity\"\r\n" + 
//				"}";
		michael.attemptsTo(
				RegisterUser.withInfo(userRegister)
		);
	}

	@Then("^el debe obtener una cuenta virtual para poder ingresar cuando lo requiera$")
	public void el_debe_obtener_una_cuenta_virtual_para_poder_ingresar_cuando_lo_requiera() {
		michael.should(seeThat("El codigo de respuesta", new ResponseCode(), equalTo(STATUS_CODE_200)));
		UserToken userToken = new GetTokenQuestion().answeredBy(michael);
		michael.should(seeThat("El token es", token -> userToken.getToken(), equalTo("QpwL5tke4Pnpja7X6")));
	}

}
