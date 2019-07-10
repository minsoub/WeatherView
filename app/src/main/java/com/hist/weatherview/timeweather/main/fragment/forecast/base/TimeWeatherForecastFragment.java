package com.hist.weatherview.timeweather.main.fragment.forecast.base;

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

import com.hist.item.common.SharedPlaceInfo;
import com.hist.item.timeweather.TimeWeatherBase;
import com.hist.item.timeweather.TimeWeatherResult;
import com.hist.item.timeweather.TimeWeatherResultTime;
import com.hist.item.weeklyweather.WeeklyWeatherArea;

import com.hist.repository.local.SharedPrefersManager;
import com.hist.weatherview.R;
import com.hist.weatherview.timeweather.main.base.TimeWeatherActivity;
import com.hist.weatherview.timeweather.main.fragment.forecast.adapter.TimeWeatherForecastRecycleViewAdapter;
import com.hist.weatherview.timeweather.main.fragment.forecast.presenter.TimeWeatherForecastPresenter;
import com.hist.weatherview.timeweather.main.fragment.forecast.presenter.impl.TimeWeatherForecastPresenterImpl;
import com.hist.weatherview.timeweather.main.fragment.forecast.view.TimeWeatherForecastView;

import com.hist.weatherview.timeweather.main.view.TimeWeatherView;
import com.hist.weatherview.weeklyweather.main.view.WeeklyWeatherView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 시간별 날씨 정보 예보 정보 프래그먼트
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 *  - 완성
 */
public class TimeWeatherForecastFragment extends Fragment implements TimeWeatherForecastView {

    private Context context;
    private TimeWeatherForecastPresenter timeWeatherForecastPresenter;
    private TimeWeatherForecastRecycleViewAdapter timeWeatherForecastRecycleViewAdapter;

    private RecyclerView rvList;
    private ProgressDialog progressDialog;
    private Handler progressDialogHandler;
    private WeeklyWeatherArea area;
    private SharedPlaceInfo startPlaceInfo;
    private SharedPrefersManager sharedPrefersManager;


    @BindView(R.id.rv_weekly_weather_forecast)
    RecyclerView RvWeeklyWeatherForecast;

    @BindView(R.id.ll_weekly_weather_forecast_empty)
    LinearLayout LlWeeklyWeatherForecastEmpty;

    public TimeWeatherForecastFragment() {
    }

    @SuppressLint("ValidFragment")
    public TimeWeatherForecastFragment(Context mainActivity) {
        this.context = mainActivity;
    }

    @SuppressLint("ValidFragment")
    public TimeWeatherForecastFragment(TimeWeatherActivity timeWeatherActivity, WeeklyWeatherArea area) {
        this.context = timeWeatherActivity;
        this.area = area;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
        this.progressDialog = new ProgressDialog(context);
        this.progressDialogHandler = new Handler();
        this.sharedPrefersManager = new SharedPrefersManager(context);

        this.timeWeatherForecastPresenter = new TimeWeatherForecastPresenterImpl(this);
        this.timeWeatherForecastPresenter.init(this.area);  // 임시
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_weekly_weather_forecast, container, false);
        ButterKnife.bind(this, view);
        this.timeWeatherForecastPresenter.onCreateView();

        this.startPlaceInfo = this.sharedPrefersManager.getTimeWeatherStartPlace();
        if(this.startPlaceInfo == null)
        {
            this.startPlaceInfo = new SharedPlaceInfo("1100000000", "서울특별시");
        }
        ((TimeWeatherView)context).setTimeWeatherPlaceTitle(this.startPlaceInfo.getPlaceName());

        this.getTimeWeatherForecastByAreaAndDate(this.startPlaceInfo.getPlaceCode());


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

// 일단 주석

    @Override
    public void clearTimeWeatherAdapter() {
        // 어뎁터 설정 초기화
        if (timeWeatherForecastRecycleViewAdapter != null) {
            timeWeatherForecastRecycleViewAdapter = null;
        }
    }


    @Override
    public void setTimeWeatherToday(TimeWeatherResult today) {
        // 오늘날짜 정보를 Activity로 넘긴다.
        ((TimeWeatherView)context).setWeeklyWeatherTodayForecast(today);
    }

    @Override
    public void showEmptyTimeWeatherView() {
        // 빈 리스트뷰 현시
        LlWeeklyWeatherForecastEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void setTimeWeatherListRecycleViewAdapterItem(List<TimeWeatherResult> data) {
        // 어뎁터 설정 하기
        timeWeatherForecastRecycleViewAdapter = new TimeWeatherForecastRecycleViewAdapter(timeWeatherForecastPresenter, data, context);
        RvWeeklyWeatherForecast.setAdapter(timeWeatherForecastRecycleViewAdapter);
        RvWeeklyWeatherForecast.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
    }

    public void getTimeWeatherForecastByAreaAndDate(String areaCode) {
        //presenter로 넘김
        this.timeWeatherForecastPresenter.getTimeWeatherForecastByAreaAndDate(areaCode);
    }
}
