package com.hist.weatherview.weeklyweather.main.fragment.forecast.interactor;

import com.hist.item.weeklyweather.WeeklyWeather;
import com.hist.item.weeklyweather.WeeklyWeatherArea;
import java.util.List;

/**
 * 주간 날씨 정보 예보 정보 인터렉터 인터페이스
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */

public interface WeeklyWeatherForecastInteractor {

    void setWeeklyWeatherRepository(WeeklyWeatherArea area);

    List<WeeklyWeather> getWeeklyWeather();

    void setWeeklyWeather(List<WeeklyWeather> weeklyWeather);

    void setWeeklyWeatherRepository(String accessToken);
    WeeklyWeatherArea getWeeklyWeatherArea();

    void getWeeklyWeatherByAreaID(WeeklyWeatherArea area);
}
