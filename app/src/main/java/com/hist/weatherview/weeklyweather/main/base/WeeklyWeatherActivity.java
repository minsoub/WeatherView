package com.hist.weatherview.weeklyweather.main.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.hist.item.common.SharedPlaceInfo;
import com.hist.item.weeklyweather.WeeklyWeatherBase;
import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.repository.local.SharedPrefersManager;
import com.hist.weatherview.R;
import com.hist.weatherview.common.area.base.WeeklyWeatherAreaActivity;
import com.hist.weatherview.common.comm.WeeklyWeatherActivityResultFlag;
import com.hist.weatherview.common.comm.dialog.WeeklyWeatherDialog;
import com.hist.weatherview.weatherlife.area.base.WeatherLifeAreaActivity;

import com.hist.weatherview.weeklyweather.main.fragment.forecast.base.WeeklyWeatherForecastFragment;
import com.hist.weatherview.weeklyweather.main.presenter.WeeklyWeatherPresenter;
import com.hist.weatherview.weeklyweather.main.presenter.impl.WeeklyWeatherPresenterImpl;
import com.hist.weatherview.weeklyweather.main.view.WeeklyWeatherView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 주간 날씨 정보 베이스 액티비티
 * Author:JJW
 * Date: 2018.03.18
 * Remark:
 *  - 액티비티 (CollapsedToolBar) + Fragment 구조
 *  - 주간 날씨는 주간 기상 정보 + 주간 날씨 정보 형태로 구성한다.
 */

public class WeeklyWeatherActivity extends AppCompatActivity implements WeeklyWeatherView {

    private static final String TAG = "WeeklyWeatherActivity";
    private WeeklyWeatherPresenter weeklyWeatherPresenter;
    private IncludedToolbarLayout includedToolbarLayout;            //탭 레이아웃
    private ProgressDialog progressDialog;
    private Handler progressDialogHandler;
    private WeeklyWeatherForecastFragment weeklyWeatherForecastFragment;
    private WeeklyWeatherDialog weeklyWeatherDialog;
    private SharedPrefersManager sharedPrefersManager;
    private SharedPlaceInfo startPlaceInfo;


    @BindView(R.id.in_weekly_weather_toolbar)
    View InWeeklyWeatherToolbar;
    @BindView(R.id.fl_weekly_weather_bottom_sheet)
    FrameLayout FlWeeklyWeatherBottomSheet;


