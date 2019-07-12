package com.hist.weatherview.main.view;

import com.hist.item.main.MainMenuItem;
import com.hist.item.timeweather.TimeWeatherResult;

import java.util.List;

/**
 * 시간별 날씨 정보 예보 정보 뷰 인터페이스
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */
public interface MainView {

    void showProgressDialog();

    void goneProgressDialog();

    void init();

    void showMessage(String message);

    void clearMainMenuRecycleViewAdapter();   // 어뎁터 설정 초기화

    void setMainMenuRecycleViewAdapterItem(List<MainMenuItem> data);

    void navigateToMainMenuActivity(MainMenuItem mainMenuItem, int position);
}
