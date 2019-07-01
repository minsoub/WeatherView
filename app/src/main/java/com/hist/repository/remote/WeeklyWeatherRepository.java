package com.hist.repository.remote;


import com.hist.item.weeklyweather.WeeklyWeatherBase;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 주간 기상 관련 레파지토리 레이어 인터페이스
 * 주간 기상 관련 api url 함수 정보
 */

public interface WeeklyWeatherRepository {
    /**
     * GET 요청메소드
     */

    /**
     * 지역, 날짜 및 시간에 따른 동네 예보 및 중기 예보 정보 조회
     * @param date
     * @param time
     * @param nx
     * @param ny
     * @return
     */
    @GET("forecast/all/{date}/{time}/{nx}/{ny}")
    Call<WeeklyWeatherBase> findWeeklyWeatherListByArea(@Path("date") String date,
                                                        @Path("time") String time,
                                                        @Path("nx") String nx,
                                                        @Path("ny") String ny);


    /**
     * 지역, 날짜 및 시간에 따른 동네 예보 정보 조회
     * @param date
     * @param time
     * @param nx
     * @param ny
     * @return
     */
    @GET("forecast/space/{date}/{time}/{nx}/{ny}")
    Call<WeeklyWeatherBase> findWeeklyWeatherSpaceByDateAndTimeAndArea(@Path("date") String date,
                                                                       @Path("time") String time,
                                                                       @Path("nx") String nx,
                                                                       @Path("ny") String ny);


    /**
     * 지역 및 날짜에 따른 중기 예보 정보 조회
     * @param date
     * @param regid
     * @return
     */
    @GET("forecast/middletemperature/{date}/{regid}")
    Call<List<WeeklyWeatherBase>> findWeeklyWeatherMiddleTemperatureByDateAndArea(@Path("date") String date,
                                                                                  @Path("regid") String regid
                                                                              );


    /**
     *  날짜와 지역에 따른 중기 육지 예보 정보 조회
     * @param date
     * @param regid
     * @return
     */
    @GET("forecast/middletemperature/{date}/{regid}")
    Call<List<WeeklyWeatherBase>> findWeeklyWeatherMiddleLandTemperatureByDateAndArea(@Path("date") String date,
                                                                                      @Path("regid") String regid);

    @GET("middleforecast/all/{time}/{landRegId}/{tempRegId}/{stnId}")
    Call<WeeklyWeatherBase> findWeeklyWeatherMiddleForecastAllByTimeAndRegIdAndStnId(@Path("time") String time,
                                                                                     @Path("landRegId") String landRegId,
                                                                                     @Path("tempRegId") String tempRegId,
                                                                                     @Path("stnId") String stnId);
}
