package com.hist.weatherview.weeklyweather.area.fragment.list.base;

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
import android.widget.Toast;

import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.weatherview.R;
import com.hist.weatherview.common.area.fragment.list.presenter.impl.WeeklyWeatherAreaListPresenterImpl;
import com.hist.weatherview.weeklyweather.area.fragment.list.adapter.WeeklyWeatherAreaListRecycleViewAdapter;
import com.hist.weatherview.weeklyweather.area.fragment.list.presenter.WeeklyWeatherAreaListPresenter;
import com.hist.weatherview.weeklyweather.area.fragment.list.view.WeeklyWeatherAreaListView;
import com.hist.weatherview.weeklyweather.area.view.WeeklyWeatherAreaView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 주간 날씨 정보 지역설정 리스트 현시 프레그먼트
 * Author:JJW
 * Date: 2018.03.29
 * Remark:
 * - 시, 구, 동 구분해서 현시
 */

public class WeeklyWeatherAreaListFragment extends Fragment implements WeeklyWeatherAreaListView{

    private Context context;
    private WeeklyWeatherAreaListPresenter weeklyWeatherAreaListPresenter;
    private WeeklyWeatherAreaListRecycleViewAdapter weeklyWeatherAreaListRecycleViewAdapter;
    private ProgressDialog progressDialog;
    private Handler progressDialogHandler;


    private int requestType;

    @BindView(R.id.rv_weekly_weather_area_list)
    RecyclerView RvWeeklyWeatherAreaList;

    public WeeklyWeatherAreaListFragment() {
    }

    @SuppressLint("ValidFragment")
    public WeeklyWeatherAreaListFragment(Context mainActivity, int requestType) {
        this.context = mainActivity;
        this.requestType = requestType;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
        this.progressDialog = new ProgressDialog(context);
        this.progressDialogHandler = new Handler();

        this.weeklyWeatherAreaListPresenter = new WeeklyWeatherAreaListPresenterImpl(this, requestType);
        this.weeklyWeatherAreaListPresenter.init();  // 임시
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // 시, 구, 동 별 리스트 현시 할 수 있도록

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_weekly_weather_area_list, container, false);
        ButterKnife.bind(this, view);
        this.weeklyWeatherAreaListPresenter.onCreateView();
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

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setWeeklyWeatherAreaListRecycleViewAdapterItem(List<WeeklyWeatherArea> areas) {
        weeklyWeatherAreaListRecycleViewAdapter = new WeeklyWeatherAreaListRecycleViewAdapter(weeklyWeatherAreaListPresenter, areas, context);
        RvWeeklyWeatherAreaList.setAdapter(weeklyWeatherAreaListRecycleViewAdapter);
        RvWeeklyWeatherAreaList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void navigateToGuFragment(WeeklyWeatherArea area) {
        ((WeeklyWeatherAreaView)context).navigateToGuFragment(area);
    }

    @Override
    public void navigateToDongFragment(WeeklyWeatherArea area) {
        ((WeeklyWeatherAreaView)context).navigateToDongFragment(area);
    }

    @Override
    public void navigateToWeeklyWeatherMain(WeeklyWeatherArea area) {
        ((WeeklyWeatherAreaView)context).navigateToWeeklyWeatherMain(area);
    }

    /*
    @Override
    public void navigateToPlaceAttractionDetailActivity(PlaceAttraction placeAttraction, int position) {
        //Adapter 클릭시 프레젠터를 거쳐 해당 메서드 호출 한다.
        Intent intent = new Intent(context, PlaceDetailBaseActivity.class);
        intent.putExtra("placeAttraction", placeAttraction);
        intent.putExtra("position", position);
        //startActivityForResult(intent, ActivityResultFlag.STORY_COMMENT_REQEUST);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }*/

}
