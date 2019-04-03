package com.hist.weatherview.weeklyweather.main.presenter.impl;


import com.hist.item.weeklyweather.WeeklyWeather;
import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.weatherview.weeklyweather.main.presenter.WeeklyWeatherPresenter;
import com.hist.weatherview.weeklyweather.main.view.WeeklyWeatherView;

import java.util.ArrayList;
import java.util.List;

/**
 * 주간 날씨 정보 예보 정보 베이스 프레젠터 인터페이스 구현
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */

public class WeeklyWeatherPresenterImpl implements WeeklyWeatherPresenter{

    private WeeklyWeatherView weeklyWeatherView;

    public WeeklyWeatherPresenterImpl(WeeklyWeatherView weeklyWeatherView){
        this.weeklyWeatherView = weeklyWeatherView;
    }


    //view init
    @Override
    public void init() {
        //db 가져오는 등 절차..

        weeklyWeatherView.init();
        weeklyWeatherView.setToolbarLayout();
        weeklyWeatherView.showToolbarTitle("주간날씨정보");

    }

    @Override
    public void onCreateView() {

    }

    @Override
    public void onActivityResultWeeklyWeatherFavoriteAreaResultOK(WeeklyWeatherArea weeklyWeather, int position) {
        // 즐겨 찾기 결과 후
        // 1. 디비를 호출 후 결과를 받아 온다.
        this.weeklyWeatherView.weeklyWeatherAreaChanged(weeklyWeather);
    }

    @Override
    public void onActivityResultWeeklyWeatherSearchAreaResultOK(WeeklyWeatherArea weeklyWeather, int position) {
        // 장소 설정 결과 후 Fragment로 넘긴다.
        this.weeklyWeatherView.weeklyWeatherAreaChanged(weeklyWeather);
    }

}
