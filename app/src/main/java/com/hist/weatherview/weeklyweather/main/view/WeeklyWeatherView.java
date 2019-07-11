package com.hist.weatherview.weeklyweather.main.view;


import com.hist.item.common.SharedPlaceInfo;
import com.hist.item.weeklyweather.WeeklyWeatherBase;
import com.hist.item.weeklyweather.WeeklyWeatherArea;

import java.util.ArrayList;

/**
 * 주간 날씨 정보 예보 정보 뷰 인터페이스
 * Author:JJW
 * Date: 2018.03.18
 * Remark:
 */
public interface WeeklyWeatherView {

    void showProgressDialog();

    void goneProgressDialog();

    void setToolbarLayout();                // 툴바 셋팅

    void showToolbarTitle(String title);    // 툴바 타이틀

    void init();                            // 초기화

    void showMessage(String message);       // 메시지 로직

    void onClickMore();                     // 더보기 클릭

    void setWeeklyWeatherSearchArea(String area);   // 지역 설정 완료 후 최종 지역 가져오기

    void setWeeklyWeatherTodayForecast(WeeklyWeatherBase today);

    void setWeeklyWeatherFavoriteArea(ArrayList<SharedPlaceInfo> placeInfoArrayList, int index); // 즐겨 찾기 설정 완료 후 최종 즐겨 찾기 가졍괴

    void navigateToWeeklyWeatherSearchArea();       // 지역 설정 하기로 이동하기

    void navigateToWeeklyWeatherFavoriteArea();     // 즐겨 찾기 설정 하기로 이동하기

    void weeklyWeatherAdapterNotifyItemChanged();

    // fragment change
    void setWeeklyWeatherListFragmentChange();      // 프레그먼트 변경하기

    void setWeeklyWeatherListFragmentChange(WeeklyWeatherArea area);    // 선택 한 지역으로 프레그먼트 재설정 하기

    //
    void weeklyWeatherAreaChanged(WeeklyWeatherArea area);              // 지역 설정 또는 즐겨찾기를 통해 지역이 변경된것을 알리기

    void deleteWeeklyWeatherFavoriteArea(ArrayList<SharedPlaceInfo> placeInfoArrayList, int index);                  // 즐겨찾기 지역 삭제 하기

    void setWeeklyWeatherMiddleForecast(WeeklyWeatherBase weeklyWeatherMiddleForecast);

    void getWeeklyWeatherMiddleForecastByAreaAndDate(String areaCode, String s);

    void navigateToWeeklyWeatherFavoriteAreaFail();                     // 즐겨 찾기 Fail 인 경우 dismiss 한다.

    void setWeeklyWeatherPlaceTitle(String placeName);                  // 지역명 설정하기
}
