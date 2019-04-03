package com.hist.weatherview.weatherlife.area.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hist.weatherview.weatherlife.area.adapter.WeatherLifeAreaDataParser;
import com.hist.weatherview.weatherlife.area.adapter.WeatherLifeAreaDataParserListener;
import com.hist.weatherview.weatherlife.area.adapter.WeatherLifeAreaExpandableListViewAdapter;
import com.hist.weatherview.weatherlife.area.adapter.WeatherLifeAreaListViewAdapter;
import com.hist.weatherview.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * 생활 기상 장소 선택 액티비티
 * Author:JJW
 * Date: 2018.01.29
 * Remark: 현재 사용 안함
 */
public class WeatherLifeAreaActivity extends AppCompatActivity implements WeatherLifeAreaDataParserListener{

    private ArrayAdapter mAdapter;
    private WeatherLifeAreaListViewAdapter mAreaAdapter;
    private ListView mListView;
    private TextView mEmptyView;

    //Test용
    private LinkedHashMap<String, String> linkedHashMap;

    /* Expandable List View */
    private WeatherLifeAreaExpandableListViewAdapter mExpandalbeAreaAdapter;
    private List<String> mCityList;
    private HashMap<String, List<String>> mCityListDetail;
    private HashMap<String, List<String>> mCityKeyListDetail;
    private ExpandableListView mExpandableListView;
    
    /* ProgressBar */
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_life_area);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // mListView = (ListView) findViewById(R.id.list);
        mEmptyView = (TextView) findViewById(R.id.emptyView);

        /* Expandable List View */
        mExpandableListView = (ExpandableListView) findViewById(R.id.expandable_list_view_weather_life_area);

        GenerateWeatherLifeArea();
        /*mAdapter = new ArrayAdapter(WeatherLifeAreaActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.months_array));*/
//        mAreaAdapter = new WeatherLifeAreaListViewAdapter(WeatherLifeAreaActivity.this, linkedHashMap);

        /*주석
        mListView.setAdapter(mAreaAdapter);

        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                StringBuilder builder = new StringBuilder();
                builder.append(adapterView.getItemAtPosition(i).toString());
                builder.append("-");
                builder.append(mAreaAdapter.GetKeyName(i));
                Toast.makeText(WeatherLifeAreaActivity.this, builder.toString(), Toast.LENGTH_SHORT).show();

                //System.out.println("---------result code : " + resultCode + ", mPlacedetails : " + mPlaceDetailsText);
                Intent intent = new Intent();
                intent.putExtra("areaCode", mAreaAdapter.GetKeyName(i));
                intent.putExtra("areaName", adapterView.getItemAtPosition(i).toString());
                setResult(RESULT_OK, intent);
                finish();

            }
        });

        mListView.setEmptyView(mEmptyView);
        */
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

        /*
        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu_weatherlife_toolbar, menu);
        // Associate searchable_weather_life configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
        */
    }

    private void GenerateWeatherLifeArea()
    {
        WeatherLifeAreaDataParser parser = new WeatherLifeAreaDataParser(this);
        parser.LoadWeatherLifePlaceInfo();
    }

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
    public void OnStartParsing() {
        mProgressDialog = ProgressDialog.show(this, "",
                "불러오는중입니다.", true);
    }

    @Override
    public void OnFinishParsing(HashMap<String, List<String>> expandableListDetail, HashMap<String, List<String>> expandableKeyListDetail) {
        if(mProgressDialog.isShowing())
        {
            mProgressDialog.dismiss();
        }
        mCityListDetail = expandableListDetail;
        mCityKeyListDetail = expandableKeyListDetail;
        mCityList = new ArrayList<String>(this.mCityListDetail.keySet());
        mExpandalbeAreaAdapter = new WeatherLifeAreaExpandableListViewAdapter(this, mCityList, mCityListDetail);
        mExpandableListView.setAdapter(mExpandalbeAreaAdapter);

        /* Event Handler */
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
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
        });
    }
}
