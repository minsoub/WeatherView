
package com.hist.item.timeweather;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hist.item.weatherlife.WeatherLifeItem;

import java.util.HashMap;
import java.util.Map;


public class TimeWeatherBaseItem {

    @JsonProperty("item")
    private WeatherLifeItem item;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("item")
    public WeatherLifeItem getItem() {
        return item;
    }

    @JsonProperty("item")
    public void setItem(WeatherLifeItem item) {
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
