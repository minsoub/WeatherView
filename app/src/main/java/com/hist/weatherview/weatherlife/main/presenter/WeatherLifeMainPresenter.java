package com.hist.weatherview.weatherlife.main.presenter;

import com.hist.item.weatherlife.WeatherLifeBase;
import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.item.weeklyweather.WeeklyWeatherBase;
import com.hist.repository.util.HttpError;

/**
 * 주간 날씨 정보 예보 정보 프레젠터 인터페이스
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */
public interface WeatherLifeMainPresenter {

    void init(WeeklyWeatherArea area);
    void init();
    //void setGuiderList(List<AreaInfo> guiderList);

    void onNetworkError(HttpError httpError);
    void onClickSearch();

    void onSuccessGetWeeklyWeatherByAreaID(WeatherLifeBase weatherLifeBase);

    void onSuccessGetWeeklyWeatherByDateAndTimeAndArea(WeatherLifeBase weatherLifeBase);

    void onSuccessGetWeatherLifeFsnLifeListByAreaAndDate(WeatherLifeBase weatherLifeBase);

    void onSuccessGetWeatherLifeSensorytemLifeListByAreaAndDate(WeatherLifeBase weatherLifeBase);

    void onSuccessGetWeatherLifeAllLifeListByAreaAndDate(WeatherLifeBase weatherLifeBase);
    //void onClickImage(PlaceAttraction guider, int position);

    //void onSuccessGetGuiderList(List<Guider> guiderList);

    void onChangeWeatherLifeType(String type);

    void onChangeArea(String area);

    void onClickAreaSearch();

    void onActivityResultForWeatherLifeAreaResultOk(String areaCode, String areaName);
}
