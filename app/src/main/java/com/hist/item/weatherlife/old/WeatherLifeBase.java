package com.hist.item.weatherlife.old;

import java.util.ArrayList;

/***
 *  Author : JJW
 *  Date : 20180208
 *  Desc : 생활 기상 관련 아이템 Base 클래스
 */

public abstract class WeatherLifeBase {
    public WeatherLifeType weatherLifeType;
    public String code;
    public String areaNo;
    public String date;

    public WeatherLifeBase() {

    }

    public WeatherLifeBase(WeatherLifeType weatherLifeType)
    {
        this.weatherLifeType = weatherLifeType;
    }

    public WeatherLifeBase(WeatherLifeType weatherLifeType, String code, String areaNo, String date) {
        this.weatherLifeType = weatherLifeType;
        this.code = code;
        this.areaNo = areaNo;
        this.date = date;
    }

    public WeatherLifeType getWeatherLifeType() {
        return weatherLifeType;
    }

    public void setWeatherLifeType(WeatherLifeType weatherLifeType) {
        this.weatherLifeType = weatherLifeType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // 추상 메서드화
    public abstract ArrayList<WeatherLifeItem> GetWeatherLifeItems();
}
