package com.hist.item.timeweather;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  실제 정보 저장 아이템
 */
public class TimeWeatherResultTime {

    @JsonProperty("baseDate")
    private Integer baseDate;
    @JsonProperty("baseTime")
    private String baseTime;
    @JsonProperty("category")
    private String category;
    @JsonProperty("fcstDate")
    private Integer fcstDate;
    @JsonProperty("fcstTime")
    private String fcstTime;
    @JsonProperty("fcstValue")
    private Double fcstValue;
    @JsonProperty("nx")
    private Integer nx;
    @JsonProperty("ny")
    private Integer ny;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("baseDate")
    public Integer getBaseDate() {
        return baseDate;
    }

    @JsonProperty("baseDate")
    public void setBaseDate(Integer baseDate) {
        this.baseDate = baseDate;
    }

    @JsonProperty("baseTime")
    public String getBaseTime() {
        return baseTime;
    }

    @JsonProperty("baseTime")
    public void setBaseTime(String baseTime) {
        this.baseTime = baseTime;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("fcstDate")
    public Integer getFcstDate() {
        return fcstDate;
    }

    @JsonProperty("fcstDate")
    public void setFcstDate(Integer fcstDate) {
        this.fcstDate = fcstDate;
    }

    @JsonProperty("fcstTime")
    public String getFcstTime() {
        return fcstTime;
    }

    @JsonProperty("fcstTime")
    public void setFcstTime(String fcstTime) {
        this.fcstTime = fcstTime;
    }

    @JsonProperty("fcstValue")
    public Double getFcstValue() {
        return fcstValue;
    }

    @JsonProperty("fcstValue")
    public void setFcstValue(Double fcstValue) {
        this.fcstValue = fcstValue;
    }

    @JsonProperty("nx")
    public Integer getNx() {
        return nx;
    }

    @JsonProperty("nx")
    public void setNx(Integer nx) {
        this.nx = nx;
    }

    @JsonProperty("ny")
    public Integer getNy() {
        return ny;
    }

    @JsonProperty("ny")
    public void setNy(Integer ny) {
        this.ny = ny;
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
