package com.mycompany.app.main.questions;

import com.mycompany.app.main.model.search.SearchUserByIdResponse;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class SearchUserQuestion implements Question<SearchUserByIdResponse> {
    @Override
    public SearchUserByIdResponse answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(SearchUserByIdResponse.class);
    }
}
