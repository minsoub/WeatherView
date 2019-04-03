package com.hist.weatherview.weeklyweather.area.base;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.weatherview.R;
import com.hist.weatherview.weeklyweather.area.fragment.list.base.WeeklyWeatherAreaListFragment;
import com.hist.weatherview.weeklyweather.area.presenter.WeeklyWeatherAreaPresenter;
import com.hist.weatherview.weeklyweather.area.presenter.impl.WeeklyWeatherAreaPresenterImpl;
import com.hist.weatherview.weeklyweather.area.view.WeeklyWeatherAreaView;
import com.hist.weatherview.weeklyweather.comm.WeeklyWeatherActivityResultFlag;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 주간 날씨 정보 지역설정 베이스 액티비티
 * Author:JJW
 * Date: 2018.03.29
 * Remark:
 *  - 툴바만 Fragment FrameLayout 만 제공
 */

public class WeeklyWeatherAreaActivity extends AppCompatActivity implements WeeklyWeatherAreaView {

    private static final String TAG = "WeeklyWeatherAreaActivity";

    public static int REQUEST_CITY = 0;
    public static int REQUEST_GU = 1;
    public static int REQUEST_DONG = 2;

    private WeeklyWeatherAreaPresenter weeklyWeatherAreaPresenter;
    private IncludedToolbarLayout includedToolbarLayout;            //탭 레이아웃
    private ProgressDialog progressDialog;
    private Handler progressDialogHandler;


    @BindView(R.id.in_weekly_weather_toolbar)
    View InWeeklyWeatherToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.weeklyWeatherAreaPresenter = new WeeklyWeatherAreaPresenterImpl(this);

        setContentView(R.layout.activity_weekly_weather_area);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_weekly_weather_area, new WeeklyWeatherAreaListFragment(this, REQUEST_CITY))
                    .commit();
        }

        ButterKnife.bind(this);
        this.progressDialog = new ProgressDialog(this);
        this.progressDialogHandler = new Handler();

        setSupportActionBar((Toolbar) InWeeklyWeatherToolbar);
        this.weeklyWeatherAreaPresenter.init();     //View Init
        this.weeklyWeatherAreaPresenter.onCreateView();
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
        includedToolbarLayout = new IncludedToolbarLayout();
        ButterKnife.bind(includedToolbarLayout, InWeeklyWeatherToolbar);
    }

    @Override
    public void showToolbarTitle(String title) {
        includedToolbarLayout.TvToolbarWeeklyWeatherTitle.setText(title);
        // 필요정보 숨기기/현시하기
        includedToolbarLayout.IbToolBarWeeklyWeatherMore.setVisibility(View.GONE);
        includedToolbarLayout.IbToolBarWeeklyWeatherCancel.setVisibility(View.VISIBLE);
        includedToolbarLayout.IbToolBarWeeklyWeatherCurrentPosition.setVisibility(View.VISIBLE);
    }

    @Override
    public void init() {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
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
        /*finish();*/
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }

    @Override
    @OnClick(R.id.ib_toolbar_weekly_weather_current_position)
    public void onClickCurrentPosition() {
        // 현재 위치 클릭 시
        // 아직 미 구현
    }

    @Override
    public void navigateToGuFragment(WeeklyWeatherArea area) {
        //Gu Fragment로 변경한다.
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_weekly_weather_area, new WeeklyWeatherAreaListFragment(this, REQUEST_GU))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void navigateToDongFragment(WeeklyWeatherArea area) {
        //Dong Fragment로 변경한다.
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_weekly_weather_area, new WeeklyWeatherAreaListFragment(this, REQUEST_DONG))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void navigateToWeeklyWeatherMain(WeeklyWeatherArea area) {
        // 지역정보와 함께 메인화면으로 이동한다.
        Intent intent = getIntent();
        intent.putExtra("position", 0);
        intent.putExtra("weeklyweather",area);
        setResult(WeeklyWeatherActivityResultFlag.RESULT_OK, intent);
        finish();
    }

    @Override
    @OnClick(R.id.ib_toolbar_weekly_weather_cancel)
    public void onClickCancel() {
        //취소 버튼 클릭
        Intent intent = getIntent();
        setResult(WeeklyWeatherActivityResultFlag.RESULT_CANCEL, intent);
        finish();
    }

    static class IncludedToolbarLayout {
        @BindView(R.id.ib_toolbar_weekly_weather_back)
        ImageButton IbToolBarWeeklyWeatherBack;
        @BindView(R.id.tv_toolbar_weekly_weather_title)
        TextView TvToolbarWeeklyWeatherTitle;
        @BindView(R.id.ib_toolbar_weekly_weather_more)
        ImageButton IbToolBarWeeklyWeatherMore;
        @BindView(R.id.ib_toolbar_weekly_weather_cancel)
        ImageButton IbToolBarWeeklyWeatherCancel;
        @BindView(R.id.ib_toolbar_weekly_weather_current_position)
        ImageButton IbToolBarWeeklyWeatherCurrentPosition;

    }
}
