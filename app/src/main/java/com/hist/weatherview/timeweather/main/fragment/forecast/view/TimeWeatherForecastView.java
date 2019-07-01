package com.hist.weatherview.timeweather.main.fragment.forecast.view;

import com.hist.item.timeweather.TimeWeatherBase;
import com.hist.item.timeweather.TimeWeatherResult;
import com.hist.item.weeklyweather.WeeklyWeatherBase;
import com.hist.item.weeklyweather.WeeklyWeatherData;

import java.util.List;

/**
 * 시간별 날씨 정보 예보 정보 뷰 인터페이스
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */
public interface TimeWeatherForecastView {

    void showProgressDialog();

    void goneProgressDialog();

    void init();

    void showMessage(String message);

    //void setWeeklyWeatherListRecycleViewAdapterItem(List<WeeklyWeatherBase> weeklyWeatherList);

    void setTimeWeatherToday(TimeWeatherResult today);       //오늘 날짜 정보 설정

    void clearTimeWeatherAdapter();   // 어뎁터 설정 초기화

    void showEmptyTimeWeatherView();  // 빈 리스트뷰 현시

    void setTimeWeatherListRecycleViewAdapterItem(List<TimeWeatherResult> data);
}
