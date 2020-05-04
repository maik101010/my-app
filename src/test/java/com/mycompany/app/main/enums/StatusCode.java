package com.mycompany.app.main.enums;

public enum StatusCode {
    STATUS_CODE_200(200),
    STATUS_CODE_400(400);
    int statusCode;
    StatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public int getStatusCode() {
        return statusCode;
    }
}
