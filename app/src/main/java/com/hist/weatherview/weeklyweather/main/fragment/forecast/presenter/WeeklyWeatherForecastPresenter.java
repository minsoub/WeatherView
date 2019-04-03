package com.hist.weatherview.weeklyweather.main.fragment.forecast.presenter;

import com.hist.item.weeklyweather.WeeklyWeather;
import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.repository.util.HttpError;
import com.hist.weatherview.weeklyweather.main.base.WeeklyWeatherActivity;

import java.util.List;

/**
 * 주간 날씨 정보 예보 정보 프레젠터 인터페이스
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */
public interface WeeklyWeatherForecastPresenter {

    void init(WeeklyWeatherArea area);
    void onCreateView();
    //void setGuiderList(List<AreaInfo> guiderList);

    void onNetworkError(HttpError httpError);
    void onClickSearch();

    void onSuccessGetWeeklyWeatherByAreaID(List<WeeklyWeather> weeklyWeathers);
    //void onClickImage(PlaceAttraction guider, int position);

    //void onSuccessGetGuiderList(List<Guider> guiderList);
}
