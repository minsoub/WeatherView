package com.hist.weatherview.weeklyweather.main.fragment.forecast.presenter;

import com.hist.item.PlaceInfo.PlaceInfoResult;
import com.hist.item.weeklyweather.WeeklyWeatherBase;
import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.repository.util.HttpError;

/**
 * 주간 날씨 정보 예보 정보 프레젠터 인터페이스
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */
public interface WeeklyWeatherForecastPresenter {

    void init(WeeklyWeatherArea area);
    void onCreateView();
    //void setGuiderList(List<AreaInfo> guiderList);

    void onNetworkError(HttpError httpError);
    void onClickSearch();

    void onSuccessGetWeeklyWeatherByAreaID(WeeklyWeatherBase weeklyWeathersBase);

    void onSuccessGetWeeklyWeatherByDateAndTimeAndArea(WeeklyWeatherBase weeklyWeathersBase);

    void onSuccessGetWeeklyWeatherMiddleForecastAllByTimeAndRegIdAndStnId(WeeklyWeatherBase weeklyWeathersBase);
    //void onClickImage(PlaceAttraction guider, int position);

    //void onSuccessGetGuiderList(List<Guider> guiderList);
    void getWeeklyWeatherMiddleForecastByAreaAndDate(String areaCode);

    void onSuccessGetPlaceInfoByAreaCode(PlaceInfoResult placeInfoResult);      //지역코드 성공 시
}
