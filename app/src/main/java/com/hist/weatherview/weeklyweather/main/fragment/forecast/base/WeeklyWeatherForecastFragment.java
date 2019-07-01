package com.hist.weatherview.weeklyweather.main.fragment.forecast.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hist.item.weeklyweather.WeeklyWeatherBaseItem;
import com.hist.item.weeklyweather.WeeklyWeatherBase;
import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.weatherview.R;
import com.hist.weatherview.weeklyweather.main.base.WeeklyWeatherActivity;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.adapter.WeeklyWeatherForecastRecycleViewAdapter;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.presenter.WeeklyWeatherForecastPresenter;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.presenter.impl.WeeklyWeatherForecastPresenterImpl;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.view.WeeklyWeatherForecastView;
import com.hist.weatherview.weeklyweather.main.view.WeeklyWeatherView;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 주간 날씨 정보 예보 정보 프래그먼트
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 *  - 완성
 */
public class WeeklyWeatherForecastFragment extends Fragment implements WeeklyWeatherForecastView{

    private Context context;
    private WeeklyWeatherForecastPresenter weeklyWeatherForecastPresenter;
    private WeeklyWeatherForecastRecycleViewAdapter weeklyWeatherForecastRecycleViewAdapter;

    private RecyclerView rvList;
    private ProgressDialog progressDialog;
    private Handler progressDialogHandler;
    private WeeklyWeatherArea area;


    @BindView(R.id.rv_weekly_weather_forecast)
    RecyclerView RvWeeklyWeatherForecast;

    @BindView(R.id.ll_weekly_weather_forecast_empty)
    LinearLayout LlWeeklyWeatherForecastEmpty;

    public WeeklyWeatherForecastFragment() {
    }

    @SuppressLint("ValidFragment")
    public WeeklyWeatherForecastFragment(Context mainActivity) {
        this.context = mainActivity;
    }

    @SuppressLint("ValidFragment")
    public WeeklyWeatherForecastFragment(WeeklyWeatherActivity weeklyWeatherActivity, WeeklyWeatherArea area) {
        this.context = weeklyWeatherActivity;
        this.area = area;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
        this.progressDialog = new ProgressDialog(context);
        this.progressDialogHandler = new Handler();

        this.weeklyWeatherForecastPresenter = new WeeklyWeatherForecastPresenterImpl(this);
        this.weeklyWeatherForecastPresenter.init(this.area);  // 임시
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_weekly_weather_forecast, container, false);
        ButterKnife.bind(this, view);
        this.weeklyWeatherForecastPresenter.onCreateView();
        return view;
    }

    @Override
    public void showProgressDialog() {
        // 로딩 현시
        progressDialog = new ProgressDialog(context);
        progressDialog.show();
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.setContentView(R.layout.dialog_all_pregress);
    }

    @Override
    public void goneProgressDialog() {
        // 로딩 제거
        progressDialogHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        }, 10);
    }

    @Override
    public void init() {
        // 초기화 작업

    }

    @Override
    public void showMessage(String message) {
        // 메시지  현시
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

/*    @Override
    public void setWeeklyWeatherListRecycleViewAdapterItem(List<WeeklyWeatherBase> weeklyWeatherList) {
        // 어뎁터 설정 하기
        weeklyWeatherForecastRecycleViewAdapter = new WeeklyWeatherForecastRecycleViewAdapter(weeklyWeatherForecastPresenter, weeklyWeatherList, context);
        RvWeeklyWeatherForecast.setAdapter(weeklyWeatherForecastRecycleViewAdapter);
        RvWeeklyWeatherForecast.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
    }*/
// 일단 주석

    @Override
    public void clearWeeklyWeatherAdapter() {
        // 어뎁터 설정 초기화
        if (weeklyWeatherForecastRecycleViewAdapter != null) {
            weeklyWeatherForecastRecycleViewAdapter = null;
        }
    }


    @Override
    public void setWeeklyWeatherToday(WeeklyWeatherBase today) {
        // 오늘날짜 정보를 Activity로 넘긴다.
        ((WeeklyWeatherView)context).setWeeklyWeatherTodayForecast(today);
    }

    @Override
    public void showEmptyWeeklyWeatherView() {
        // 빈 리스트뷰 현시
        LlWeeklyWeatherForecastEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void setWeeklyWeatherListRecycleViewAdapterItem(List<WeeklyWeatherBaseItem> data) {
        // 어뎁터 설정 하기
        weeklyWeatherForecastRecycleViewAdapter = new WeeklyWeatherForecastRecycleViewAdapter(weeklyWeatherForecastPresenter, data, context);
        RvWeeklyWeatherForecast.setAdapter(weeklyWeatherForecastRecycleViewAdapter);
        RvWeeklyWeatherForecast.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void setWeeklyWeatherMiddleForecast(WeeklyWeatherBase weeklyWeathersBase) {
        ((WeeklyWeatherView)context).setWeeklyWeatherMiddleForecast(weeklyWeathersBase);

    }
}
