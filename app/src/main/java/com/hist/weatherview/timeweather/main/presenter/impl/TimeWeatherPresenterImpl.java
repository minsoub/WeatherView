package com.hist.weatherview.timeweather.main.presenter.impl;


import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.weatherview.timeweather.main.presenter.TimeWeatherPresenter;
import com.hist.weatherview.timeweather.main.view.TimeWeatherView;
import com.hist.weatherview.weeklyweather.main.presenter.WeeklyWeatherPresenter;

/**
 * 시간별 날씨 정보 예보 정보 베이스 프레젠터 인터페이스 구현
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */

public class TimeWeatherPresenterImpl implements TimeWeatherPresenter{

    private TimeWeatherView timeWeatherView;

    public TimeWeatherPresenterImpl(TimeWeatherView timeWeatherView){
        this.timeWeatherView = timeWeatherView;
    }


    //view init
    @Override
    public void init() {
        //db 가져오는 등 절차..

        timeWeatherView.init();
        timeWeatherView.setToolbarLayout();
        timeWeatherView.showToolbarTitle("시간별날씨정보");

    }

    @Override
    public void onCreateView() {

    }

    @Override
    public void onActivityResultTimeWeatherFavoriteAreaResultOK(WeeklyWeatherArea weeklyWeather, int position) {
        // 즐겨 찾기 결과 후
        // 1. 디비를 호출 후 결과를 받아 온다.
        this.timeWeatherView.weeklyWeatherAreaChanged(weeklyWeather);
    }

    @Override
    public void onActivityResultTimeWeatherSearchAreaResultOK(WeeklyWeatherArea weeklyWeather, int position) {
        // 장소 설정 결과 후 Fragment로 넘긴다.
        this.timeWeatherView.weeklyWeatherAreaChanged(weeklyWeather);
    }

    @Override
    public void onActivityResultForTimeWeatherAreaResultOk(String areaCode, String areaName) {
        this.timeWeatherView.getTimeWeatherForecastByAreaAndDate(areaCode, "2019061206" );
    }

}
