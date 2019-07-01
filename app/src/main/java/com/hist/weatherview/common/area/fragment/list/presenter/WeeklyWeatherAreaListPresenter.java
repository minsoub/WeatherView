package com.hist.weatherview.weeklyweather.area.fragment.list.presenter;

import com.hist.item.weeklyweather.WeeklyWeatherArea;

/**
 * 주간 날씨 정보 지역설정 리스트 인터페이스
 * Author:JJW
 * Date: 2018.03.29
 * Remark:
 */
public interface WeeklyWeatherAreaListPresenter {

    void init();
    void onCreateView();
    //void setGuiderList(List<AreaInfo> guiderList);

    //void onNetworkError(HttpErrorDto httpErrorDto);

    void onClickSearch();
    void onClickArea(WeeklyWeatherArea area, int position);         // 지역 선택 이벤트
    //void onClickImage(PlaceAttraction guider, int position);

    //void onSuccessGetGuiderList(List<Guider> guiderList);
}
