package com.hist.weatherview.weatherlife.main.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hist.item.weatherlife.WeatherLifeBase;
import com.hist.item.weatherlife.WeatherLifeBaseData;
import com.hist.item.weatherlife.WeatherLifeBaseItem;
import com.hist.item.weatherlife.WeatherLifeItem;
import com.hist.weatherview.weatherlife.main.adapter.WeatherLifePagerAdapter;
import com.hist.weatherview.weatherlife.main.adapter.WeatherLifeListViewAdapter;
import com.hist.weatherview.weatherlife.main.adapter.WeatherLifeListViewArrayAdapter;
import com.hist.weatherview.weatherlife.main.adapter.WeatherLifeTypeListener;
import com.hist.weatherview.R;
import com.hist.weatherview.weatherlife.area.base.WeatherLifeAreaActivity;
import com.hist.weatherview.weatherlife.main.presenter.WeatherLifeMainPresenter;
import com.hist.weatherview.weatherlife.main.presenter.impl.WeatherLifeMainPresenterImpl;
import com.hist.weatherview.weatherlife.main.view.WeatherLifeMainView;

import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 생활 기상 액티비티
 * Author:JJW
 * Date: 2018.01.29
 * Remark: 현재 사용 안함
 */

public class WeatherLifeActivity extends AppCompatActivity implements WeatherLifeTypeListener, WeatherLifeMainView{


    private static final int REQUEST_WEATHERLIFE_AREA =  1;

    private Context context;
    private WeatherLifeMainPresenter weatherLifeMainPresenter;
    private ProgressDialog progressDialog;
    private Handler progressDialogHandler;


    private WeatherLifeListViewAdapter[] listViewAdapter;
    private WeatherLifeListViewArrayAdapter[] listViewArrayAdapter;

    private WeatherLifeBase weatherLifeBase = new WeatherLifeBase();

    /*ListView[] listViews = new ListView[7];
    Vector<View> pages = new Vector<View>();*/
    ListView[] listViews = null;
    Vector<View> pages = null;

    /* ViewPager */
    //private ViewPager mViewPager;
    /* ViewPagerIndicator */

