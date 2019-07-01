package com.hist.weatherview.weeklyweather.main.fragment.forecast.view;

import com.hist.item.weeklyweather.WeeklyWeatherBaseItem;
import com.hist.item.weeklyweather.WeeklyWeatherBase;

import java.util.List;

/**
 * 주간 날씨 정보 예보 정보 뷰 인터페이스
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */
public interface WeeklyWeatherForecastView {

    void showProgressDialog();

    void goneProgressDialog();

    void init();

    void showMessage(String message);

    //void setWeeklyWeatherListRecycleViewAdapterItem(List<WeeklyWeatherBase> weeklyWeatherList);

    void setWeeklyWeatherToday(WeeklyWeatherBase today);       //오늘 날짜 정보 설정

    void clearWeeklyWeatherAdapter();   // 어뎁터 설정 초기화

    void showEmptyWeeklyWeatherView();  // 빈 리스트뷰 현시

    void setWeeklyWeatherListRecycleViewAdapterItem(List<WeeklyWeatherBaseItem> data);

    // 중기 예보 설정
    void setWeeklyWeatherMiddleForecast(WeeklyWeatherBase weeklyWeathersBase);
}
