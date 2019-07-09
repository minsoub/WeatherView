package com.hist.repository.remote;


import com.hist.item.PlaceInfo.PlaceInfoResult;
import com.hist.item.weeklyweather.WeeklyWeatherBase;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 지역 정보 코드 관련 레파지토리 레이어 인터페이스
 * 지역 정보 api url 함수 정보
 */

public interface PlaceInfoRepository {
    /**
     * GET 요청메소드
     */

    /**
     * 지역 코드를 바탕으로 해당 지역의 상세 정보를 가져온다.
     * @param areaCode
     * @return
     */
    @GET("info/{code}")
    Call<PlaceInfoResult> findPlaceInfoByArea(@Path("code") String areaCode
                                                        );


}
