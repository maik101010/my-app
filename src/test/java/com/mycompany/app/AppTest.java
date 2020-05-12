package com.mycompany.app;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertNotNull;

import com.mycompany.app.main.model.*;
import com.mycompany.app.main.task.GetUserById;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.core.util.EnvironmentVariables;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.mycompany.app.main.facts.UsersData;
import com.mycompany.app.main.questions.GetTokenQuestion;
import com.mycompany.app.main.questions.GetUserUpdateQuestion;
import com.mycompany.app.main.questions.GetUsersQuestion;
import com.mycompany.app.main.questions.ResponseCode;
import com.mycompany.app.main.task.GetUser;
import com.mycompany.app.main.task.RegisterUser;
import com.mycompany.app.main.task.UpdateUser;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(SerenityRunner.class)
public class AppTest {
    private static int STATUS_CODE_200 = 200;
    private EnvironmentVariables environmentVariables;
    private Actor michael;

    @Before
    public void configureBaseUrl() {
        String theRestApiBaseUrl = environmentVariables.optionalProperty("restapi.baseurl")
                .orElse("https://reqres.in/api");
        michael = Actor.named("Michael the trainer").whoCan(CallAnApi.at(theRestApiBaseUrl));
    }

    @Test
    public void find_an_individual_user() {
        michael.attemptsTo(
                Get.resource("/users/1")
        );
        michael.should(
                seeThatResponse("User details should be correct",
                        response -> response.statusCode(200)
                                .body("data.first_name", equalTo("George"))
                                .body("data.last_name", equalTo("Bluth"))
                )
        );
    }

    @Test
    public void retrieve_an_element_from_the_json_structure() {

        michael.attemptsTo(
                GetUserById.withId(1)
        );

        UserJsonResponseModel user = SerenityRest.lastResponse()
                .jsonPath()
                .getObject("data", UserJsonResponseModel.class);

        assertThat(user.getFirstName()).isEqualTo("George");
        assertThat(user.getLastName()).isEqualTo("Bluth");
        assertThat(user.getEmail()).isEqualTo("george.bluth@reqres.in");

    }

    @Test
    public void retrieve_an_list_from_the_json_structure() {
        michael.attemptsTo(
                Get.resource("/users")
        );

        michael.should(
                seeThatResponse("all the expected users should be returned with first name",
                        response -> response.body("data.first_name",
                                hasItems("George", "Janet", "Emma")
                        )
                ),
                seeThatResponse("all the expected users should be returned with email",
                        response -> response.body("data.email",
                                hasItems("george.bluth@reqres.in", "janet.weaver@reqres.in", "emma.wong@reqres.in")
                        )
                )
        );

        List<String> userLastName = SerenityRest.lastResponse().path("data.last_name");
        assertThat(userLastName).contains("Bluth", "Weaver", "Wong");
    }

    @Test
    public void retrieve_an_list_from_the_json_structure_to_object() {
        michael.attemptsTo(
                Get.resource("/users")
        );
        List<UserJsonResponseModel> users = SerenityRest.lastResponse()
                .jsonPath()
                .getList("data", UserJsonResponseModel.class);
        assertThat(users).hasSize(6);
    }

    @Test
    public void getUsersTest() {
        michael.attemptsTo(GetUser.fromPage(1));

        michael.should(seeThat("el código de respuesta", ResponseCode.was(), equalTo(STATUS_CODE_200)));
        Datum datumResult = new GetUsersQuestion().answeredBy(michael).getData().stream()
                .filter(datum -> datum.getId() == 1).findFirst().orElse(null);
        michael.should(seeThat("usuario no es nulo", act -> datumResult, notNullValue()));
        michael.should(
                seeThat("El email del usuario", user -> datumResult.getEmail(), equalTo("george.bluth@reqres.in")),
                seeThat("El avatar del usuario", user -> datumResult.getAvatar(),
                        equalTo("https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg")));
    }

    @Test
    public void create_user_test() {
        UserJsonResponseModel newUser = new UserJsonResponseModel("Sarah-Jane", "Smith");
        michael.attemptsTo(
                Post.to("/users")
                        .with(request -> request.contentType(ContentType.JSON)
                                .body(newUser)
                        )
        );

        michael.should(
                seeThatResponse("The user should have been successfully added",
                        response -> response.statusCode(201))
        );
    }

    @Test
    public void registerUserTest() {
        michael.attemptsTo(RegisterUser.with()
                .withName("morpheus")
                .withJob("leader")
                .withEmail("tracey.ramos@reqres.in")
                .withPassword("serenity")
                .rememberMe()
        );
        michael.should(seeThat("El codigo de respuesta", new ResponseCode(), equalTo(STATUS_CODE_200)));
        UserToken userToken = new GetTokenQuestion().answeredBy(michael);
        michael.should(seeThat("El token es", token -> userToken.getToken(), equalTo("QpwL5tke4Pnpja7X6")));

    }

    @Test
    public void updateUserTest() {
        UserRegisterUpdate userRegisterUpdate = new UserRegisterUpdate("morpheus", "zion resident");
        michael.attemptsTo(UpdateUser.withInfo(userRegisterUpdate));
        michael.should(seeThat("El codigo de respuesta", new ResponseCode(), equalTo(STATUS_CODE_200)));
        UserResponseUpdate responseUpdate = new GetUserUpdateQuestion().answeredBy(michael);
        michael.should(seeThat("El token es", token -> responseUpdate.getJob(), equalTo(userRegisterUpdate.getJob())));
    }

    @Test
    public void factTest() {
        michael.has(UsersData.toViewUsers(1));
        assertNotNull(UsersData.toViewUsers(1));
    }

//	@Test
//	public void dataBaseConnectionTest(){
//		DatabaseConnectionInfo connectionInfo = DatabaseConnectionInfo
//				.builder()
//				.username("root")
//				.databaseType(DatabaseType.MYSQL)
//				.url("jdbc:mysql://localhost/test_automation")
//				.password("my-secret-pw")
//				.entityNames(Stream.of(Example.class)
//				.map(Class::getName)
//				.collect(Collectors.toList()))
//				.build();
//		Actor michael = Actor.named("michael");
//		// Damos la habilidad al actor
//		michael.can(InteractWithDb.using(connectionInfo));
//
//		EntityManager entityManager = InteractWithDb.as(michael).getManager();
//		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Example> criteriaQuery =criteriaBuilder.createQuery(Example.class);
//		Root<Example> exampleRoot = criteriaQuery.from(Example.class);
//		Example exampleResult = entityManager.createQuery(criteriaQuery.select(exampleRoot)).getSingleResult();
//		System.out.println(exampleResult);
////		Assert.assertNotNull(exampleResult);
//	}

}
