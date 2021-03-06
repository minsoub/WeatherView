package com.hist.weatherview.timeweather.main.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hist.item.common.SharedPlaceInfo;
import com.hist.item.timeweather.TimeWeatherResult;
import com.hist.item.timeweather.TimeWeatherResultTime;
import com.hist.item.weatherlife.WeatherLifeItem;
import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.repository.local.SharedPrefersManager;
import com.hist.weatherview.R;
import com.hist.weatherview.common.comm.WeeklyWeatherActivityResultFlag;
import com.hist.weatherview.common.comm.dialog.TimeWeatherDialog;
import com.hist.weatherview.common.util.WeatherUtil;
import com.hist.weatherview.timeweather.main.fragment.forecast.base.TimeWeatherForecastFragment;
import com.hist.weatherview.timeweather.main.presenter.TimeWeatherPresenter;
import com.hist.weatherview.timeweather.main.presenter.impl.TimeWeatherPresenterImpl;
import com.hist.weatherview.timeweather.main.view.TimeWeatherView;
import com.hist.weatherview.weatherlife.area.base.WeatherLifeAreaActivity;
import com.hist.weatherview.weatherlife.main.adapter.WeatherLifeTypeListener;



import java.util.ArrayList;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 시간별 날씨 정보 베이스 액티비티
 * Author:JJW
 * Date: 2018.03.18
 * Remark:
 *  - 액티비티 (CollapsedToolBar) + Fragment 구조
 */

public class TimeWeatherActivity extends AppCompatActivity implements WeatherLifeTypeListener, TimeWeatherView {

    private static final String TAG = "WeeklyWeatherActivity";
    private TimeWeatherPresenter timeWeatherPresenter;
    private IncludedToolbarLayout includedToolbarLayout;            //탭 레이아웃
    private ProgressDialog progressDialog;
    private Handler progressDialogHandler;
    private SharedPrefersManager sharedPrefersManager;
    private TimeWeatherDialog timeWeatherDialog;
    private TimeWeatherForecastFragment timeWeatherForecastFragment;
    private SharedPlaceInfo startPlaceInfo;

    @BindView(R.id.in_weekly_weather_toolbar)
    View InWeeklyWeatherToolbar;
    @BindView(R.id.fl_weekly_weather_bottom_sheet)
    FrameLayout FlWeeklyWeatherBottomSheet;
    @BindView(R.id.tv_area)
    TextView TvArea;
    @BindView(R.id.tv_weather_type)
    TextView TvWeatherType;
    @BindView(R.id.tv_temp_max)
    TextView TvTempMax;
    @BindView(R.id.tv_temp_min)
    TextView TvTempMin;
    @BindView(R.id.iv_image)
    ImageView IvImage;
    @BindView(R.id.tv_temp_reh)
    TextView TvTempReh;
    @BindView(R.id.tv_temp_pop)
    TextView TvTempPop;
    @BindView(R.id.tv_temp_vvv)
    TextView TvTempVvv;

    /*@BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.timeWeatherPresenter = new TimeWeatherPresenterImpl(this);
        this.timeWeatherForecastFragment = new TimeWeatherForecastFragment(this);
        setContentView(R.layout.activity_time_weather_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_time_weather, timeWeatherForecastFragment)
                    .commit();
        }

        ButterKnife.bind(this);
        this.progressDialog = new ProgressDialog(this);
        this.progressDialogHandler = new Handler();
        this.sharedPrefersManager = new SharedPrefersManager(this);

        this.timeWeatherPresenter.init();     //View Init
        this.timeWeatherPresenter.onCreateView();
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
                        this.timeWeatherPresenter.onActivityResultTimeWeatherSearchAreaResultOK(weeklyWeather, position);*/

                        String areaCode = data.getStringExtra("areaCode");
                        String areaName = data.getStringExtra("areaName");
                        TvArea.setText(areaName);
                        if(this.startPlaceInfo == null)
                        {
                            this.startPlaceInfo = new SharedPlaceInfo(areaCode, areaName);
                        }else
                        {
                            this.startPlaceInfo.setPlaceName(areaName);
                            this.startPlaceInfo.setPlaceCode(areaCode);
                        }
                        this.sharedPrefersManager.setTimeWeatherStartPlace(startPlaceInfo);
                        timeWeatherPresenter.onActivityResultForTimeWeatherAreaResultOk(areaCode, areaName);
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

                        ArrayList<SharedPlaceInfo> placeInfos = this.sharedPrefersManager.getTimeWeatherFavoritePlaceArrayListPref();
                        if(placeInfos == null)
                        {
                            ArrayList<SharedPlaceInfo> tmp = new ArrayList<>();
                            tmp.add(new SharedPlaceInfo(areaCode, areaName));
                            this.sharedPrefersManager.setTimeWeatherFavoritePlaceArrayListPref(tmp);
                        }else {
                            placeInfos.add(new SharedPlaceInfo(areaCode, areaName));
                            this.sharedPrefersManager.setTimeWeatherFavoritePlaceArrayListPref(placeInfos);
                        }


