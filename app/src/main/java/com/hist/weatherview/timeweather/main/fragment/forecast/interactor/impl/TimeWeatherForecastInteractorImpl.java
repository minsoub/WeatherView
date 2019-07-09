package com.hist.weatherview.timeweather.main.fragment.forecast.interactor.impl;

import com.hist.item.PlaceInfo.PlaceInfoResult;
import com.hist.item.timeweather.TimeWeatherBase;
import com.hist.item.timeweather.TimeWeatherResult;
import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.item.weeklyweather.WeeklyWeatherBase;
import com.hist.repository.remote.PlaceInfoRepository;
import com.hist.repository.remote.TimeWeatherRepository;
import com.hist.repository.remote.WeeklyWeatherRepository;
import com.hist.repository.util.ErrorInterceptor;
import com.hist.repository.util.NetworkInterceptor;
import com.hist.weatherview.timeweather.main.fragment.forecast.interactor.TimeWeatherForecastInteractor;
import com.hist.weatherview.timeweather.main.fragment.forecast.presenter.TimeWeatherForecastPresenter;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.interactor.WeeklyWeatherForecastInteractor;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.presenter.WeeklyWeatherForecastPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 시간별 날씨 정보 예보 정보 인터렉터 구현 클래스
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */

public class TimeWeatherForecastInteractorImpl implements TimeWeatherForecastInteractor {
    private TimeWeatherForecastPresenter timeWeatherForecastPresenter;
    private WeeklyWeatherArea area;
    private TimeWeatherBase timeWeatherBase;
    private TimeWeatherRepository timeWeatherRepository;
    private PlaceInfoRepository placeInfoRepository;

    public TimeWeatherForecastInteractorImpl(TimeWeatherForecastPresenter timeWeatherForecastPresenter) {
        this.timeWeatherForecastPresenter = timeWeatherForecastPresenter;
        this.timeWeatherBase = null;

    }


    @Override
    public void setTimeWeatherBaseList(TimeWeatherBase timeWeatherBase) {
        this.timeWeatherBase = timeWeatherBase;
    }

    @Override
    public TimeWeatherBase getTimeWeatherBaseList() {
        return timeWeatherBase;
    }

    @Override
    public void setTimeWeatherRepository(WeeklyWeatherArea area) {
        // Repository 설정
    }

    @Override
    public void getTimeWeather(String day, String time, String nx, String ny) {



        try {
            Call<TimeWeatherBase> callGetTimeWeatherListByAreaApi = timeWeatherRepository.findTimeWeatherSpaceByDateAndTimeAndArea(day,
                    time,
                    nx,
                    ny);
            callGetTimeWeatherListByAreaApi.enqueue(new Callback<TimeWeatherBase>() {
                @Override
                public void onResponse(Call<TimeWeatherBase> call, Response<TimeWeatherBase> response) {
                    if (response.isSuccessful()) {
                        TimeWeatherBase weeklyWeathersBase = response.body();
                        timeWeatherForecastPresenter.onSuccessGetTimeWeatherByDateAndTimeAndArea(weeklyWeathersBase);
                    } else {
                        timeWeatherForecastPresenter.onNetworkError(new ErrorInterceptor(getClass()).parseError(response));
                    }
                }

                @Override
                public void onFailure(Call<TimeWeatherBase> call, Throwable t) {
                    //log(t);
                    timeWeatherForecastPresenter.onNetworkError(null);
                }
            });
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void setTimeWeatherBase(TimeWeatherBase timeWeatherBase) {
        this.timeWeatherBase = timeWeatherBase;

    }

    @Override
    public void setTimeWeatherRepository(String accessToken) {
        // 레포지토리 연결
        this.timeWeatherRepository = new NetworkInterceptor(accessToken).getTimeWeatherRepository().create(TimeWeatherRepository.class);
        this.placeInfoRepository = new NetworkInterceptor().getPlaceInfoRepository().create(PlaceInfoRepository.class);
    }

    @Override
    public WeeklyWeatherArea getTimeWeatherArea() {
        return null;
    }


    @Override
    public void getTimeWeatherByAreaID(WeeklyWeatherArea area) {
        /*Call<List<TimeWeatherBase>> callGetWeeklyWeatherListByAreaApi = weeklyWeatherRepository.findWeeklyWeatherListByArea(area.getAreaName() ,area.getAreaName(), area.getAreaName(), area.getAreaName());
        callGetWeeklyWeatherListByAreaApi.enqueue(new Callback<List<WeeklyWeatherBase>>() {
            @Override
            public void onResponse(Call<List<WeeklyWeatherBase>> call, Response<List<WeeklyWeatherBase>> response) {
                if (response.isSuccessful()) {
                    List<WeeklyWeatherBase> weeklyWeathers = response.body();
                    timeWeatherForecastPresenter.onSuccessGetWeeklyWeatherByAreaID(weeklyWeathers);
                } else {
                    timeWeatherForecastPresenter.onNetworkError(new ErrorInterceptor(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<List<WeeklyWeatherBase>> call, Throwable t) {
                //log(t);
                timeWeatherForecastPresenter.onNetworkError(null);
            }
        });*/
    }

    @Override
    public void setTimeWeatherRepository() {
        this.timeWeatherRepository = new NetworkInterceptor().getTimeWeatherRepository().create(TimeWeatherRepository.class);
        this.placeInfoRepository = new NetworkInterceptor().getPlaceInfoRepository().create(PlaceInfoRepository.class);
    }

    @Override
    public List<TimeWeatherResult> getTimeWeatherResult() {
        if(timeWeatherBase == null)
        {
            return null;
        }
        return timeWeatherBase.getData().getItem().getResult();
    }

    @Override
    public void getPlaceInfoByAreaCode(String areaCode) {
        try {
            Call<PlaceInfoResult> callGetPlaceInfoByAreaCodeApi = placeInfoRepository.findPlaceInfoByArea(areaCode);
            callGetPlaceInfoByAreaCodeApi.enqueue(new Callback<PlaceInfoResult>() {
                @Override
                public void onResponse(Call<PlaceInfoResult> call, Response<PlaceInfoResult> response) {
                    if (response.isSuccessful()) {
                        PlaceInfoResult placeInfoResult = response.body();
                        timeWeatherForecastPresenter.onSuccessGetPlaceInfoByAreaCode(placeInfoResult);
                    } else {
                        timeWeatherForecastPresenter.onNetworkError(new ErrorInterceptor(getClass()).parseError(response));
                    }
                }

                @Override
                public void onFailure(Call<PlaceInfoResult> call, Throwable t) {
                    //log(t);
                    timeWeatherForecastPresenter.onNetworkError(null);
                }
            });
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
