package com.hist.item.weatherlife.old;

import java.util.ArrayList;

import static com.hist.item.weatherlife.old.WeatherLifeType.FOOD_POISONING;
import static com.hist.item.weatherlife.old.WeatherLifeType.UV_INDEX;

/***
 *  Author : JJW
 *  Date : 20180208
 *  Desc : 생활 기상 관련 아이템 중 오늘, 내일, 모레 정보를 저장하는 WeatherLifeItem 관리 클래스
 */
public class WeatherLifeItemType1 extends WeatherLifeBase {

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

        String todayText = "";
        String tomorrowText = "";
        String todayAfterTomorrowText = "";

        WeatherLifeType weatherLifeType = getWeatherLifeType();
        if(weatherLifeType == FOOD_POISONING)
        {
            todayText = "오늘\n3~4시간내 부패\n음식물 취급 극히주의\n식중독 위험";
            tomorrowText = "내일\n3~4시간내 부패\n음식물 취급 극히주의\n식중독 위험";
            todayAfterTomorrowText = "모레\n4~6시간내 부패\n조리시설 취급 주의\n식중독 경고";
        }else if(weatherLifeType == UV_INDEX)
        {
            todayText = "오늘\n햇볓에 노출 시 수십분 이내에도 피부 화상을 입을 수 있어 가장 위험하며 가능한 실내에 머물러야 함";
            tomorrowText = "내일\n햇볓에 노출 시 수십분 이내에도 피부 화상을 입을 수 있어 가장 위험하며 가능한 실내에 머물러야 함";
            todayAfterTomorrowText = "모레\n햇볓에 노출 시 수십분 이내에도 피부 화상을 입을 수 있어 가장 위험하며 가능한 실내에 머물러야 함";
        }

        WeatherLifeItem s1 = new WeatherLifeItem(today, todayText, weatherLifeType);
        WeatherLifeItem s2 = new WeatherLifeItem(tomorrow, tomorrowText, weatherLifeType);
        WeatherLifeItem s3 = new WeatherLifeItem(todayAfterTomorrow, todayAfterTomorrowText, weatherLifeType);

        weatherLifeItems = new ArrayList<>();
        weatherLifeItems.add(s1);
        weatherLifeItems.add(s2);
        weatherLifeItems.add(s3);

        return weatherLifeItems;
    }
}
