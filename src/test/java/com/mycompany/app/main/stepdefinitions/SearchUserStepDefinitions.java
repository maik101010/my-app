package com.mycompany.app.main.stepdefinitions;

import com.mycompany.app.main.enums.ApiUrl;
import com.mycompany.app.main.enums.StatusCode;
import com.mycompany.app.main.model.UserToken;
import com.mycompany.app.main.model.search.SearchUserById;
import com.mycompany.app.main.model.search.SearchUserByIdResponse;
import com.mycompany.app.main.questions.GetTokenQuestion;
import com.mycompany.app.main.questions.ResponseCode;
import com.mycompany.app.main.questions.SearchUserQuestion;
import com.mycompany.app.main.task.SearchUser;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class SearchUserStepDefinitions {
    Actor actor;
//    @Given("^(.*) es un cliente que quiere poder ver información de un usuario$")
//    public void es_un_cliente_que_quiere_poder_ver_información_de_un_usuario(String name) {
//        actor = new Actor(String.format("%s, el trainer", name));
//        actor.whoCan(CallAnApi.at(ApiUrl.API_URL.getApi()));
//    }

    @Given("^Michael es un cliente que quiere poder ver información de un usuario$")
    public void michael_es_un_cliente_que_quiere_poder_ver_información_de_un_usuario() {
        actor = new Actor("Michael trainer");
        actor.whoCan(CallAnApi.at(ApiUrl.API_URL.getApi()));
    }

    @When("^el envia la informacion requerida para la busqueda$")
    public void el_envia_la_informacion_requerida_para_la_busqueda() {
        actor.attemptsTo(
                SearchUser
                        .with()
                        .withId(2)
                        .rememberMe()
        );
    }

    @Then("^el debe obtener unos datos de un usuario que correspondan a la informacion requerida$")
    public void el_debe_obtener_unos_datos_de_un_usuario_que_correspondan_a_la_informacion_requerida() {
        actor.should(seeThat("El codigo de respuesta", new ResponseCode(), equalTo(StatusCode.STATUS_CODE_200.getStatusCode())));
        SearchUserByIdResponse userByIdResponse = new SearchUserQuestion().answeredBy(actor);
        actor.should(seeThat("El email ",
                data -> userByIdResponse.getData().getEmail(), equalTo("janet.weaver@reqres.in")));
        actor.should(seeThat("La compania ",
                data -> userByIdResponse.getAd().getCompany(), equalTo("StatusCode Weekly")));
    }
}
