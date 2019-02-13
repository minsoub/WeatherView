package com.hist.item.weatherlife;

public class WeatherLifeArea {
    private String areaCode;
    private String areaName;

    public WeatherLifeArea(String areaCode, String areaName)
    {
        this.areaCode = areaCode;
        this.areaName = areaName;
    }

    @Override
    public String toString() {
        return this.areaName;
    }
}
