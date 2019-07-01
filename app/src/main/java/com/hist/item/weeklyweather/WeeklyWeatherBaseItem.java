
package com.hist.item.weeklyweather;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hist.item.weatherlife.WeatherLifeItem;

import java.util.HashMap;
import java.util.Map;


public class WeeklyWeatherBaseItem {

    @JsonProperty("item")
    private WeeklyWeatherItem item;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("item")
    public WeeklyWeatherItem getItem() {
        return item;
    }

    @JsonProperty("item")
    public void setItem(WeeklyWeatherItem item) {
        this.item = item;
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
