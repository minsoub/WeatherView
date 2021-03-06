package com.hist.weatherview.weatherlife.main.view;

import com.hist.item.weatherlife.WeatherLifeBase;
import com.hist.item.weatherlife.WeatherLifeBaseItem;
import com.hist.item.weatherlife.WeatherLifeItem;

/**
 * 생활 기상 뷰 인터페이스
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */
public interface WeatherLifeMainView {

    void showProgressDialog();

    void goneProgressDialog();

    void init();

    void showMessage(String message);

    void clearWeatherLifeAdapter();   // 어뎁터 설정 초기화

    void showEmptyWeatherLifeView();  // 빈 리스트뷰 현시


    void setWeatherLifeFsnLifeListAdapter(WeatherLifeBaseItem weatherLifeBase);

    void setWeatherLifeSensorytemLifeListAdapter(WeatherLifeBase weatherLifeBase);

    void setWeatherLifeAllLifeListAdapter(WeatherLifeBase weatherLifeBase);

    void setTabLayoutWithViewPager(int size);

    void setOnChangeWeatherLifeType(String text, WeatherLifeItem itemCount);       // 뷰 페이저 변경 시 타이틀 변경

    void clearListViewArrayAdapter();

    void setListViewArrayAdapter(int size);

    void onClickAreaSearch();

    void navigateToWeatherLifeAreaActivity();

    void clearListViewsAndPages();

    void setListViewsAndPages(int size);

    void setWeatherLifePlaceTilte(String placeName);
}
