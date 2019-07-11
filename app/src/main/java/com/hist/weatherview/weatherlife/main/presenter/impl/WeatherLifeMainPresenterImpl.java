package com.hist.weatherview.weatherlife.main.presenter.impl;


import android.content.Context;

import com.hist.item.common.SharedPlaceInfo;
import com.hist.item.weatherlife.WeatherLifeBase;
import com.hist.item.weatherlife.WeatherLifeBaseData;
import com.hist.item.weatherlife.WeatherLifeItem;
import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.repository.local.SharedPrefersManager;
import com.hist.repository.util.HttpError;
import com.hist.weatherview.common.util.DateUtil;
import com.hist.weatherview.weatherlife.main.interactor.WeatherLifeMainInteractor;
import com.hist.weatherview.weatherlife.main.interactor.impl.WetherLifeMainInteractorImpl;
import com.hist.weatherview.weatherlife.main.presenter.WeatherLifeMainPresenter;
import com.hist.weatherview.weatherlife.main.view.WeatherLifeMainView;

/**
 * 주간 날씨 정보 예보 정보 프레젠터 인터페이스 구현
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */
public class WeatherLifeMainPresenterImpl implements WeatherLifeMainPresenter {
    private WeatherLifeMainView weatherLifeMainView;
    private WeatherLifeMainInteractor weatherLifeMainInteractor;
    private SharedPlaceInfo startPlaceInfo;
    private SharedPrefersManager sharedPrefersManager;

    public WeatherLifeMainPresenterImpl(WeatherLifeMainView weatherLifeMainView) {
        this.weatherLifeMainView = weatherLifeMainView;
        this.weatherLifeMainInteractor = new WetherLifeMainInteractorImpl(this);
        this.sharedPrefersManager = new SharedPrefersManager((Context)weatherLifeMainView);
    }

    /**
     *  초기화 함수
     * @param area
     */
    @Override
    public void init(WeeklyWeatherArea area) {
       // weatherLifeMainView.showProgressDialog();
        //repository 등록
        weatherLifeMainInteractor.setWeatherLifeRepository();
    }

    @Override
    public void init() {
        weatherLifeMainView.showProgressDialog();
        //repository 등록
        weatherLifeMainInteractor.setWeatherLifeRepository();

        String date = DateUtil.getCurrentDateByYYYYMMDD() + "06";

        // weatherLifeMainInteractor.getWeatherLifeFsnLifeListByAreaAndDate("1100000000", "2019061206" );
        this.startPlaceInfo = this.sharedPrefersManager.getWeatherLifeStartPlace();
        if(this.startPlaceInfo == null)
        {
            this.startPlaceInfo = new SharedPlaceInfo("1100000000", "서울특별시");
        }
        weatherLifeMainView.setWeatherLifePlaceTilte(this.startPlaceInfo.getPlaceName());
        weatherLifeMainInteractor.getWeatherLifeAllLifeListByAreaAndDate(this.startPlaceInfo.getPlaceCode(), date );
    }

    @Override
    public void onNetworkError(HttpError httpError) {
        // 네트워크 실패

    }


    @Override
    public void onClickSearch() {

    }

    @Override
    public void onSuccessGetWeeklyWeatherByAreaID(WeatherLifeBase weatherLifeBase) {

    }

    @Override
    public void onSuccessGetWeeklyWeatherByDateAndTimeAndArea(WeatherLifeBase weatherLifeBase) {
        // 네트워크 성공 후 API 서버로 부터 주간 기상 정보를 받아온다.
        //int size = weatherLifeBase.getData();
        /*List<WeeklyWeatherData> oldWeeklyWeatherData = weeklyWeatherForecastInteractor.getWeeklyWeatherBaseList().getData();
        int oldSize = (oldWeeklyWeatherItems == null) ? 0 : oldWeeklyWeatherItems.size();

        // 기존에 데이터가 없는 경우
        if (oldSize == 0) {
            //1. 신규 데이터를 Interactor에 설정 한다.
            //weeklyWeatherForecastInteractor.setWeeklyWeather(weeklyWeathersBase);
            //2. 어뎁타를 초기화한다.
            //weeklyWeatherForecastView.clearWeeklyWeatherAdapter();
            //3. 데이터를 어답에 등록한다.
            //weeklyWeatherForecastView.setWeeklyWeatherListRecycleViewAdapterItem(weeklyWeathersBase);
            weatherLifeMainInteractor.setWeatherLifeItem(weeklyWeathersBase);
            weatherLifeMainView.clearWeeklyWeatherAdapter();
            weatherLifeMainView.setWeeklyWeatherListRecycleViewAdapterItem(weeklyWeathersBase.getData());
        } else {
            // 기존 데이터가 있는 경우..
            // 1. 추가 하지 않고 갱신한다.
            // weeklyWeatherForecastInteractor.setWeeklyWeather(weeklyWeathersBase);
            // 1.1 만약 Add 인경우
            //weeklyWeatherForecastInteractor.setWeeklyWeatherAddAll(weeklyWeathersBase);
            // 2.1 만약 NotifyItemChanged 필요한경우
            //weeklyWeatherForecastInteractor.AdapterNotifyItemRangeInserted(oldSize, newData);
            // 2.2 단순히 새로운 데이터 업데이트 시
            //2. 어뎁타를 초기화한다.
            weeklyWeatherForecastView.clearWeeklyWeatherAdapter();
            //3. 데이터를 어답에 등록한다.
            weeklyWeatherForecastView.setWeeklyWeatherListRecycleViewAdapterItem(weeklyWeathersBase.getData());
        }*/
    }

