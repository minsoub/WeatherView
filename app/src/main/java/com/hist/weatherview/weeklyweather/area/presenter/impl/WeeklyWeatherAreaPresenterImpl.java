package com.hist.weatherview.weeklyweather.area.presenter.impl;


import com.hist.weatherview.weeklyweather.area.presenter.WeeklyWeatherAreaPresenter;
import com.hist.weatherview.weeklyweather.area.view.WeeklyWeatherAreaView;

/**
 * 주간 날씨 정보 지역설정 베이스 프레젠터 구현 클래스
 * Author:JJW
 * Date: 2018.03.29
 * Remark:
 */

public class WeeklyWeatherAreaPresenterImpl implements WeeklyWeatherAreaPresenter {

    private WeeklyWeatherAreaView weeklyWeatherAreaView;

    public WeeklyWeatherAreaPresenterImpl(WeeklyWeatherAreaView weeklyWeatherAreaView){
        this.weeklyWeatherAreaView = weeklyWeatherAreaView;
    }


    //view init
    @Override
    public void init() {
        //db 가져오는 등 절차..
        weeklyWeatherAreaView.init();
        weeklyWeatherAreaView.setToolbarLayout();
        weeklyWeatherAreaView.showToolbarTitle("위치찾기");

    }

    @Override
    public void onCreateView() {

    }

}
