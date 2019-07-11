package com.hist.weatherview.common.placeinfo.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.weatherview.R;
import com.hist.weatherview.common.placeinfo.presenter.PlaceInfoPresenter;
import com.hist.weatherview.common.placeinfo.presenter.impl.PlaceInfoPresenterImpl;
import com.hist.weatherview.common.placeinfo.view.PlaceInfoView;
import com.hist.weatherview.weatherlife.area.adapter.WeatherLifeAreaDataParser;
import com.hist.weatherview.weatherlife.area.adapter.WeatherLifeAreaDataParserListener;
import com.hist.weatherview.weatherlife.area.adapter.WeatherLifeAreaExpandableListViewAdapter;
import com.hist.weatherview.weatherlife.area.adapter.WeatherLifeAreaListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 장소 선택 공통 액티비티
 * Author:JJW
 * Date: 2018.01.29
 * Remark: 현재 사용 안함
 */
public class PlaceInfoActivity extends AppCompatActivity implements PlaceInfoView{

    private PlaceInfoPresenter placeInfoPresenter;
  //  private IncludedToolbarLayout includedToolbarLayout;            //탭 레이아웃
    private ProgressDialog progressDialog;
    private Handler progressDialogHandler;



    private ArrayAdapter mAdapter;
    private WeatherLifeAreaListViewAdapter mAreaAdapter;
    private WeatherLifeAreaExpandableListViewAdapter mExpandalbeAreaAdapter;

    private ListView mListView;
    private TextView mEmptyView;

    //Test용
    private LinkedHashMap<String, String> linkedHashMap;


    
    /* ProgressBar */
    private ProgressDialog mProgressDialog;

    @BindView(R.id.emptyView)
    TextView TvEmptView;
    @BindView(R.id.expandable_list_view_weather_life_area)
    ExpandableListView ExListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_life_area);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        placeInfoPresenter = new PlaceInfoPresenterImpl(this, this);
        //ButterKnife Bind
        ButterKnife.bind(this);
        this.progressDialog = new ProgressDialog(this);
        this.progressDialogHandler = new Handler();

        placeInfoPresenter.init();
        placeInfoPresenter.onCreateView();
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

        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAreaAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }

   /* private void GenerateWeatherLifeArea()
    {
        WeatherLifeAreaDataParser parser = new WeatherLifeAreaDataParser(this);
        parser.LoadWeatherLifePlaceInfo();
    }*/

    //Dummy 리스트를 만든다.
    private HashMap<String, List<String>> MakeDummy()
    {
        linkedHashMap = new LinkedHashMap<String, String>();

        linkedHashMap.put("1100000000", "서울");
        linkedHashMap.put("1111000000", "종로구");

        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> seoul = new ArrayList<String>();
        seoul.add("서울특별시");
        seoul.add("종로구");
        seoul.add("중구");
        seoul.add("용산구");
        seoul.add("성동구");
        seoul.add("광진구");
        seoul.add("동대문구");
        seoul.add("중랑구");
        seoul.add("성북구");
        seoul.add("도봉구");
        seoul.add("노원구");
        seoul.add("은평구");
        seoul.add("서대문구");
        seoul.add("마포구");
        seoul.add("양천구");
        seoul.add("강서구");
        seoul.add("구로구");
        seoul.add("금천구");
        seoul.add("영등포구");
        seoul.add("동작구");
        seoul.add("관악구");
        seoul.add("서초구");
        seoul.add("강남구");
        seoul.add("관악구");

        List<String> busan = new ArrayList<String>();
        busan.add("부산광역시");
        busan.add("중구");
        busan.add("서구");
        busan.add("동구");
        busan.add("영도구");

        expandableListDetail.put("서울특별시", seoul);
        expandableListDetail.put("부산광역시", busan);

        WeatherLifeAreaDataParser parser = new WeatherLifeAreaDataParser(this);
        parser.LoadWeatherLifePlaceInfo();


        return expandableListDetail;
    }

    private HashMap<String, List<String>> MakeKeyDummy()
    {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> seoul = new ArrayList<String>();
        seoul.add("1100000000");
        seoul.add("1111000000");
        seoul.add("1114000000");
        seoul.add("1117000000");
        seoul.add("1120000000");
        seoul.add("1121500000");
        seoul.add("1123000000");
        seoul.add("1126000000");
        seoul.add("1129000000");
        seoul.add("도봉구");
        seoul.add("노원구");
        seoul.add("은평구");
        seoul.add("서대문구");
        seoul.add("마포구");
        seoul.add("양천구");
        seoul.add("강서구");
        seoul.add("구로구");
        seoul.add("금천구");
        seoul.add("영등포구");
        seoul.add("동작구");
        seoul.add("관악구");
        seoul.add("서초구");
        seoul.add("강남구");
        seoul.add("관악구");

        List<String> busan = new ArrayList<String>();
        busan.add("부산광역시");
        busan.add("중구");
        busan.add("서구");
        busan.add("동구");
        busan.add("영도구");

        expandableListDetail.put("서울특별시", seoul);
        expandableListDetail.put("부산광역시", busan);
        return expandableListDetail;
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

    }

    @Override
    public void showToolbarTitle(String title) {

    }

    @Override
    public void init() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void onClickCancel() {

    }

    @Override
    public void onClickCurrentPosition() {

    }

    @Override
    public void navigateToGuFragment(WeeklyWeatherArea area) {

    }

    @Override
    public void navigateToDongFragment(WeeklyWeatherArea area) {

    }

    @Override
    public void navigateToWeeklyWeatherMain(WeeklyWeatherArea area) {

    }

    @Override
    public void setPlaceInfoExpandableListViewAdapterItem( List<String> mCityList,  HashMap<String, List<String>> mCityListDetail,  HashMap<String, List<String>> mCityKeyListDetail) {

        final List<String> cityList = mCityList;
        final HashMap<String, List<String>> cityListDetail = mCityListDetail;
        final HashMap<String, List<String>> cityKeyListDetail = mCityKeyListDetail;

        mExpandalbeAreaAdapter = new WeatherLifeAreaExpandableListViewAdapter(this, cityList, cityListDetail);

        ExListView.setAdapter(mExpandalbeAreaAdapter);

        /* Event Handler */
        /*ExListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        mCityList.get(groupPosition)
                                + " -> "
                                + mCityListDetail.get(
                                mCityList.get(groupPosition)).get(
                                childPosition)
                                +" , Key -> "
                                + mCityKeyListDetail.get(
                                mCityList.get(groupPosition)).get(
                                childPosition)
                        , Toast.LENGTH_SHORT
                )
                        .show();

                Intent intent = new Intent();
                intent.putExtra("areaCode", mCityKeyListDetail.get(
                        mCityList.get(groupPosition)).get(
                        childPosition));
                intent.putExtra("areaName", mCityList.get(groupPosition) + " " + mCityListDetail.get(
                        mCityList.get(groupPosition)).get(
                        childPosition));
                setResult(RESULT_OK, intent);
                finish();
                return false;
            }
        });*/
    }
}
