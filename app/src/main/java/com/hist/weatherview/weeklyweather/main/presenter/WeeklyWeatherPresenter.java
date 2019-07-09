package com.hist.weatherview.weeklyweather.main.presenter;

import com.hist.item.weeklyweather.WeeklyWeatherArea;

/**
 * 주간 날씨 정보 예보 정보 베이스 프레젠터 인터페이스
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */

public interface WeeklyWeatherPresenter {

    void init();

    void onCreateView();

    // 즐겨찾기 지역 요청 결과 처리
    void onActivityResultWeeklyWeatherFavoriteAreaResultOK(String areaCode, String areaName);

    // 지역 변경 요청 결과 처리
    void onActivityResultWeeklyWeatherSearchAreaResultOK(WeeklyWeatherArea weeklyWeather, int position);

    void onActivityResultForWeatherLifeAreaResultOk(String areaCode, String areaName);
}
