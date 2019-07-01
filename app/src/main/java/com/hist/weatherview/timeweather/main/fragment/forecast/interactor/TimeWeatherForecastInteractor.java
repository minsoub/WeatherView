package com.hist.weatherview.timeweather.main.fragment.forecast.interactor;

import com.hist.item.timeweather.TimeWeatherBase;
import com.hist.item.timeweather.TimeWeatherResult;
import com.hist.item.weeklyweather.WeeklyWeatherArea;

import java.util.List;

/**
 * 주간 날씨 정보 예보 정보 인터렉터 인터페이스
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */

public interface TimeWeatherForecastInteractor {


    void setTimeWeatherBaseList(TimeWeatherBase timeWeatherBaseList);

    TimeWeatherBase getTimeWeatherBaseList();

    void setTimeWeatherRepository(WeeklyWeatherArea area);

    void getTimeWeather(String day, String time, String nx, String ny);

    void setTimeWeatherBase(TimeWeatherBase timeWeatherBase);

    void setTimeWeatherRepository(String accessToken);
    WeeklyWeatherArea getTimeWeatherArea();

    void getTimeWeatherByAreaID(WeeklyWeatherArea area);

    void setTimeWeatherRepository();

    List<TimeWeatherResult> getTimeWeatherResult();
}
