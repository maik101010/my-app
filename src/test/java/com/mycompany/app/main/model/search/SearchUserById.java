package com.mycompany.app.main.model.search;
import com.mycompany.app.main.task.SearchUser;
import net.serenitybdd.screenplay.Performable;

public class SearchUserById {

    public SearchUserById() {
    }

    public SearchUserById(int id) {
        this.id = id;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SearchUserById withId(int id) {
        this.id = id;
        return this;
    }

    public Performable rememberMe() {
        return new SearchUser(new SearchUserById(id));
    }

}
