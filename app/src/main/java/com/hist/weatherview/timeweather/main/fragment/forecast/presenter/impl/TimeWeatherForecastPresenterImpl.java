package com.hist.weatherview.timeweather.main.fragment.forecast.presenter.impl;


import com.hist.item.timeweather.TimeWeatherBase;
import com.hist.item.timeweather.TimeWeatherResult;
import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.item.weeklyweather.WeeklyWeatherBase;
import com.hist.item.weeklyweather.WeeklyWeatherData;
import com.hist.item.weeklyweather.dto.WeeklyWeatherDto;
import com.hist.repository.util.HttpError;
import com.hist.weatherview.common.util.DateUtil;
import com.hist.weatherview.timeweather.main.fragment.forecast.interactor.TimeWeatherForecastInteractor;
import com.hist.weatherview.timeweather.main.fragment.forecast.interactor.impl.TimeWeatherForecastInteractorImpl;
import com.hist.weatherview.timeweather.main.fragment.forecast.presenter.TimeWeatherForecastPresenter;
import com.hist.weatherview.timeweather.main.fragment.forecast.view.TimeWeatherForecastView;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.interactor.WeeklyWeatherForecastInteractor;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.interactor.impl.WeeklyWeatherForecastInteractorImpl;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.presenter.WeeklyWeatherForecastPresenter;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.view.WeeklyWeatherForecastView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 시간별 날씨 정보 예보 정보 프레젠터 인터페이스 구현
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */
public class TimeWeatherForecastPresenterImpl implements TimeWeatherForecastPresenter {
    private TimeWeatherForecastView timeWeatherForecastView;
    private TimeWeatherForecastInteractor timeWeatherForecastInteractor;

    public TimeWeatherForecastPresenterImpl(TimeWeatherForecastView timeWeatherForecastView) {
        this.timeWeatherForecastView = timeWeatherForecastView;
        this.timeWeatherForecastInteractor = new TimeWeatherForecastInteractorImpl(this);
    }

    @Override
    public void init(WeeklyWeatherArea area) {
        timeWeatherForecastView.showProgressDialog();
        //repository 등록
        timeWeatherForecastInteractor.setTimeWeatherRepository();
    }

    @Override
    public void onCreateView() {
        // db onnect
        //timeWeatherForecastView.showProgressDialog();
        String time = "0500";
        String nx = "60";
        String ny = "127";

        String day = DateUtil.getCurrentDateByYYYYMMDD();

        timeWeatherForecastInteractor.getTimeWeather(day,time,nx,ny);

        // 투데이 날씨를 base activity로 넘긴다.
        //this.timeWeatherForecastView.setWeeklyWeatherToday(a1);*/

    }

    @Override
    public void onNetworkError(HttpError httpError) {
        // 네트워크 실패

    }


    @Override
    public void onClickSearch() {

    }

    @Override
    public void onSuccessGetTimeWeatherByAreaID(TimeWeatherBase timeWeatherBase) {
        // 네트워크 성공 후 API 서버로 부터 주간 기상 정보를 받아온다.
        timeWeatherForecastView.goneProgressDialog();
        int size = timeWeatherBase.getData().getItem().getResult().size();

        List<TimeWeatherResult> oldWeeklyWeatherItems = timeWeatherForecastInteractor.getTimeWeatherResult();
        int oldSize = (oldWeeklyWeatherItems == null) ? 0 : oldWeeklyWeatherItems.size();

        // 기존에 데이터가 없는 경우
        if (oldSize == 0) {
            //1. 신규 데이터를 Interactor에 설정 한다.
            //timeWeatherForecastInteractor.setWeeklyWeather(weeklyWeathersBase);
            //2. 어뎁타를 초기화한다.
            //timeWeatherForecastView.clearWeeklyWeatherAdapter();
            //3. 데이터를 어답에 등록한다.
            //timeWeatherForecastView.setWeeklyWeatherListRecycleViewAdapterItem(weeklyWeathersBase);
            timeWeatherForecastInteractor.setTimeWeatherBase(timeWeatherBase);
            timeWeatherForecastView.clearTimeWeatherAdapter();
            timeWeatherForecastView.setTimeWeatherListRecycleViewAdapterItem(timeWeatherBase.getData().getItem().getResult());
            timeWeatherForecastView.setTimeWeatherToday(timeWeatherBase.getData().getItem().getResult().get(0));
        } else {
            // 기존 데이터가 있는 경우..
            // 1. 추가 하지 않고 갱신한다.
           // timeWeatherForecastInteractor.setWeeklyWeather(weeklyWeathersBase);
            // 1.1 만약 Add 인경우
            //timeWeatherForecastInteractor.setWeeklyWeatherAddAll(weeklyWeathersBase);
            // 2.1 만약 NotifyItemChanged 필요한경우
            //timeWeatherForecastInteractor.AdapterNotifyItemRangeInserted(oldSize, newData);
            // 2.2 단순히 새로운 데이터 업데이트 시
            //2. 어뎁타를 초기화한다.
            timeWeatherForecastInteractor.setTimeWeatherBase(timeWeatherBase);
            timeWeatherForecastView.clearTimeWeatherAdapter();
            timeWeatherForecastView.setTimeWeatherListRecycleViewAdapterItem(timeWeatherBase.getData().getItem().getResult());

            //오늘날짜 등록 하기
            timeWeatherForecastView.setTimeWeatherToday(timeWeatherBase.getData().getItem().getResult().get(0));
            //3. 데이터를 어답에 등록한다.
           // timeWeatherForecastView.setWeeklyWeatherListRecycleViewAdapterItem(weeklyWeathersBase);
        }

        //timeWeatherForecastView.goneProgressDialog();
    }

