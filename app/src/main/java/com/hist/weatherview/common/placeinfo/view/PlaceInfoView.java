package com.hist.weatherview.common.placeinfo.view;


import com.hist.item.weeklyweather.WeeklyWeatherArea;

import java.util.HashMap;
import java.util.List;

/**
 * 주간 날씨 정보 지역설정 베이스 뷰
 * Author:JJW
 * Date: 2018.03.29
 * Remark:
 */

public interface PlaceInfoView {

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

    void setPlaceInfoExpandableListViewAdapterItem(List<String> mCityList, HashMap<String, List<String>> mCityListDetail, HashMap<String, List<String>> mCityKeyListDetail);
}
