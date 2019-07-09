package com.hist.weatherview.common.placeinfo.presenter.impl;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.hist.weatherview.common.placeinfo.base.PlaceInfoActivity;
import com.hist.weatherview.common.placeinfo.presenter.PlaceInfoPresenter;
import com.hist.weatherview.common.placeinfo.view.PlaceInfoView;
import com.hist.weatherview.common.util.parser.placeinfo.PlaceInfoDataParser;
import com.hist.weatherview.common.util.parser.placeinfo.PlaceInfoDataParserListener;
import com.hist.weatherview.weatherlife.area.adapter.WeatherLifeAreaDataParser;
import com.hist.weatherview.weatherlife.area.adapter.WeatherLifeAreaExpandableListViewAdapter;
import com.hist.weatherview.weeklyweather.area.presenter.WeeklyWeatherAreaPresenter;
import com.hist.weatherview.weeklyweather.area.view.WeeklyWeatherAreaView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 장소 코드 정보 공통 베이스 프레젠터 구현 클래스
 * Author:JJW
 * Date: 2018.03.29
 * Remark:
 */

public class PlaceInfoPresenterImpl implements PlaceInfoPresenter, PlaceInfoDataParserListener {

    private PlaceInfoView placeInfoView;
    /* Expandable List View */

    private List<String> mCityList;
    private HashMap<String, List<String>> mCityListDetail;
    private HashMap<String, List<String>> mCityKeyListDetail;
    private ExpandableListView mExpandableListView;
    PlaceInfoDataParser parser;



    public PlaceInfoPresenterImpl(PlaceInfoView placeInfoView, PlaceInfoActivity activity){
        this.placeInfoView = placeInfoView;
        this.parser = new PlaceInfoDataParser(this, (PlaceInfoActivity)placeInfoView);
    }


    //view init
    @Override
    public void init() {
        //db 가져오는 등 절차..
        placeInfoView.init();
        placeInfoView.setToolbarLayout();
        placeInfoView.showToolbarTitle("위치찾기");

    }

    @Override
    public void onCreateView() {
        parser.LoadWeatherLifePlaceInfo();
    }

    @Override
    public void OnStartParsing() {

        //placeInfoView.showProgressDialog();
    }

    @Override
    public void OnFinishParsing(HashMap<String, List<String>> expandableListDetail, HashMap<String, List<String>> expandableKeyListDetail) {
        //placeInfoView.goneProgressDialog();
        mCityListDetail = expandableListDetail;
        mCityKeyListDetail = expandableKeyListDetail;
        mCityList = new ArrayList<String>(this.mCityListDetail.keySet());

        placeInfoView.setPlaceInfoExpandableListViewAdapterItem(mCityList, mCityListDetail, mCityKeyListDetail);


    }
}
