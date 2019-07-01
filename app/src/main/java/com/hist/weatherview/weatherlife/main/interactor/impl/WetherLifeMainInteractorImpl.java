package com.hist.weatherview.weatherlife.main.interactor.impl;

import com.hist.item.weatherlife.WeatherLifeBase;
import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.item.weeklyweather.WeeklyWeatherBase;
import com.hist.item.weeklyweather.dto.WeeklyWeatherDto;
import com.hist.repository.remote.WeatherLifeRepository;
import com.hist.repository.remote.WeeklyWeatherRepository;
import com.hist.repository.util.ErrorInterceptor;
import com.hist.repository.util.NetworkInterceptor;
import com.hist.weatherview.weatherlife.main.interactor.WeatherLifeMainInteractor;
import com.hist.weatherview.weatherlife.main.presenter.WeatherLifeMainPresenter;
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

public class WetherLifeMainInteractorImpl implements WeatherLifeMainInteractor {
    private WeatherLifeMainPresenter weatherLifeMainPresenter;
    private WeeklyWeatherArea area;
    private WeatherLifeBase weeklyWeatherBase;
    private WeatherLifeRepository weatherLifeRepository;

    public WetherLifeMainInteractorImpl(WeatherLifeMainPresenter weatherLifeMainPresenter) {
        this.weatherLifeMainPresenter = weatherLifeMainPresenter;
        this.weeklyWeatherBase = new WeatherLifeBase();

    }


    @Override
    public void setWeatherLifeBase(WeatherLifeBase weatherLifeBase) {
        this.weeklyWeatherBase = weatherLifeBase;
    }

    @Override
    public WeatherLifeBase getWeatherLifeBase() {
        return this.weeklyWeatherBase;
    }


    @Override
    public void getWeatherLifeFsnLifeListByAreaAndDate(String area, String date) {
        try {
            Call<WeatherLifeBase> callGetWeatherLifeListByAreaApi = weatherLifeRepository.findWeatherLifeFsnLifeListByAreaAndDate(area, date);
            callGetWeatherLifeListByAreaApi.enqueue(new Callback<WeatherLifeBase>() {
                @Override
                public void onResponse(Call<WeatherLifeBase> call, Response<WeatherLifeBase> response) {
                    if (response.isSuccessful()) {
                        WeatherLifeBase weatherLifeBase = response.body();
                        weatherLifeMainPresenter.onSuccessGetWeatherLifeFsnLifeListByAreaAndDate(weatherLifeBase);
                    } else {
                        weatherLifeMainPresenter.onNetworkError(new ErrorInterceptor(getClass()).parseError(response));
                    }
                }

                @Override
                public void onFailure(Call<WeatherLifeBase> call, Throwable t) {
                    //log(t);
                    weatherLifeMainPresenter.onNetworkError(null);
                }
            });
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void getWeatherLifeSensorytemLifeListByAreaAndDate(String area, String date) {
        try {
            Call<WeatherLifeBase> callGetWeatherLifeListByAreaApi = weatherLifeRepository.findWeatherLifeSensorytemLifeListByAreaAndDate(area, date);
            callGetWeatherLifeListByAreaApi.enqueue(new Callback<WeatherLifeBase>() {
                @Override
                public void onResponse(Call<WeatherLifeBase> call, Response<WeatherLifeBase> response) {
                    if (response.isSuccessful()) {
                        WeatherLifeBase weatherLifeBase = response.body();
                        weatherLifeMainPresenter.onSuccessGetWeatherLifeSensorytemLifeListByAreaAndDate(weatherLifeBase);
                    } else {
                        weatherLifeMainPresenter.onNetworkError(new ErrorInterceptor(getClass()).parseError(response));
                    }
                }

                @Override
                public void onFailure(Call<WeatherLifeBase> call, Throwable t) {
                    //log(t);
                    weatherLifeMainPresenter.onNetworkError(null);
                }
            });
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void getWeatherLifeHeatLifeListByAreaAndDate(String area, String date) {
        /*try {
            Call<WeeklyWeatherBase> callGetWeatherLifeListByAreaApi = weeklyWeatherRepository.findWeeklyWeatherSpaceByDateAndTimeAndArea(weeklyWeatherDto.getDate(),
                    weeklyWeatherDto.getTime(),
                    weeklyWeatherDto.getNx(),
                    weeklyWeatherDto.getNy());
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
        }*/
    }

    @Override
    public void setWeatherLifeRepository() {
        this.weatherLifeRepository = new NetworkInterceptor().getWeatherLifeRepository().create(WeatherLifeRepository.class);
    }

    @Override
    public void getWeatherLifeAllLifeListByAreaAndDate(String area, String date) {
        try {
            Call<WeatherLifeBase> callGetWeatherLifeListByAreaApi = weatherLifeRepository.findWeatherLifeAllLifeListByAreaAndDate(area, date);
            callGetWeatherLifeListByAreaApi.enqueue(new Callback<WeatherLifeBase>() {
                @Override
                public void onResponse(Call<WeatherLifeBase> call, Response<WeatherLifeBase> response) {
                    if (response.isSuccessful()) {
                        WeatherLifeBase weatherLifeBase = response.body();
                        weatherLifeMainPresenter.onSuccessGetWeatherLifeAllLifeListByAreaAndDate(weatherLifeBase);
                    } else {
                        weatherLifeMainPresenter.onNetworkError(new ErrorInterceptor(getClass()).parseError(response));
                    }
                }

                @Override
                public void onFailure(Call<WeatherLifeBase> call, Throwable t) {
                    //log(t);
                    weatherLifeMainPresenter.onNetworkError(null);
                }
            });
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
