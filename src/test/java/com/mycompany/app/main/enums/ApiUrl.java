package com.mycompany.app.main.enums;

public enum ApiUrl {
    API_URL("https://reqres.in/api");
    String api;
    ApiUrl(String api) {
        this.api = api;
    }
    public String getApi() {
        return api;
    }
}
