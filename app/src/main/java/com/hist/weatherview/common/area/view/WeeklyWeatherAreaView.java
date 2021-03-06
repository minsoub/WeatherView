package com.hist.weatherview.weeklyweather.area.view;


import com.hist.item.weeklyweather.WeeklyWeatherArea;

/**
 * 주간 날씨 정보 지역설정 베이스 뷰
 * Author:JJW
 * Date: 2018.03.29
 * Remark:
 */

public interface WeeklyWeatherAreaView {

    void showProgressDialog();

    void goneProgressDialog();

    void setToolbarLayout();

    void showToolbarTitle(String title);

    void init();

    void showMessage(String message);

    void onClickCancel();

    void onClickCurrentPosition();

    void navigateToGuFragment(WeeklyWeatherArea area);            // 구 설정 화면

    void navigateToDongFragment(WeeklyWeatherArea area);          // 동 설정 화면

    void navigateToWeeklyWeatherMain(WeeklyWeatherArea area);     // 메인 화면으로 이동한다.


}
