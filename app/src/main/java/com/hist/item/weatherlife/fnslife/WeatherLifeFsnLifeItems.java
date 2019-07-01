package com.hist.item.weatherlife.fnslife;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "code",
        "areaNo",
        "date",
        "today",
        "tomorrow",
        "theDayAfterTomorrow"
})
public class WeatherLifeFsnLifeItems {
    @JsonProperty("code")
    private String code;
    @JsonProperty("areaNo")
    private String areaNo;
    @JsonProperty("date")
    private String date;
    @JsonProperty("today")
    private String today;
    @JsonProperty("tomorrow")
    private String tomorrow;
    @JsonProperty("theDayAfterTomorrow")
    private String theDayAfterTomorrow;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("areaNo")
    public String getAreaNo() {
        return areaNo;
    }

    @JsonProperty("areaNo")
    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("today")
    public String getToday() {
        return today;
    }

    @JsonProperty("today")
    public void setToday(String today) {
        this.today = today;
    }

    @JsonProperty("tomorrow")
    public String getTomorrow() {
        return tomorrow;
    }

    @JsonProperty("tomorrow")
    public void setTomorrow(String tomorrow) {
        this.tomorrow = tomorrow;
    }

    @JsonProperty("theDayAfterTomorrow")
    public String getTheDayAfterTomorrow() {
        return theDayAfterTomorrow;
    }

    @JsonProperty("theDayAfterTomorrow")
    public void setTheDayAfterTomorrow(String theDayAfterTomorrow) {
        this.theDayAfterTomorrow = theDayAfterTomorrow;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public ArrayList<String> getType(){
        ArrayList<String> list = new ArrayList<String>();
        list.add("오늘");
        list.add("내일");
        list.add("모레");

        return list;
    }

    public ArrayList<String> getValue() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(today);
        list.add(this.tomorrow);
        list.add(this.theDayAfterTomorrow);

        return list;
    }

}
