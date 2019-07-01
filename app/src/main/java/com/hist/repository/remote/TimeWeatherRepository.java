package com.hist.repository.remote;


import com.hist.item.timeweather.TimeWeatherBase;
import com.hist.item.weeklyweather.WeeklyWeatherBase;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 시간별 기상 관련 레파지토리 레이어 인터페이스
 * 시간별 기상 관련 api url 함수 정보
 */

public interface TimeWeatherRepository {
    /**
     * GET 요청메소드
     */

    /**
     * 지역, 날짜 및 시간에 따른 동네 예보 정보 조회
     * @param date
     * @param time
     * @param nx
     * @param ny
     * @return
     */
    @GET("space/{date}/{time}/{nx}/{ny}")
    Call<TimeWeatherBase> findTimeWeatherSpaceByDateAndTimeAndArea(@Path("date") String date,
                                                                       @Path("time") String time,
                                                                       @Path("nx") String nx,
                                                                       @Path("ny") String ny);





}
