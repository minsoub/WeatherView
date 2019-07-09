package com.hist.item.PlaceInfo;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hist.item.timeweather.TimeWeatherResultTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaceInfoResult {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("create_user_name")
    private String createUserName;
    @JsonProperty("created_time")
    private String createdTime;
    @JsonProperty("del_yn")
    private String delYn;
    @JsonProperty("update_user_name")
    private String updateUserName;
    @JsonProperty("updated_time")
    private String updatedTime;
    @JsonProperty("use_yn")
    private String useYn;
    @JsonProperty("lat")
    private Double lat;
    @JsonProperty("lat_hour")
    private String latHour;
    @JsonProperty("lat_min")
    private String latMin;
    @JsonProperty("lat_sec")
    private String latSec;
    @JsonProperty("lon")
    private Double lon;
    @JsonProperty("lon_hour")
    private String lonHour;
    @JsonProperty("lon_min")
    private String lonMin;
    @JsonProperty("lon_sec")
    private String lonSec;
    @JsonProperty("nx")
    private String nx;
    @JsonProperty("ny")
    private String ny;
    @JsonProperty("place_distict_code_id")
    private Integer placeDistictCodeId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("create_user_name")
    public String getCreateUserName() {
        return createUserName;
    }

    @JsonProperty("create_user_name")
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    @JsonProperty("created_time")
    public String getCreatedTime() {
        return createdTime;
    }

    @JsonProperty("created_time")
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    @JsonProperty("del_yn")
    public String getDelYn() {
        return delYn;
    }

    @JsonProperty("del_yn")
    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }

    @JsonProperty("update_user_name")
    public String getUpdateUserName() {
        return updateUserName;
    }

    @JsonProperty("update_user_name")
    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    @JsonProperty("updated_time")
    public String getUpdatedTime() {
        return updatedTime;
    }

    @JsonProperty("updated_time")
    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    @JsonProperty("use_yn")
    public String getUseYn() {
        return useYn;
    }

    @JsonProperty("use_yn")
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    @JsonProperty("lat")
    public Double getLat() {
        return lat;
    }

    @JsonProperty("lat")
    public void setLat(Double lat) {
        this.lat = lat;
    }

    @JsonProperty("lat_hour")
    public String getLatHour() {
        return latHour;
    }

    @JsonProperty("lat_hour")
    public void setLatHour(String latHour) {
        this.latHour = latHour;
    }

    @JsonProperty("lat_min")
    public String getLatMin() {
        return latMin;
    }

    @JsonProperty("lat_min")
    public void setLatMin(String latMin) {
        this.latMin = latMin;
    }

    @JsonProperty("lat_sec")
    public String getLatSec() {
        return latSec;
    }

    @JsonProperty("lat_sec")
    public void setLatSec(String latSec) {
        this.latSec = latSec;
    }

    @JsonProperty("lon")
    public Double getLon() {
        return lon;
    }

    @JsonProperty("lon")
    public void setLon(Double lon) {
        this.lon = lon;
    }

    @JsonProperty("lon_hour")
    public String getLonHour() {
        return lonHour;
    }

    @JsonProperty("lon_hour")
    public void setLonHour(String lonHour) {
        this.lonHour = lonHour;
    }

    @JsonProperty("lon_min")
    public String getLonMin() {
        return lonMin;
    }

    @JsonProperty("lon_min")
    public void setLonMin(String lonMin) {
        this.lonMin = lonMin;
    }

    @JsonProperty("lon_sec")
    public String getLonSec() {
        return lonSec;
    }

    @JsonProperty("lon_sec")
    public void setLonSec(String lonSec) {
        this.lonSec = lonSec;
    }

    @JsonProperty("nx")
    public String getNx() {
        return nx;
    }

    @JsonProperty("nx")
    public void setNx(String nx) {
        this.nx = nx;
    }

    @JsonProperty("ny")
    public String getNy() {
        return ny;
    }

    @JsonProperty("ny")
    public void setNy(String ny) {
        this.ny = ny;
    }

    @JsonProperty("place_distict_code_id")
    public Integer getPlaceDistictCodeId() {
        return placeDistictCodeId;
    }

    @JsonProperty("place_distict_code_id")
    public void setPlaceDistictCodeId(Integer placeDistictCodeId) {
        this.placeDistictCodeId = placeDistictCodeId;
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
