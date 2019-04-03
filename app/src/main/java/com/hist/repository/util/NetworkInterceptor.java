package com.hist.repository.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * HTTP 연결 인터셉터
 * Author:JJW
 * Date: 2018.04.02
 * Remark:
 */

public class NetworkInterceptor implements Interceptor {
    private final String BASE_SERVER_API = "http://api.com/";

    private final String THUNDERSTROKE_URL = "";    // 낙뢰정보
    private final String WEEKLY_WEATHER_URL = "";    // 주간 날씨
    private final String WEATHER_LIFE_URL = "";    // 생활 기상

    private String accessToken = null;      //토큰
    public Retrofit retrofit;               //Retrofit
    private OkHttpClient client;            //클라이언트
    private Gson gson = new GsonBuilder().setLenient().create();



    public NetworkInterceptor() {
        super();
        client = new OkHttpClient.Builder()
                .addInterceptor(this)
                .build();
    }

    public NetworkInterceptor(String accessToken) {
        this.accessToken = accessToken;
        client = new OkHttpClient.Builder()
                .addInterceptor(this)
                .build();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (accessToken != null) {
            request = request.newBuilder()
                    .addHeader("Authorization", accessToken)
                    .build();
        } else {
            request = request.newBuilder().build();
        }

        Response response = chain.proceed(request);

        return response;
    }

    /**
     *  주간날씨 연결 레파지토리
     * @return
     */
    public Retrofit getWeeklyWeatherRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVER_API + WEEKLY_WEATHER_URL).addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit;
    }


    /**
     *  생활기상 연결 레파지토리
     * @return
     */
    public Retrofit getWeatherLifeRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVER_API + WEATHER_LIFE_URL).addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit;
    }

    /**
     *  낙뢰 정보 연결 레파지토리
     * @return
     */
    public Retrofit getThunderStrokeRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVER_API + THUNDERSTROKE_URL).addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit;
    }


}
