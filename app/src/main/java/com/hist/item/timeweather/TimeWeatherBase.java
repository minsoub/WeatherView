/*
 * Copyright (c) 2015. Tyler McCraw
 */

package com.hist.item.timeweather;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hist.item.weatherlife.WeatherLifeBaseData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeWeatherBase {

    @JsonProperty("code")
    private Integer code;
    @JsonProperty("data")
    private TimeWeatherBaseData data;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("code")
    public Integer getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(Integer code) {
        this.code = code;
    }

    @JsonProperty("data")
    public TimeWeatherBaseData getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(TimeWeatherBaseData data) {
        this.data = data;
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
