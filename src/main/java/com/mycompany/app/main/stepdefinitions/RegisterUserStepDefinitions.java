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
    public void michaelEsUnClienteQueQuierePoderAdministrarSusProductosBancarios() {
        michael.whoCan(CallAnApi.at(API_URL));
    }

    @When("^el envia la informacion requerida para el registro$")
    public void elEnviaLaInformacionRequeridaParaElRegistro() {
        UserRegisterUpdate userRegister = new UserRegisterUpdate("morpheus", "leader", "tracey.ramos@reqres.in",
                "serenity");
        michael.attemptsTo(
                RegisterUser.withInfo(userRegister)
        );
    }

    @Then("^el debe obtener una cuenta virtual para poder ingresar cuando lo requiera$")
    public void elDebeObtenerUnaCuentaVirtualParaPoderIngresarCuandoLoRequiera() {
        michael.should(seeThat("El codigo de respuesta", new ResponseCode(), equalTo(STATUS_CODE_200)));
        UserToken userToken = new GetTokenQuestion().answeredBy(michael);
        michael.should(seeThat("El token es", token -> userToken.getToken(), equalTo("QpwL5tke4Pnpja7X6")));
    }

}


