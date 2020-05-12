package com.mycompany.app.main.stepdefinitions;

import com.mycompany.app.main.enums.StatusCode;
import com.mycompany.app.main.model.Employe;
import com.mycompany.app.main.questions.ResponseCode;
import com.mycompany.app.main.task.FindEmployes;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class FindEmployesStepDefinitions {

    private String theRestApiBaseUrl;
    private EnvironmentVariables environmentVariables;
    private Actor michael;
    List<Employe> employedList;

    public FindEmployesStepDefinitions() {
    }

    @Before
    public void setTheStage() {
        theRestApiBaseUrl = environmentVariables.optionalProperty("restapi.baseurl").get();
//                .orElse("https://reqres.in/api");
    }

    @Given("^(.*) es un cliente que quiere buscar informacion de los empleados$")
    public void michael_es_un_cliente_que_quiere_buscar_informacion_de_los_empleados(String actor) {
        michael = Actor.named(actor+" the train").whoCan(CallAnApi.at(theRestApiBaseUrl));
    }

    @When("^el intenta buscar los empleados$")
    public void el_intenta_buscar_los_empleados() {
        michael.attemptsTo(
                FindEmployes.fromPage()
        );
    }

    @Then("^el deberia obtener (\\d+) empleados$")
    public void el_deberia_obtener_empleados(int sizeEmployes) {
        michael.should(seeThat("El codigo de respuesta", new ResponseCode(), equalTo(StatusCode.STATUS_CODE_200.getStatusCode())));
        employedList = SerenityRest.lastResponse()
                .jsonPath()
                .getList("data", Employe.class);
        michael.should(seeThat("El largo de la lista es", largo -> employedList.size(), equalTo(sizeEmployes)));

    }

    @Then("^el primer nombre de la lista debe ser (.*)$")
    public void el_primer_nombre_de_la_lista_debe_ser_cerulean(String firstName) {
        michael.should(seeThat("El primer nombre en la lista es ", name -> employedList.stream().findFirst().get().getName(), equalTo(firstName)));
    }

}
