package com.hist.weatherview.weeklyweather.main.fragment.forecast.interactor.impl;

import com.hist.item.weeklyweather.WeeklyWeatherBase;
import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.item.weeklyweather.dto.WeeklyWeatherDto;
import com.hist.repository.remote.WeeklyWeatherRepository;
import com.hist.repository.util.ErrorInterceptor;
import com.hist.repository.util.NetworkInterceptor;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.interactor.WeeklyWeatherForecastInteractor;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.presenter.WeeklyWeatherForecastPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 주간 날씨 정보 예보 정보 인터렉터 구현 클래스
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */

public class WeeklyWeatherForecastInteractorImpl implements WeeklyWeatherForecastInteractor {
    private WeeklyWeatherForecastPresenter weeklyWeatherForecastPresenter;
    private WeeklyWeatherArea area;
    private WeeklyWeatherBase weeklyWeatherBaseList;
    private WeeklyWeatherRepository weeklyWeatherRepository;

    public WeeklyWeatherForecastInteractorImpl(WeeklyWeatherForecastPresenter weeklyWeatherForecastPresenter) {
        this.weeklyWeatherForecastPresenter = weeklyWeatherForecastPresenter;
        this.weeklyWeatherBaseList = new WeeklyWeatherBase();

    }


    @Override
    public void setWeeklyWeatherBaseList(WeeklyWeatherBase weeklyWeatherBaseList) {
        this.weeklyWeatherBaseList = weeklyWeatherBaseList;
    }

    @Override
    public WeeklyWeatherBase getWeeklyWeatherBaseList() {
        return weeklyWeatherBaseList;
    }

    @Override
    public void setWeeklyWeatherRepository(WeeklyWeatherArea area) {
        // Repository 설정
    }

    @Override
    public void getWeeklyWeather(String day, String time, String nx, String ny) {



        try {
            Call<WeeklyWeatherBase> callGetWeeklyWeatherListByAreaApi = weeklyWeatherRepository.findWeeklyWeatherSpaceByDateAndTimeAndArea(day,
                    time,
                    nx,
                    ny);
            callGetWeeklyWeatherListByAreaApi.enqueue(new Callback<WeeklyWeatherBase>() {
                @Override
                public void onResponse(Call<WeeklyWeatherBase> call, Response<WeeklyWeatherBase> response) {
                    if (response.isSuccessful()) {
                        WeeklyWeatherBase weeklyWeathersBase = response.body();
                        weeklyWeatherForecastPresenter.onSuccessGetWeeklyWeatherByDateAndTimeAndArea(weeklyWeathersBase);
                    } else {
                        weeklyWeatherForecastPresenter.onNetworkError(new ErrorInterceptor(getClass()).parseError(response));
                    }
                }

                @Override
                public void onFailure(Call<WeeklyWeatherBase> call, Throwable t) {
                    //log(t);
                    weeklyWeatherForecastPresenter.onNetworkError(null);
                }
            });
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void setWeeklyWeather(WeeklyWeatherBase weeklyWeatherBase) {
        this.weeklyWeatherBaseList = weeklyWeatherBase;

    }

    @Override
    public void setWeeklyWeatherRepository(String accessToken) {
        // 레포지토리 연결
        this.weeklyWeatherRepository = new NetworkInterceptor(accessToken).getWeeklyWeatherRepository().create(WeeklyWeatherRepository.class);
    }

    @Override
    public WeeklyWeatherArea getWeeklyWeatherArea() {
        return null;
    }

    @Override
    public void getWeeklyWeatherByAreaID(WeeklyWeatherArea area) {
        /*Call<List<WeeklyWeatherBase>> callGetWeeklyWeatherListByAreaApi = weeklyWeatherRepository.findWeeklyWeatherListByArea(area.getAreaName() ,area.getAreaName(), area.getAreaName(), area.getAreaName());
        callGetWeeklyWeatherListByAreaApi.enqueue(new Callback<List<WeeklyWeatherBase>>() {
            @Override
            public void onResponse(Call<List<WeeklyWeatherBase>> call, Response<List<WeeklyWeatherBase>> response) {
                if (response.isSuccessful()) {
                    List<WeeklyWeatherBase> weeklyWeathers = response.body();
                    weeklyWeatherForecastPresenter.onSuccessGetWeeklyWeatherByAreaID(weeklyWeathers);
                } else {
                    weeklyWeatherForecastPresenter.onNetworkError(new ErrorInterceptor(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<List<WeeklyWeatherBase>> call, Throwable t) {
                //log(t);
                weeklyWeatherForecastPresenter.onNetworkError(null);
            }
        });*/
    }

    @Override
    public void setWeeklyWeatherRepository() {
        this.weeklyWeatherRepository = new NetworkInterceptor().getWeeklyWeatherRepository().create(WeeklyWeatherRepository.class);
    }

    @Override
    public void getWeeklyWeatherAll(String time, String landRegId, String tempRegId, String stnId) {

        try {
            Call<WeeklyWeatherBase> callGetWeeklyWeatherListByAreaApi = weeklyWeatherRepository.findWeeklyWeatherMiddleForecastAllByTimeAndRegIdAndStnId(time,
                    landRegId,
                    tempRegId,
                    stnId);
            callGetWeeklyWeatherListByAreaApi.enqueue(new Callback<WeeklyWeatherBase>() {
                @Override
                public void onResponse(Call<WeeklyWeatherBase> call, Response<WeeklyWeatherBase> response) {
                    if (response.isSuccessful()) {
                        WeeklyWeatherBase weeklyWeathersBase = response.body();
                        weeklyWeatherForecastPresenter.onSuccessGetWeeklyWeatherMiddleForecastAllByTimeAndRegIdAndStnId(weeklyWeathersBase);
                    } else {
                        weeklyWeatherForecastPresenter.onNetworkError(new ErrorInterceptor(getClass()).parseError(response));
                    }
                }

                @Override
                public void onFailure(Call<WeeklyWeatherBase> call, Throwable t) {
                    //log(t);
                    weeklyWeatherForecastPresenter.onNetworkError(null);
                }
            });
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
