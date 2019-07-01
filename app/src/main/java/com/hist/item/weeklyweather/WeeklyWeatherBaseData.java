
package com.hist.item.weeklyweather;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hist.item.weatherlife.WeatherLifeBaseItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WeeklyWeatherBaseData {

    @JsonProperty("items")
    private List<WeeklyWeatherBaseItem> items = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("items")
    public List<WeeklyWeatherBaseItem> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<WeeklyWeatherBaseItem> items) {
        this.items = items;
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
