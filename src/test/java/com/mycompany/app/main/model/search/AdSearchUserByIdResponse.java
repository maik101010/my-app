package com.mycompany.app.main.model.search;

import java.util.HashMap;
import java.util.Map;

public class AdSearchUserByIdResponse {
    private String company;
    private String url;
    private String text;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}