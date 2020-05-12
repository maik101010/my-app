package com.mycompany.app.main.model;

public class UserJsonResponseModel {
    private String id;
    private String first_name;
    private String last_name;
    private String email;

    public UserJsonResponseModel(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public UserJsonResponseModel(String firs_name, String last_name) {
        this.first_name=firs_name;
        this.last_name=last_name;
    }

    public UserJsonResponseModel() {

    }

    public UserJsonResponseModel userJsonResponseModel(){
        return new UserJsonResponseModel();
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }
}
