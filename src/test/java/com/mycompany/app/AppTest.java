package com.mycompany.app;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.mycompany.app.main.facts.UsersData;
import com.mycompany.app.main.model.Datum;
import com.mycompany.app.main.model.UserRegisterUpdate;
import com.mycompany.app.main.model.UserResponseUpdate;
import com.mycompany.app.main.model.UserToken;
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

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(SerenityRunner.class)
public class AppTest {
    private static String REST_API_URL = "https://reqres.in/api";
    private static int STATUS_CODE_200 = 200;

    @Test
    public void getUsersTest() {
        Actor michael = Actor.named("michael el trainer").whoCan(CallAnApi.at(REST_API_URL));
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
    public void registerUserTest() {
        Actor michael = Actor.named("michael el trainer").whoCan(CallAnApi.at(REST_API_URL));
        UserRegisterUpdate userRegister = new UserRegisterUpdate("morpheus", "morpheus", "tracey.ramos@reqres.in",
                "serenity");
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
        Actor michael = Actor.named("michael el trainer").whoCan(CallAnApi.at(REST_API_URL));
        UserRegisterUpdate userRegisterUpdate = new UserRegisterUpdate("morpheus", "zion resident");
        michael.attemptsTo(UpdateUser.withInfo(userRegisterUpdate));
        michael.should(seeThat("El codigo de respuesta", new ResponseCode(), equalTo(STATUS_CODE_200)));
        UserResponseUpdate responseUpdate = new GetUserUpdateQuestion().answeredBy(michael);
        michael.should(seeThat("El token es", token -> responseUpdate.getJob(), equalTo(userRegisterUpdate.getJob())));
    }

    @Test
    public void factTest() {
        Actor michael = Actor.named("michael el trainer").whoCan(CallAnApi.at(REST_API_URL));
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
