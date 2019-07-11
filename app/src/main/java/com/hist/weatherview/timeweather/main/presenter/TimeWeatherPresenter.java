package com.hist.weatherview.timeweather.main.presenter;

import com.hist.item.weeklyweather.WeeklyWeatherArea;

/**
 * 시간별 날씨 정보 예보 정보 베이스 프레젠터 인터페이스
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */

public interface TimeWeatherPresenter {

    void init();

    void onCreateView();

    // 즐겨찾기 지역 요청 결과 처리
    void onActivityResultTimeWeatherFavoriteAreaResultOK(String areaCode, String areaName);

    // 지역 변경 요청 결과 처리
    void onActivityResultTimeWeatherSearchAreaResultOK(WeeklyWeatherArea weeklyWeather, int position);


    void onActivityResultForTimeWeatherAreaResultOk(String areaCode, String areaName);
}
