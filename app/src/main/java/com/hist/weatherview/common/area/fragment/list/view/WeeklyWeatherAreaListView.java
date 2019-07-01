package com.hist.weatherview.weeklyweather.area.fragment.list.view;

import com.hist.item.weeklyweather.WeeklyWeatherArea;

import java.util.List;


/**
 * 주간 날씨 정보 지역설정 리스트 뷰 인터페이스
 * Author:JJW
 * Date: 2018.03.29
 * Remark:
 */

public interface WeeklyWeatherAreaListView {

    void showProgressDialog();

    void goneProgressDialog();

    void init();

    void showMessage(String message);

    void setWeeklyWeatherAreaListRecycleViewAdapterItem(List<WeeklyWeatherArea> weeklyWeatherAreas);    // 시, 구, 동 어뎁터 설정

    void navigateToGuFragment(WeeklyWeatherArea area);

    void navigateToDongFragment(WeeklyWeatherArea area);

    void navigateToWeeklyWeatherMain(WeeklyWeatherArea area);     // 메인 화면으로 이동한다.

}