    @Override
    public void onSuccessGetWeatherLifeFsnLifeListByAreaAndDate(WeatherLifeBase weatherLifeBase) {
        //weatherLifeMainView.setWeatherLifeFsnLifeListAdapter(weatherLifeBase);
    }

    @Override
    public void onSuccessGetWeatherLifeSensorytemLifeListByAreaAndDate(WeatherLifeBase weatherLifeBase) {
        weatherLifeMainView.setWeatherLifeSensorytemLifeListAdapter(weatherLifeBase);
    }

    @Override
    public void onSuccessGetWeatherLifeAllLifeListByAreaAndDate(WeatherLifeBase weatherLifeBase) {
        weatherLifeMainView.goneProgressDialog();
        WeatherLifeBaseData data = weatherLifeBase.getData();

        int size = data.getItems().size();
        WeatherLifeBase oldWeatherLifeBase = weatherLifeMainInteractor.getWeatherLifeBase();
        if(oldWeatherLifeBase == null)
        {
            weatherLifeMainInteractor.setWeatherLifeBase(weatherLifeBase);
            weatherLifeMainView.clearListViewsAndPages();
            weatherLifeMainView.setListViewsAndPages(size);
            weatherLifeMainView.setWeatherLifeAllLifeListAdapter(weatherLifeBase);
            weatherLifeMainView.setTabLayoutWithViewPager(size);
        }else
        {
            weatherLifeMainView.clearListViewArrayAdapter();
            weatherLifeMainView.clearListViewsAndPages();
            weatherLifeMainView.setListViewsAndPages(size);
            weatherLifeMainView.setListViewArrayAdapter(size);
            weatherLifeMainView.setWeatherLifeAllLifeListAdapter(weatherLifeBase);
            weatherLifeMainView.setTabLayoutWithViewPager(size);
        }
    }

    @Override
    public void onChangeWeatherLifeType(WeatherLifeItem weatherLifeItem, String type) {
        String retVal = "";
        if("fsnLifeItem".equals(type))
        {
            retVal = "식중독 지수";
        }else if("sensorytemLifeItem".equals(type))
        {
            retVal = "체감온도 지수";
        }
        else if("heatLifeItem".equals(type))
        {
            retVal = "열 지수";
        }
        else if("dspIsLifeItem".equals(type))
        {
            retVal = "불쾌 지수";
        }
        else if("airpollutionLifeItem".equals(type))
        {
            retVal = "대기오염확산 지수";
        }
        else if("winterLifeItem".equals(type))
        {
            retVal = "동파 지수";
        }
        else if("ultrvLifeItem".equals(type))
        {
            retVal = "자외선 지수";
        }

        //int itemCount = weatherLifeItem.getResult().size();

        weatherLifeMainView.setOnChangeWeatherLifeType(retVal, weatherLifeItem);
    }

    @Override
    public void onChangeArea(String area) {
        String date = DateUtil.getCurrentDateByYYYYMMDD() + "06";
        weatherLifeMainInteractor.getWeatherLifeAllLifeListByAreaAndDate(area, date );
    }

    @Override
    public void onClickAreaSearch() {
        weatherLifeMainView.navigateToWeatherLifeAreaActivity();
    }

    @Override
    public void onActivityResultForWeatherLifeAreaResultOk(String areaCode, String areaName) {
        String date = DateUtil.getCurrentDateByYYYYMMDD() + "06";
        if(this.startPlaceInfo == null)
        {
            this.startPlaceInfo = new SharedPlaceInfo(areaCode, areaName);
        }else
        {
            this.startPlaceInfo.setPlaceName(areaName);
            this.startPlaceInfo.setPlaceCode(areaCode);
        }
        this.sharedPrefersManager.setWeatherLifeStartPlace(startPlaceInfo);

        weatherLifeMainInteractor.getWeatherLifeAllLifeListByAreaAndDate(areaCode, date );
    }

    /*@Override
    public void onClickImage(PlaceAttraction placeAttraction, int position) {
        // 어뎁터에서 뷰 아이템 클릭시 발생
        this.placeAttractionView.navigateToPlaceAttractionDetailActivity(placeAttraction, position);
    }


    @Override
    public void onSuccessGetGuiderList(List<Guider> guiderList) {

    }*/
}
