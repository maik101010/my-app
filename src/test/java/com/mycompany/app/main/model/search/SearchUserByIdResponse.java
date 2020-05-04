package com.mycompany.app.main.model.search;

import java.util.HashMap;
import java.util.Map;

public class SearchUserByIdResponse {

    private DataSearchUserByIdResponse data;
    private AdSearchUserByIdResponse ad;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public DataSearchUserByIdResponse getData() {
        return data;
    }

    public void setData(DataSearchUserByIdResponse data) {
        this.data = data;
    }

    public AdSearchUserByIdResponse getAd() {
        return ad;
    }

    public void setAd(AdSearchUserByIdResponse ad) {
        this.ad = ad;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}