package com.hist.repository.remote;


import com.hist.item.weeklyweather.WeeklyWeatherBase;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 낙룆 정보 관련 레파지토리 레이어 인터페이스
 * 낙뢰 정보 관련 api url 함수 정보
 */

public interface ThunderStrokeRepository {
    /**
     * GET 요청메소드
     */
    @GET("{area}")
    Call<List<WeeklyWeatherBase>> findWeeklyWeatherListByArea(@Path("area") String area);

}
