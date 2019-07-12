package com.hist.weatherview.main.presenter.impl;


import android.content.Context;

import com.hist.item.PlaceInfo.PlaceInfoResult;
import com.hist.item.main.MainMenuItem;
import com.hist.item.timeweather.TimeWeatherBase;
import com.hist.item.timeweather.TimeWeatherResult;
import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.repository.util.HttpError;
import com.hist.weatherview.R;
import com.hist.weatherview.common.util.DateUtil;
import com.hist.weatherview.main.base.MainActivity;
import com.hist.weatherview.main.presenter.MainPresenter;
import com.hist.weatherview.main.view.MainView;
import com.hist.weatherview.timeweather.main.fragment.forecast.interactor.TimeWeatherForecastInteractor;
import com.hist.weatherview.timeweather.main.fragment.forecast.interactor.impl.TimeWeatherForecastInteractorImpl;
import com.hist.weatherview.timeweather.main.fragment.forecast.presenter.TimeWeatherForecastPresenter;
import com.hist.weatherview.timeweather.main.fragment.forecast.view.TimeWeatherForecastView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 시간별 날씨 정보 예보 정보 프레젠터 인터페이스 구현
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */
public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;
    private Context context;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void init() {

    }


    @Override
    public void onCreateView() {

        //dummy
        List<MainMenuItem> mainMenuItemArrayList = new ArrayList<>();
        mainMenuItemArrayList.add(new MainMenuItem( R.drawable.ic_menu_airport, "공항 기상정보", "공항별 기상정보를 안내합니다", "0001"));
        mainMenuItemArrayList.add(new MainMenuItem( R.drawable.ic_menu_thunder_stroke, "낙뢰 정보", "낙뢰정보를 안내합니다", "0002"));
        mainMenuItemArrayList.add(new MainMenuItem( R.drawable.ic_menu_timewather, "날씨 정보", "날시정보를 안내합니다", "0003"));
        mainMenuItemArrayList.add(new MainMenuItem( R.drawable.ic_menu_weeklywather, "주간 날씨", "주간날씨 정보를 안내합니다", "0004"));
        mainMenuItemArrayList.add(new MainMenuItem( R.drawable.ic_menu_weeklywather,  "생활 기상", "생활기상 정보를 안내합니다", "0005"));
        mainMenuItemArrayList.add(new MainMenuItem(  R.drawable.ic_menu_weeklywather, "미세먼지 정보", "미세먼지 정보를 안내합니다", "0006"));

        mainView.setMainMenuRecycleViewAdapterItem(mainMenuItemArrayList);

    }

    @Override
    public void onNetworkError(HttpError httpError) {
        // 네트워크 실패

    }


    @Override
    public void onClickSearch() {

    }

    @Override
    public void onClickMenuItem(MainMenuItem mainMenuItem, int position) {
        mainView.showProgressDialog();
        mainView.navigateToMainMenuActivity(mainMenuItem, position);
        mainView.goneProgressDialog();
    }


}
