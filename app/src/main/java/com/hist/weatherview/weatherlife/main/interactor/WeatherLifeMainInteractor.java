package com.hist.weatherview.weatherlife.main.interactor;

import com.hist.item.weatherlife.WeatherLifeBase;
import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.item.weeklyweather.WeeklyWeatherBase;
import com.hist.item.weeklyweather.dto.WeeklyWeatherDto;

/**
 * 주간 날씨 정보 예보 정보 인터렉터 인터페이스
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */

public interface WeatherLifeMainInteractor {


    void setWeatherLifeBase(WeatherLifeBase weatherLifeBase);

    WeatherLifeBase getWeatherLifeBase();



    void getWeatherLifeFsnLifeListByAreaAndDate(String area, String date);

    void getWeatherLifeSensorytemLifeListByAreaAndDate(String area, String date);

    void getWeatherLifeHeatLifeListByAreaAndDate(String area, String date);

    void setWeatherLifeRepository();

    // 모든 생활 지수 조회
    void getWeatherLifeAllLifeListByAreaAndDate(String area, String date);
}