    @Override
    public void onSuccessGetTimeWeatherByDateAndTimeAndArea(TimeWeatherBase timeWeatherBase) {
        // 네트워크 성공 후 API 서버로 부터 주간 기상 정보를 받아온다.
        timeWeatherForecastView.goneProgressDialog();
        int size = timeWeatherBase.getData().getItem().getResult().size();
        List<TimeWeatherResult> oldTimeWeatherItems = timeWeatherForecastInteractor.getTimeWeatherResult();
        int oldSize = (oldTimeWeatherItems == null) ? 0 : oldTimeWeatherItems.size();

        // 기존에 데이터가 없는 경우
        if (oldSize == 0) {
            //1. 신규 데이터를 Interactor에 설정 한다.
            //timeWeatherForecastInteractor.setWeeklyWeather(weeklyWeathersBase);
            //2. 어뎁타를 초기화한다.
            //timeWeatherForecastView.clearWeeklyWeatherAdapter();
            //3. 데이터를 어답에 등록한다.
            //timeWeatherForecastView.setWeeklyWeatherListRecycleViewAdapterItem(weeklyWeathersBase);
            timeWeatherForecastInteractor.setTimeWeatherBase(timeWeatherBase);
            timeWeatherForecastView.clearTimeWeatherAdapter();
            timeWeatherForecastView.setTimeWeatherListRecycleViewAdapterItem(timeWeatherBase.getData().getItem().getResult());
            timeWeatherForecastView.setTimeWeatherToday(timeWeatherBase.getData().getItem().getResult().get(0));
        } else {
            // 기존 데이터가 있는 경우..
            // 1. 추가 하지 않고 갱신한다.
            // timeWeatherForecastInteractor.setWeeklyWeather(weeklyWeathersBase);
            // 1.1 만약 Add 인경우
            //timeWeatherForecastInteractor.setWeeklyWeatherAddAll(weeklyWeathersBase);
            // 2.1 만약 NotifyItemChanged 필요한경우
            //timeWeatherForecastInteractor.AdapterNotifyItemRangeInserted(oldSize, newData);
            // 2.2 단순히 새로운 데이터 업데이트 시
            //2. 어뎁타를 초기화한다.
            timeWeatherForecastInteractor.setTimeWeatherBase(timeWeatherBase);
            timeWeatherForecastView.clearTimeWeatherAdapter();
            //3. 데이터를 어답에 등록한다.
            timeWeatherForecastView.setTimeWeatherListRecycleViewAdapterItem(timeWeatherBase.getData().getItem().getResult());
            timeWeatherForecastView.setTimeWeatherToday(timeWeatherBase.getData().getItem().getResult().get(0));
        }
    }

    public String getTodayDate()
    {

        Date currentTime = Calendar.getInstance().getTime();
        String currentDate = DateFormat.getDateTimeInstance().format(new Date());

        return currentDate;
    }
}