    @BindView(R.id.tabDots)
    TabLayout mTabLayout;
    /* 생활기상 타입 */
    @BindView(R.id.lifewather_type)
    TextView mLifewatherType;
    /* 생활기상 지역 */
    @BindView(R.id.edit_weatherlife_area_search)
    EditText mLifeWeatherAreaSearch;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.ll_weather_life_no_data)
    LinearLayout mLlWeatherLifeNoDataContainer;

    @BindView(R.id.rl_weather_life_data_container)
    RelativeLayout mRlWeatherLifeDataContainer;

    @BindView(R.id.txt_weather_life_no_data)
    TextView mTxtWeatherLifeNoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_life);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("생활 기상 정보");
        this.context = this;
        this.progressDialog = new ProgressDialog(context);
        this.progressDialogHandler = new Handler();

        this.weatherLifeMainPresenter = new WeatherLifeMainPresenterImpl(this);
        this.weatherLifeMainPresenter.init();
    }



    // 장소 검색 이 후 결과 화면으로 리턴한다..
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check that the result was from the autocomplete widget.
        if (requestCode == REQUEST_WEATHERLIFE_AREA) {
            //다른 액티비티로 부터 받음 코드..
            if (resultCode == RESULT_OK) {      // -1
                // Get the user's selected place from the Intent.
                String areaCode = data.getStringExtra("areaCode");
                String areaName = data.getStringExtra("areaName");


                weatherLifeMainPresenter.onActivityResultForWeatherLifeAreaResultOk(areaCode, areaName);
                mLifeWeatherAreaSearch.setText(areaName);
            } else if (resultCode == RESULT_CANCELED) {
                // Indicates that the activity closed before a selection was made. For example if
                // the user pressed the back button.
            }
        }
    }

    /**
     *  뷰 어뎁터로부터 뷰 이동시 액티비티로 받는 핸들러
     * @param weatherLifeItem
     * @param type
     */
    @Override
    public void onChangeWeatherLifeType(WeatherLifeItem weatherLifeItem, String type) {
        weatherLifeMainPresenter.onChangeWeatherLifeType(weatherLifeItem, type);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_weatherlife_toolbar, menu);

        MenuItem mSearch = menu.findItem(R.id.action_search);
        return super.onCreateOptionsMenu(menu);
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
        // 메시지  현시
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void clearWeatherLifeAdapter() {
        // listViewArrayAdapter = new WeatherLifeListViewArrayAdapter[7];
        if (listViewArrayAdapter != null) {
            listViewArrayAdapter = null;
        }
    }

    @Override
    public void showEmptyWeatherLifeView() {

    }

    @Override
    public void setWeatherLifeFsnLifeListAdapter(WeatherLifeBaseItem weatherLifeBase) {

        //listViewAdapter[0]
/*        listViewArrayAdapter[0] = new WeatherLifeListViewArrayAdapter(this, R.layout.list_item_weather_life, weatherLifeBase.getItem());
        listViews[0].setAdapter(listViewArrayAdapter[0]);*/


    }

    @Override
    public void setWeatherLifeSensorytemLifeListAdapter(com.hist.item.weatherlife.WeatherLifeBase weatherLifeBase) {

    }

    @Override
    public void setWeatherLifeAllLifeListAdapter(WeatherLifeBase weatherLifeBase) {
        this.weatherLifeBase = weatherLifeBase;
    }

    @Override
    public void setTabLayoutWithViewPager(int size) {
        for(int i = 0 ; i < size ; i++)
        {
            listViews[i] = new ListView(this);
            pages.add(listViews[i]);
        }
        //mViewPager = (ViewPager)findViewById(R.id.viewpager);
        WeatherLifePagerAdapter adapter = new WeatherLifePagerAdapter(this,pages);
        mViewPager.setAdapter(adapter);


        WeatherLifeBaseData weatherLifeBaseData = weatherLifeBase.getData();
        //int size = weatherLifeBaseData.getItems().size();

        for(int i = 0 ; i < size ; i++)
        {
            WeatherLifeBaseItem item = weatherLifeBaseData.getItems().get(i);
            //listview array ada[ter 생성
            listViewArrayAdapter[i] = new WeatherLifeListViewArrayAdapter(this, R.layout.list_item_weather_life, item.getItem());
        }

        for(int i = 0 ; i < size ; i++)
        {
            ListView listView = listViews[i];
            //listView.setAdapter(listViewAdapter[i]);          //BaseAdapter 사용
            listView.setAdapter(listViewArrayAdapter[i]);       //ArrayAdapter 사용
        }

        mTabLayout.setupWithViewPager(mViewPager, true);
    }

    @Override
    public void setOnChangeWeatherLifeType(String text, WeatherLifeItem weatherLifeItem) {
        mLifewatherType.setText(text);
        int itemCount = weatherLifeItem.getResult().size();
        if(itemCount == 0)
        {
            mLlWeatherLifeNoDataContainer.setVisibility(View.VISIBLE);
            String errMsg = weatherLifeItem.getHeader().getErrMsg();
            mTxtWeatherLifeNoData.setText(errMsg);
        }else
        {
            mLlWeatherLifeNoDataContainer.setVisibility(View.GONE);
        }
    }


    @Override
    public void clearListViewArrayAdapter() {

        if(listViewArrayAdapter != null)
            listViewAdapter = null;
    }

    @Override
    public void setListViewArrayAdapter(int size) {

        //listViewAdapter = new WeatherLifeListViewAdapter[7];
        listViewArrayAdapter = new WeatherLifeListViewArrayAdapter[size];

    }


    @Override
    @OnClick(R.id.edit_weatherlife_area_search)
    public void onClickAreaSearch() {
        this.showMessage("해당 기능은 준비 중입니다.");
        //weatherLifeMainPresenter.onClickAreaSearch();
    }

    @Override
    public void navigateToWeatherLifeAreaActivity() {

        Intent intent = null;
        intent = new Intent(WeatherLifeActivity.this, WeatherLifeAreaActivity.class);
        startActivityForResult(intent, REQUEST_WEATHERLIFE_AREA);
    }

    @Override
    public void clearListViewsAndPages() {
        if(listViews != null)
            listViews = null;
        if(pages != null)
            pages = null;

    }

    @Override
    public void setListViewsAndPages(int size) {
        listViews = new ListView[size];
        pages = new Vector<View>();
    }

    @Override
    public void setWeatherLifePlaceTilte(String placeName) {
        mLifeWeatherAreaSearch.setText(placeName);
    }

}
