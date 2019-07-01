package com.hist.item.weatherlife;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hist.item.common.Header;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WeatherLifeItem {


    @JsonProperty("type")
    private String type;
    @JsonProperty("header")
    private Header header;
    @JsonProperty("result")
    private List<WeatherLifeResult> result = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("header")
    public Header getHeader() {
        return header;
    }

    @JsonProperty("header")
    public void setHeader(Header header) {
        this.header = header;
    }

    @JsonProperty("result")
    public List<WeatherLifeResult> getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(List<WeatherLifeResult> result) {
        this.result = result;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
