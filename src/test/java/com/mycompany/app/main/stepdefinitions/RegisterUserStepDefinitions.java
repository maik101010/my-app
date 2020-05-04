package com.mycompany.app.main.stepdefinitions;

import com.mycompany.app.main.enums.ApiUrl;
import com.mycompany.app.main.enums.StatusCode;
import com.mycompany.app.main.model.UserToken;
import com.mycompany.app.main.questions.GetTokenQuestion;
import com.mycompany.app.main.questions.ResponseCode;
import com.mycompany.app.main.task.RegisterUser;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class RegisterUserStepDefinitions {
    Actor actor;

    @Given("^(.*) es un cliente que quiere poder administrar sus productos bancarios$")
    public void elEsUnClienteQueQuierePoderAdministrarSusProductosBancarios(String name) {
        actor = new Actor(String.format("%s, el trainer", name));
        actor.whoCan(CallAnApi.at(ApiUrl.API_URL.getApi()));
    }

    @When("^el envia la informacion requerida para el registro$")
    public void elEnviaLaInformacionRequeridaParaElRegistro() {
        actor.attemptsTo(
                RegisterUser
                        .with()
                        .withName("morpheus")
                        .withJob("leader")
                        .withEmail("tracey.ramos@reqres.in")
                        .withPassword("serenity")
                        .rememberMe()
        );
    }

    @Then("^el debe obtener una cuenta virtual para poder ingresar cuando lo requiera$")
    public void elDebeObtenerUnaCuentaVirtualParaPoderIngresarCuandoLoRequiera() {
        actor.should(seeThat("El codigo de respuesta", new ResponseCode(), equalTo(StatusCode.STATUS_CODE_200.getStatusCode())));
        UserToken userToken = new GetTokenQuestion().answeredBy(actor);
        actor.should(seeThat("El token es", token -> userToken.getToken(), equalTo("QpwL5tke4Pnpja7X6")));
    }

}


