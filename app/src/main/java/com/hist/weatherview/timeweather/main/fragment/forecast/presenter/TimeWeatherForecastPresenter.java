package com.hist.weatherview.timeweather.main.fragment.forecast.presenter;

import com.hist.item.timeweather.TimeWeatherBase;
import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.item.weeklyweather.WeeklyWeatherBase;
import com.hist.repository.util.HttpError;

/**
 * 시간별 날씨 정보 예보 정보 프레젠터 인터페이스
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */
public interface TimeWeatherForecastPresenter {

    void init(WeeklyWeatherArea area);
    void onCreateView();
    //void setGuiderList(List<AreaInfo> guiderList);

    void onNetworkError(HttpError httpError);
    void onClickSearch();

    void onSuccessGetTimeWeatherByAreaID(TimeWeatherBase timeWeatherBase);

    void onSuccessGetTimeWeatherByDateAndTimeAndArea(TimeWeatherBase timeWeatherBase);
    //void onClickImage(PlaceAttraction guider, int position);

    //void onSuccessGetGuiderList(List<Guider> guiderList);
}
