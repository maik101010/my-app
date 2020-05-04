package com.mycompany.app.main.task;

import com.mycompany.app.main.interactions.Post;
import com.mycompany.app.main.model.UserRegisterUpdate;
import com.mycompany.app.main.model.search.SearchUserById;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class SearchUser implements Task {
    private final SearchUserById searchUserById;

    public SearchUser(SearchUserById searchUserById) {
        this.searchUserById = searchUserById;
    }

    public static SearchUserById with(){
        return new SearchUserById();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Get.resource("/users/"+searchUserById.getId())
                .with(requestSpecification -> requestSpecification.contentType(ContentType.JSON))
        );
    }
}
