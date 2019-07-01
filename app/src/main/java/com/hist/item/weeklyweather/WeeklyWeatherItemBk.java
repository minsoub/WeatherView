
package com.hist.item.weeklyweather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeeklyWeatherItemBk {

    @SerializedName("baseDate")
    @Expose
    private Integer baseDate;
    @SerializedName("baseTime")
    @Expose
    private String baseTime;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("fcstDate")
    @Expose
    private Integer fcstDate;
    @SerializedName("fcstTime")
    @Expose
    private String fcstTime;
    @SerializedName("fcstValue")
    @Expose
    private Integer fcstValue;
    @SerializedName("nx")
    @Expose
    private Integer nx;
    @SerializedName("ny")
    @Expose
    private Integer ny;

    public Integer getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(Integer baseDate) {
        this.baseDate = baseDate;
    }

    public String getBaseTime() {
        return baseTime;
    }

    public void setBaseTime(String baseTime) {
        this.baseTime = baseTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getFcstDate() {
        return fcstDate;
    }

    public void setFcstDate(Integer fcstDate) {
        this.fcstDate = fcstDate;
    }

    public String getFcstTime() {
        return fcstTime;
    }

    public void setFcstTime(String fcstTime) {
        this.fcstTime = fcstTime;
    }

    public Integer getFcstValue() {
        return fcstValue;
    }

    public void setFcstValue(Integer fcstValue) {
        this.fcstValue = fcstValue;
    }

    public Integer getNx() {
        return nx;
    }

    public void setNx(Integer nx) {
        this.nx = nx;
    }

    public Integer getNy() {
        return ny;
    }

    public void setNy(Integer ny) {
        this.ny = ny;
    }

}
