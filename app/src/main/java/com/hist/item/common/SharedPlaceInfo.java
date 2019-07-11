package com.hist.item.common;

public class SharedPlaceInfo {

    private String placeName;
    private String placeCode;

    public SharedPlaceInfo(String placeCode, String placeName) {
        this.placeCode = placeCode;
        this.placeName = placeName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }
}
