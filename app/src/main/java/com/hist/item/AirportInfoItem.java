package com.hist.item;

import android.graphics.drawable.Drawable;

/**
 * 공항정보 아이템을 관리한다.
 */
public class AirportInfoItem {
    private Drawable icon;
    private String icao_cd;
    private String icao_nm;
    private String lat;
    private String lon;

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getIcao_cd() {
        return icao_cd;
    }

    public void setIcao_cd(String icao_cd) {
        this.icao_cd = icao_cd;
    }

    public String getIcao_nm() {
        return icao_nm;
    }

    public void setIcao_nm(String icao_nm) {
        this.icao_nm = icao_nm;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
