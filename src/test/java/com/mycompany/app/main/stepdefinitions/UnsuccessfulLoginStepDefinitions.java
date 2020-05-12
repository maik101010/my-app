package com.mycompany.app.main.stepdefinitions;

import com.mycompany.app.main.enums.StatusCode;
import com.mycompany.app.main.questions.ResponseCode;
import com.mycompany.app.main.task.UnSuccesFullLogin;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class UnsuccessfulLoginStepDefinitions {
    private String theRestApiBaseUrl;
    private EnvironmentVariables environmentVariables;
    private Actor michael;

    public UnsuccessfulLoginStepDefinitions() {
    }

    @Before
    public void setTheStage() {
        theRestApiBaseUrl = environmentVariables.optionalProperty("restapi.baseurl").get();
    }


    @Given("^(.*) es un cliente que esta ingresando sin contrasena$")
    public void michael_es_un_cliente_que_esta_ingresando_sin_contrasena(String name) {
        michael = Actor.named(name+" the train").whoCan(CallAnApi.at(theRestApiBaseUrl));
    }

    @When("^el intenta poner un email sin contraseña$")
    public void el_intenta_poner_un_email_sin_contraseña() {
        String email ="{\n" +
                "    \"email\": \"peter@klaven\"\n" +
                "}";
        michael.attemptsTo(
                UnSuccesFullLogin.fromPage(email)
        );
    }

    @Then("^el deberia obtener un error de respuesta$")
    public void el_deberia_obtener_un_error_de_respuesta() {
        michael.should(seeThat("El codigo de respuesta ", new ResponseCode(), equalTo(StatusCode.STATUS_CODE_400.getStatusCode())));
    }

}
