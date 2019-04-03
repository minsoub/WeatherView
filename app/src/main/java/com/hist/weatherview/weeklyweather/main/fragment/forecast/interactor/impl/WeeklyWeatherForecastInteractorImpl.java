package com.hist.weatherview.weeklyweather.main.fragment.forecast.interactor.impl;

import com.hist.item.weeklyweather.WeeklyWeather;
import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.repository.remote.WeeklyWeatherRepository;
import com.hist.repository.util.ErrorInterceptor;
import com.hist.repository.util.NetworkInterceptor;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.interactor.WeeklyWeatherForecastInteractor;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.presenter.WeeklyWeatherForecastPresenter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
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
    private WeeklyWeatherRepository weeklyWeatherRepository;

    public WeeklyWeatherForecastInteractorImpl(WeeklyWeatherForecastPresenter weeklyWeatherForecastPresenter) {
        this.weeklyWeatherForecastPresenter = weeklyWeatherForecastPresenter;
    }


    @Override
    public void setWeeklyWeatherRepository(WeeklyWeatherArea area) {
        // Repository 설정
    }

    @Override
    public List<WeeklyWeather> getWeeklyWeather() {
        return null;
    }

    @Override
    public void setWeeklyWeather(List<WeeklyWeather> weeklyWeather) {

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
        Call<List<WeeklyWeather>> callGetWeeklyWeatherListByAreaApi = weeklyWeatherRepository.findWeeklyWeatherListByArea(area.getAreaName());
        callGetWeeklyWeatherListByAreaApi.enqueue(new Callback<List<WeeklyWeather>>() {
            @Override
            public void onResponse(Call<List<WeeklyWeather>> call, Response<List<WeeklyWeather>> response) {
                if (response.isSuccessful()) {
                    List<WeeklyWeather> weeklyWeathers = response.body();
                    weeklyWeatherForecastPresenter.onSuccessGetWeeklyWeatherByAreaID(weeklyWeathers);
                } else {
                    weeklyWeatherForecastPresenter.onNetworkError(new ErrorInterceptor(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<List<WeeklyWeather>> call, Throwable t) {
                //log(t);
                weeklyWeatherForecastPresenter.onNetworkError(null);
            }
        });
    }
}
