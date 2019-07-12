package com.hist.weatherview.main.presenter;

import com.hist.item.PlaceInfo.PlaceInfoResult;
import com.hist.item.main.MainMenuItem;
import com.hist.item.timeweather.TimeWeatherBase;
import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.repository.util.HttpError;

/**
 * 시간별 날씨 정보 예보 정보 프레젠터 인터페이스
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */
public interface MainPresenter {

    void init();
    void onCreateView();

    void onNetworkError(HttpError httpError);
    void onClickSearch();

    void onClickMenuItem(MainMenuItem mainMenuItem, int position);
}
