package com.hist.item.weatherlife;

import java.util.ArrayList;

/***
 *  Author : JJW
 *  Date : 20180208
 *  Desc : 생활 기상 관련 아이템 중 오늘, 내일, 모레 정보를 저장하는 WeatherLifeItem 관리 클래스
 */
public class WeatherLifeItemType1 extends WeatherLifeBase{

    private String today;
    private String tomorrow;
    private String todayAfterTomorrow;
    private ArrayList<WeatherLifeItem> weatherLifeItems;
    private String [] dummy;

    public WeatherLifeItemType1(WeatherLifeType weatherLifeType, String code, String areaNo, String date, String today, String tomorrow, String todayAfterTomorrow) {
        super(weatherLifeType, code, areaNo, date);
        this.today = today;
        this.tomorrow = tomorrow;
        this.todayAfterTomorrow = todayAfterTomorrow;
    }


    @Override
    public ArrayList<WeatherLifeItem> GetWeatherLifeItems() {
        // 현재 가상 데이터 사용

        WeatherLifeItem s1 = new WeatherLifeItem(today, "오늘 블라블라 입니다.", weatherLifeType);
        WeatherLifeItem s2 = new WeatherLifeItem(tomorrow, "내일은 블라블라 입니다.", weatherLifeType);
        WeatherLifeItem s3 = new WeatherLifeItem(todayAfterTomorrow, "모레는 블라블라 입니다.", weatherLifeType);

        weatherLifeItems = new ArrayList<>();
        weatherLifeItems.add(s1);
        weatherLifeItems.add(s2);
        weatherLifeItems.add(s3);

        return weatherLifeItems;
    }
}
