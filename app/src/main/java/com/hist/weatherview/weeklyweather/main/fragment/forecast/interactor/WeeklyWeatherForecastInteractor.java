package com.hist.weatherview.weeklyweather.main.fragment.forecast.interactor;

import com.hist.item.weeklyweather.WeeklyWeatherBase;
import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.item.weeklyweather.dto.WeeklyWeatherDto;

/**
 * 주간 날씨 정보 예보 정보 인터렉터 인터페이스
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */

public interface WeeklyWeatherForecastInteractor {


    void setWeeklyWeatherBaseList(WeeklyWeatherBase weeklyWeatherBaseList);

    WeeklyWeatherBase getWeeklyWeatherBaseList();

    void setWeeklyWeatherRepository(WeeklyWeatherArea area);

    void getWeeklyWeather(String day, String time, String nx, String ny);

    void setWeeklyWeather(WeeklyWeatherBase weeklyWeatherBase);

    void setWeeklyWeatherRepository(String accessToken);
    WeeklyWeatherArea getWeeklyWeatherArea();

    void getWeeklyWeatherByAreaID(WeeklyWeatherArea area);

    void setWeeklyWeatherRepository();

    void getWeeklyWeatherAll(String time, String landRegId, String tempRegId, String stnId);

    void getPlaceInfoByAreaCode(String areaCode);           // 지역정보 상세 조회
}