    @BindView(R.id.tv_forecast_title)
    TextView TvForecastTitle;
    @BindView(R.id.tv_forecast_description)
    TextView TvForecastDescription;
    @BindView(R.id.tv_forecast_area)
    TextView TvForecastArea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.weeklyWeatherPresenter = new WeeklyWeatherPresenterImpl(this);
        this.weeklyWeatherForecastFragment = new WeeklyWeatherForecastFragment(this);
        setContentView(R.layout.activity_weekly_weather_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_weekly_weather, weeklyWeatherForecastFragment)
                    .commit();
        }

        ButterKnife.bind(this);
        this.progressDialog = new ProgressDialog(this);
        this.progressDialogHandler = new Handler();
        this.sharedPrefersManager = new SharedPrefersManager(this);

        this.weeklyWeatherPresenter.init();     //View Init
        this.weeklyWeatherPresenter.onCreateView();


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 액티비티 요청 결과 처리
        switch (requestCode) {
            // 지역 선택 요청 시
            case WeeklyWeatherActivityResultFlag.WEEKLY_WEATHER_SEARCH_AREA_REQUEST:
                switch (resultCode) {
                    case WeeklyWeatherActivityResultFlag.RESULT_OK:
                        /*int position = data.getIntExtra("position", 0);
                        WeeklyWeatherArea weeklyWeather = (WeeklyWeatherArea) data.getSerializableExtra("weeklyweather");
                        // 프리젠터 요청
                        this.weeklyWeatherPresenter.onActivityResultWeeklyWeatherSearchAreaResultOK(weeklyWeather, position);
                        */
                        String areaCode = data.getStringExtra("areaCode");
                        String areaName = data.getStringExtra("areaName");
                        TvForecastArea.setText(areaName);
                        if(this.startPlaceInfo == null)
                        {
                            this.startPlaceInfo = new SharedPlaceInfo(areaCode, areaName);
                        }else
                        {
                            this.startPlaceInfo.setPlaceName(areaName);
                            this.startPlaceInfo.setPlaceCode(areaCode);
                        }
                        this.sharedPrefersManager.setWeeklyWeatherStartPlace(startPlaceInfo);
                        weeklyWeatherPresenter.onActivityResultForWeatherLifeAreaResultOk(areaCode, areaName);
                        break;

                    case WeeklyWeatherActivityResultFlag.RESULT_CANCEL:
                        break;
                }
                break;

            case WeeklyWeatherActivityResultFlag.WEEKLY_WEATHER_FAVORITE_AREA_REQUEST:
                switch (resultCode) {
                    case WeeklyWeatherActivityResultFlag.RESULT_OK:
                        // 추가 등록 OK 시 프로세스
                        // 1. 즐겨찾기에 지역 저장
                        // 2. 해당 지역에 대해서 정보 요청
                        //int position = data.getIntExtra("position", 0);
                        //WeeklyWeatherArea weeklyWeather = (WeeklyWeatherArea) data.getSerializableExtra("weeklyweather");
                        String areaCode = data.getStringExtra("areaCode");
                        String areaName = data.getStringExtra("areaName");
                        // SharedPreference 등록 하고 조회 한다.

                        ArrayList<SharedPlaceInfo> placeInfos = this.sharedPrefersManager.getWeeklyWeatherFavoritePlaceArrayListPref();
                        if(placeInfos == null)
                        {
                            ArrayList<SharedPlaceInfo> tmp = new ArrayList<>();
                            tmp.add(new SharedPlaceInfo(areaCode, areaName));
                            this.sharedPrefersManager.setWeeklyWeatherFavoritePlaceArrayListPref(tmp);
                        }else {
                            placeInfos.add(new SharedPlaceInfo(areaCode, areaName));
                            this.sharedPrefersManager.setWeeklyWeatherFavoritePlaceArrayListPref(placeInfos);
                        }


                        // 프리젠터 요청
                        this.weeklyWeatherPresenter.onActivityResultWeeklyWeatherFavoriteAreaResultOK(areaCode, areaName);
                        //searchTagPresenter.onActivityResultForStoryResultEdit(position, story);
                        break;
                    case WeeklyWeatherActivityResultFlag.RESULT_DELETE:
                        break;
                    case WeeklyWeatherActivityResultFlag.RESULT_CANCEL:
                        break;
                }
                break;
        }

    }


    @Override
    public void showProgressDialog() {
        // 로딩 현시
        progressDialog = new ProgressDialog(this);
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
    public void setToolbarLayout() {
        // 툴바 설정
        includedToolbarLayout = new IncludedToolbarLayout();
        ButterKnife.bind(includedToolbarLayout, InWeeklyWeatherToolbar);
    }

    @Override
    public void showToolbarTitle(String title) {
        // 툴바 타이틀 설정
        includedToolbarLayout.TvToolbarWeeklyWeatherTitle.setText(title);
    }

    @Override
    public void init() {
        // NavigationDraw 버전
        /*NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setCheckable(true);
                //drawerLayout.closeDrawers();
                Intent intent = null;
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.ni_place:
                        Toast.makeText(WeeklyWeatherActivity.this, "장소설정", Toast.LENGTH_LONG).show();
                        intent = new Intent(WeeklyWeatherActivity.this, WeeklyWeatherAreaActivity.class);
                        startActivityForResult(intent, WeeklyWeatherActivityResultFlag.WEEKLY_WEATHER_SEARCH_AREA_REQUEST);
                        break;
                    case R.id.ni_favorite:
                        Toast.makeText(WeeklyWeatherActivity.this, "즐겨찾기", Toast.LENGTH_LONG).show();
                        intent = new Intent(WeeklyWeatherActivity.this, WeeklyWeatherAreaActivity.class);
                        startActivityForResult(intent, WeeklyWeatherActivityResultFlag.WEEKLY_WEATHER_FAVORITE_AREA_REQUEST);
                        break;
                }
                //startActivity(intent);
                //startActivityForResult(intent, ActivityResultFlag.STORY_COMMENT_REQEUST);
                return true;
            }
        });*/

    }

    @Override
    public void showMessage(String message) {
        // 메시지 현시
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void weeklyWeatherAdapterNotifyItemChanged() {

    }

    @Override
    public void setWeeklyWeatherListFragmentChange() {
        // Fragment 변경
    }

    @Override
    public void setWeeklyWeatherListFragmentChange(WeeklyWeatherArea area) {
        // Fragment 변경
    }

    @Override
    public void weeklyWeatherAreaChanged(WeeklyWeatherArea area) {
        // WeeklyWeatherBase 지역 변경
        // 0. 지역정보 변경
        TvForecastArea.setText(area.getAreaName());
        // 1. List Fragment를 새롭게 생성한다.
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_weekly_weather, new WeeklyWeatherForecastFragment(this))
                .commitAllowingStateLoss();
    }

    @Override
    public void deleteWeeklyWeatherFavoriteArea(ArrayList<SharedPlaceInfo> placeInfoArrayList, int index) {
        // 선택한 즐겨찾기 지역을 삭제 한다.
        placeInfoArrayList.remove(index);
        this.sharedPrefersManager.setWeeklyWeatherFavoritePlaceArrayListPref(placeInfoArrayList);
        this.weeklyWeatherDialog.dismiss();
    }

    @Override
    public void setWeeklyWeatherMiddleForecast(WeeklyWeatherBase weeklyWeatherMiddleForecast) {

        // 오늘날짜 날씨를 업데이트 한다.
        // 1. 지역 설정
        /*TvArea.setText("서울특별시 강서구 가양동");
        // 2. 온도 설정
        TvTempMax.setText(getString(R.string.format_temperature, Double.parseDouble(getTimeWeatherResultTimeValueByCategory(today, "T3H"))));
        TvTempMin.setText(getString(R.string.format_temperature, Double.parseDouble(getTimeWeatherResultTimeValueByCategory(today, "T3H"))));
        // 3. 이미지 설정
        IvImage.setImageResource(getSkyImageByValue(getTimeWeatherResultTimeValueByCategory(today, "SKY")));
        // 4. 날씨 타입 설정
        TvWeatherType.setText("맑음");*/
        try {
            TvForecastTitle.setText(weeklyWeatherMiddleForecast.getData().getItems().get(0).getItem().getResult().get(0).getValue());
        }catch (Exception e)
        {
            TvForecastTitle.setText("오류!");
        }

    }

    /**
     * 지역 코드 선택 후 재조회
     * @param areaCode
     * @param s
     */
    @Override
    public void getWeeklyWeatherMiddleForecastByAreaAndDate(String areaCode, String s) {
        // fragment로 보낸다.
        weeklyWeatherForecastFragment.getWeeklyWeatherMiddleForecastByAreaAndDate(areaCode);
    }

    /**
     *  즐겨찾기 지역 초과한 경우
     */
    @Override
    public void navigateToWeeklyWeatherFavoriteAreaFail() {
        this.showMessage("즐겨찾기 장소를 더 이상 추가 할 수 없습니다.");
        this.weeklyWeatherDialog.dismiss();
    }

    /**
     *  지역명을 설정한다.
     * @param placeName
     */
    @Override
    public void setWeeklyWeatherPlaceTitle(String placeName) {
        TvForecastArea.setText(placeName);
    }

    /**
     *  지역 선택 화면으로 이동 한다.
     */
    @Override
    public void navigateToWeeklyWeatherSearchArea() {
        Intent intent = null;
        //intent = new Intent(WeeklyWeatherActivity.this, WeeklyWeatherAreaActivity.class);
        intent = new Intent(WeeklyWeatherActivity.this, WeatherLifeAreaActivity.class);       // 생활 기상 지역 선택으로 변경
        startActivityForResult(intent, WeeklyWeatherActivityResultFlag.WEEKLY_WEATHER_SEARCH_AREA_REQUEST);
    }

    /**
     *  지역 선택 화면으로 이동 한다.
     */
    @Override
    public void navigateToWeeklyWeatherFavoriteArea() {
        //Intent intent = new Intent(WeeklyWeatherActivity.this, WeeklyWeatherAreaActivity.class);
        Intent intent = null;
        intent = new Intent(WeeklyWeatherActivity.this, WeatherLifeAreaActivity.class);       // 생활 기상 지역 선택으로 변경
        startActivityForResult(intent, WeeklyWeatherActivityResultFlag.WEEKLY_WEATHER_FAVORITE_AREA_REQUEST);
    }

    @Override
    public void setWeeklyWeatherSearchArea(String area) {

    }

    @Override
    public void setWeeklyWeatherTodayForecast(WeeklyWeatherBase today) {
        // 오늘날짜 날씨를 업데이트 한다.
        // 1. 지역 설정
/*        TvArea.setText("서울특별시 강서구 가양동");
        // 2. 온도 설정
        TvTempMax.setText(getString(R.string.format_temperature, 31.1));
        TvTempMin.setText(getString(R.string.format_temperature, 11.1));
        // 3. 이미지 설정
        // 4. 날씨 타입 설정
        TvWeatherType.setText("맑음");*/
        TvForecastDescription.setText("");

    }

    @Override
    public void setWeeklyWeatherFavoriteArea(ArrayList<SharedPlaceInfo> placeInfoArrayList, int index) {

        SharedPlaceInfo placeInfo = placeInfoArrayList.get(index);
        if(this.startPlaceInfo == null)
        {
            this.startPlaceInfo = new SharedPlaceInfo(placeInfo.getPlaceCode(), placeInfo.getPlaceName());
        }else
        {
            this.startPlaceInfo.setPlaceName(placeInfo.getPlaceName());
            this.startPlaceInfo.setPlaceCode(placeInfo.getPlaceCode());
        }
        this.sharedPrefersManager.setWeeklyWeatherStartPlace(startPlaceInfo);
        TvForecastArea.setText(startPlaceInfo.getPlaceName());
        weeklyWeatherForecastFragment.getWeeklyWeatherMiddleForecastByAreaAndDate(placeInfo.getPlaceCode());
    }


/*
    @Override
    public void weeklyWeatherAdapterNotifyItemChanged(int position){
        if(searchTagStoryAdapter != null){
            searchTagStoryAdapter.notifyItemChanged(position);
        }
    }
*/
    //ButterKnife에서 메뉴 아이템 연동 미지원
/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.ni_place:
                Toast.makeText(this, "장소설정", Toast.LENGTH_LONG).show();
                break;
            case R.id.ni_favorite:
                Toast.makeText(this, "즐겨찾기", Toast.LENGTH_LONG).show();
                break;

        }

        return true;
    }*/

    @Override
    @OnClick(R.id.ib_toolbar_weekly_weather_back)
    public void onBackPressed() {
        finish();
    }

    @Override
    @OnClick(R.id.ib_toolbar_weekly_weather_more)
    public void onClickMore() {
        //drawerLayout.openDrawer(GravityCompat.START);
        if(weeklyWeatherDialog != null){
            weeklyWeatherDialog = null;
        }
        ArrayList<SharedPlaceInfo> placeInfos = this.sharedPrefersManager.getWeeklyWeatherFavoritePlaceArrayListPref();
        weeklyWeatherDialog = new WeeklyWeatherDialog(this, placeInfos);
        // SharedPref Loading

        weeklyWeatherDialog.show();
        weeklyWeatherDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override public void onDismiss(DialogInterface dialog) {
               /* String addCategoryStr = weeklyWeatherDialog.getAddCategoryStr(); //Something To-do */
            }
        });

    }

    static class IncludedToolbarLayout {
        @BindView(R.id.ib_toolbar_weekly_weather_back)
        ImageButton IbToolBarWeeklyWeatherBack;
        @BindView(R.id.tv_toolbar_weekly_weather_title)
        TextView TvToolbarWeeklyWeatherTitle;
        @BindView(R.id.ib_toolbar_weekly_weather_more)
        ImageButton IbToolBarWeeklyWeatherMore;
    }
}
