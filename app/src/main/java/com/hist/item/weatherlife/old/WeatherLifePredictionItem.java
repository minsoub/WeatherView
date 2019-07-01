package com.hist.item.weatherlife.old;


/***
 *  Author : JJW
 *  Date : 20180208
 *  Desc : 생활 기상 관련 아이템 중 3시간 단위로 저장하는 시간별 값 아이템 클래스
 */
public class WeatherLifePredictionItem {
    public String predictionTime;
    public String preductionTimeValue;

    public WeatherLifePredictionItem(String predictionTime, String preductionTimeValue) {
        this.predictionTime = predictionTime;
        this.preductionTimeValue = preductionTimeValue;
    }

    public String getPredictionTime() {
        return predictionTime;
    }

    public void setPredictionTime(String predictionTime) {
        this.predictionTime = predictionTime;
    }

    public String getPreductionTimeValue() {
        return preductionTimeValue;
    }

    public void setPreductionTimeValue(String preductionTimeValue) {
        this.preductionTimeValue = preductionTimeValue;
    }
}
