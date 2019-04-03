package com.hist.repository.remote;


import com.hist.item.weeklyweather.WeeklyWeather;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 주간 기상 관련 레파지토리 레이어 인터페이스
 * 주간 기상 관련 api url 함수 정보
 */

public interface WeeklyWeatherRepository {
    /**
     * GET 요청메소드
     */
    @GET("{area}")
    Call<List<WeeklyWeather>> findWeeklyWeatherListByArea(@Path("area") String area);

}
