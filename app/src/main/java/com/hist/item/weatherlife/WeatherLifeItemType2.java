package com.hist.item.weatherlife;

import java.util.ArrayList;

/***
 *  Author : JJW
 *  Date : 20180208
 *  Desc : 생활 기상 관련 아이템 중 3시간 단위로 저장하는 저장하는 WeatherLifeItem 관리 클래스
 */
public class WeatherLifeItemType2 extends WeatherLifeBase {

    ArrayList<WeatherLifePredictionItem> weatherLifePredictionItemArrayList;
    private ArrayList<WeatherLifeItem> weatherLifeItems;

    public WeatherLifeItemType2(WeatherLifeType weatherLifeType, ArrayList<WeatherLifePredictionItem> weatherLifePredictionItemArrayList) {
        super(weatherLifeType);
        this.weatherLifePredictionItemArrayList = weatherLifePredictionItemArrayList;
    }

    @Override
    public ArrayList<WeatherLifeItem> GetWeatherLifeItems() {

        //테스트용
        WeatherLifeItem s1 = new WeatherLifeItem(weatherLifePredictionItemArrayList.get(0).getPreductionTimeValue(), "h1 블라블라입니다.", weatherLifeType);
        WeatherLifeItem s2 = new WeatherLifeItem(weatherLifePredictionItemArrayList.get(1).getPreductionTimeValue(), "h2 블라블라입니다.", weatherLifeType);
        WeatherLifeItem s3 = new WeatherLifeItem(weatherLifePredictionItemArrayList.get(2).getPreductionTimeValue(), "h3 블라블라입니다.", weatherLifeType);
        WeatherLifeItem s4 = new WeatherLifeItem(weatherLifePredictionItemArrayList.get(3).getPreductionTimeValue(), "h4 블라블라입니다.", weatherLifeType);
        WeatherLifeItem s5 = new WeatherLifeItem(weatherLifePredictionItemArrayList.get(4).getPreductionTimeValue(), "h4 블라블라입니다.", weatherLifeType);

        weatherLifeItems = new ArrayList<>();
        weatherLifeItems.add(s1);
        weatherLifeItems.add(s2);
        weatherLifeItems.add(s3);
        weatherLifeItems.add(s4);
        weatherLifeItems.add(s5);

        return weatherLifeItems;
    }
}
