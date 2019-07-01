package com.hist.repository.remote;


import com.hist.item.weatherlife.WeatherLifeBase;
import com.hist.item.weeklyweather.WeeklyWeatherBase;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 생활 기상 관련 레파지토리 레이어 인터페이스
 * 생활 기상 관련 api url 함수 정보
 */

public interface WeatherLifeRepository {
    /**
     * GET 요청메소드
     */
    @GET("{area}")
    Call<List<WeeklyWeatherBase>> findWeeklyWeatherListByArea(@Path("area") String area);

    @GET("fsnlife/{area}/{date}")
    Call<WeatherLifeBase> findWeatherLifeFsnLifeListByAreaAndDate(@Path("area") String area,
                                                                        @Path("date") String date);

    @GET("fsnlife/{area}/{date}")
    Call<WeatherLifeBase> findWeatherLifeSensorytemLifeListByAreaAndDate(@Path("area") String area,
                                                                  @Path("date") String date);

    @GET("all/{area}/{date}")
    Call<WeatherLifeBase> findWeatherLifeAllLifeListByAreaAndDate(@Path("area") String area,
                                                                  @Path("date") String date);
}