                        // 프리젠터 요청
                        this.timeWeatherPresenter.onActivityResultTimeWeatherFavoriteAreaResultOK(areaCode, areaName);
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
        TvArea.setText(area.getAreaName());
        // 1. List Fragment를 새롭게 생성한다.
        /*getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_weekly_weather, new TimeWeatherForecastFragment(this, area))
                .commitAllowingStateLoss();*/
    }

    @Override
    public void deleteWeeklyWeatherFavoriteArea(ArrayList<SharedPlaceInfo> placeInfoArrayList, int index) {
        // 선택한 즐겨찾기 지역을 삭제 한다.
        placeInfoArrayList.remove(index);
        this.sharedPrefersManager.setTimeWeatherFavoritePlaceArrayListPref(placeInfoArrayList);
    }

    @Override
    public void getTimeWeatherForecastByAreaAndDate(String areaCode, String s) {
        // 지역 선택 후 시간별 조회를 수행한다.
        timeWeatherForecastFragment.getTimeWeatherForecastByAreaAndDate(areaCode);
    }

    @Override
    public void navigateToWeeklyWeatherFavoriteAreaFail() {
        this.showMessage("즐겨찾기 장소를 더 이상 추가 할 수 없습니다.");
        this.timeWeatherDialog.dismiss();
    }


    /**
     *  지역명을 설정한다.
     * @param placeName
     */
    @Override
    public void setTimeWeatherPlaceTitle(String placeName) {
        TvArea.setText(placeName);
    }

    @Override
    public void navigateToWeeklyWeatherSearchArea() {
        Intent intent = null;
        //intent = new Intent(TimeWeatherActivity.this, WeeklyWeatherAreaActivity.class);
        intent = new Intent(TimeWeatherActivity.this, WeatherLifeAreaActivity.class);       // 생활 기상 지역 선택으로 변경
        startActivityForResult(intent, WeeklyWeatherActivityResultFlag.WEEKLY_WEATHER_SEARCH_AREA_REQUEST);
    }

    @Override
    public void navigateToWeeklyWeatherFavoriteArea() {
        //Intent intent = new Intent(TimeWeatherActivity.this, WeeklyWeatherAreaActivity.class);
        Intent intent = null;
        intent = new Intent(TimeWeatherActivity.this, WeatherLifeAreaActivity.class);       // 생활 기상 지역 선택으로 변경
        startActivityForResult(intent, WeeklyWeatherActivityResultFlag.WEEKLY_WEATHER_FAVORITE_AREA_REQUEST);
    }

    @Override
    public void setWeeklyWeatherSearchArea(String area) {

    }

    @Override
    public void setWeeklyWeatherTodayForecast(TimeWeatherResult today) {
        // 오늘날짜 날씨를 업데이트 한다.
        // 1. 지역 설정
        // 2. 온도 설정
        TvTempMax.setText(getString(R.string.format_temperature, Double.parseDouble(WeatherUtil.getTimeWeatherResultTimeValueByCategory(today, "T3H"))));
        TvTempMin.setText(getString(R.string.format_temperature, Double.parseDouble(WeatherUtil.getTimeWeatherResultTimeValueByCategory(today, "T3H"))));
        // 3. 이미지 설정
        IvImage.setImageResource(WeatherUtil.getSkyImageByValue(WeatherUtil.getTimeWeatherResultTimeValueByCategory(today, "SKY")));
        // 4. 날씨 타입 설정
        TvWeatherType.setText(WeatherUtil.getSkyTypeStringByValue(WeatherUtil.getTimeWeatherResultTimeValueByCategory(today, "SKY")));
        // 5. 습도 설정
        TvTempReh.setText((WeatherUtil.getTimeWeatherResultTimeValueByCategory(today, "REH")));
        // 6. 강수 확률
        TvTempPop.setText((WeatherUtil.getTimeWeatherResultTimeValueByCategory(today, "POP")));
        // 7. 풍속 설저이
        TvTempVvv.setText((WeatherUtil.getTimeWeatherResultTimeValueByCategory(today, "VVV")));
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
        TvArea.setText(startPlaceInfo.getPlaceName());
        timeWeatherForecastFragment.getTimeWeatherForecastByAreaAndDate(placeInfo.getPlaceCode());
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
        ArrayList<SharedPlaceInfo> placeInfos = this.sharedPrefersManager.getTimeWeatherFavoritePlaceArrayListPref();
        if(timeWeatherDialog != null)
        {
            timeWeatherDialog = null;
        }
        timeWeatherDialog = new TimeWeatherDialog(this, placeInfos);
        timeWeatherDialog.show();
        timeWeatherDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override public void onDismiss(DialogInterface dialog) {
               /* String addCategoryStr = weeklyWeatherDialog.getAddCategoryStr(); //Something To-do */
            }
        });

    }

    @Override
    public void onChangeWeatherLifeType(WeatherLifeItem weatherLifeItem, String type) {

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
