package com.hist.weatherview.weeklyweather.main.fragment.forecast.presenter.impl;


import com.hist.item.weeklyweather.WeeklyWeather;
import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.repository.util.HttpError;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.interactor.WeeklyWeatherForecastInteractor;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.interactor.impl.WeeklyWeatherForecastInteractorImpl;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.presenter.WeeklyWeatherForecastPresenter;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.view.WeeklyWeatherForecastView;

import java.util.ArrayList;
import java.util.List;

/**
 * 주간 날씨 정보 예보 정보 프레젠터 인터페이스 구현
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */
public class WeeklyWeatherForecastPresenterImpl implements WeeklyWeatherForecastPresenter {
    private WeeklyWeatherForecastView weeklyWeatherForecastView;
    private WeeklyWeatherForecastInteractor weeklyWeatherForecastInteractor;

    // private MarketMainInteractor marketMainInteractor;

    public WeeklyWeatherForecastPresenterImpl(WeeklyWeatherForecastView weeklyWeatherForecastView) {
        this.weeklyWeatherForecastView = weeklyWeatherForecastView;
        this.weeklyWeatherForecastInteractor = new WeeklyWeatherForecastInteractorImpl(this);
       // this.marketMainInteractor = new MarketMainInteractorImpl(this);
    }



    @Override
    public void init(WeeklyWeatherArea area) {
        weeklyWeatherForecastView.showProgressDialog();
        //repository 등록
    }

    @Override
    public void onCreateView() {
        // db onnect
        weeklyWeatherForecastView.showProgressDialog();
        //marketStoryView.setScrollViewOnScrollChangeListener();
        //WeeklyWeatherArea area = weeklyWeatherForecastInteractor.getWeeklyWeatherArea();    //Area 겟
        WeeklyWeatherArea area = new WeeklyWeatherArea();
        area.setAreaName("등촌1동");   // 테스트
        //weeklyWeatherForecastInteractor.getWeeklyWeatherByAreaID(area);   // 서버 연동

        List<WeeklyWeather> weeklyWeathers = new ArrayList<>();
        WeeklyWeather a1 = new WeeklyWeather();
        WeeklyWeather a2 = new WeeklyWeather();
        WeeklyWeather a3 = new WeeklyWeather();
        WeeklyWeather a4 = new WeeklyWeather();
        WeeklyWeather a5 = new WeeklyWeather();
        WeeklyWeather a6 = new WeeklyWeather();
        WeeklyWeather a7 = new WeeklyWeather();
        WeeklyWeather a8 = new WeeklyWeather();
        WeeklyWeather a9 = new WeeklyWeather();
        WeeklyWeather a10 = new WeeklyWeather();

        weeklyWeathers.add(a1);
        weeklyWeathers.add(a2);
        weeklyWeathers.add(a3);
        weeklyWeathers.add(a4);
        weeklyWeathers.add(a5);
        weeklyWeathers.add(a6);
        weeklyWeathers.add(a7);
        weeklyWeathers.add(a8);
        weeklyWeathers.add(a9);
        weeklyWeathers.add(a10);

        this.weeklyWeatherForecastView.setWeeklyWeatherListRecycleViewAdapterItem(weeklyWeathers);

        // 투데이 날씨를 base activity로 넘긴다.
        this.weeklyWeatherForecastView.setWeeklyWeatherToday(a1);

    }

    @Override
    public void onNetworkError(HttpError httpError) {
        // 네트워크 실패

    }


    @Override
    public void onClickSearch() {

    }

    @Override
    public void onSuccessGetWeeklyWeatherByAreaID(List<WeeklyWeather> weeklyWeathers) {
        // 네트워크 성공 후 API 서버로 부터 주간 기상 정보를 받아온다.
        int size = weeklyWeathers.size();
        List<WeeklyWeather> oldWeeklyWeathers = weeklyWeatherForecastInteractor.getWeeklyWeather();
        int oldSize = oldWeeklyWeathers.size();

        if (size > 0) {
            weeklyWeatherForecastInteractor.setWeeklyWeather(weeklyWeathers);
            weeklyWeatherForecastView.clearWeeklyWeatherAdapter();
            weeklyWeatherForecastView.setWeeklyWeatherListRecycleViewAdapterItem(weeklyWeathers);
        } else {
            if(oldSize == 0){
                weeklyWeatherForecastView.showEmptyWeeklyWeatherView();
            }
        }

        weeklyWeatherForecastView.goneProgressDialog();
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
