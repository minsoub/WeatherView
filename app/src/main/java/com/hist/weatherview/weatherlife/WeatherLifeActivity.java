package com.hist.weatherview.weatherlife;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.hist.adapater.weatherlife.WeatherLifePagerAdapter;
import com.hist.adapater.weatherlife.WeatherLifeListViewAdapter;
import com.hist.adapater.weatherlife.WeatherLifeListViewArrayAdapter;
import com.hist.adapater.weatherlife.WeatherLifeTypeListener;
import com.hist.item.weatherlife.WeatherLifeBase;
import com.hist.item.weatherlife.WeatherLifeItem;
import com.hist.item.weatherlife.WeatherLifeItemType1;
import com.hist.item.weatherlife.WeatherLifeItemType2;
import com.hist.item.weatherlife.WeatherLifePredictionItem;
import com.hist.item.weatherlife.WeatherLifeType;
import com.hist.weatherview.R;

import java.util.ArrayList;
import java.util.Vector;

/**
 * 생활 기상 액티비티
 * Author:JJW
 * Date: 2018.01.29
 * Remark: 현재 사용 안함
 */

public class WeatherLifeActivity extends AppCompatActivity implements WeatherLifeTypeListener{


    private static final int REQUEST_WEATHERLIFE_AREA =  1;

    private WeatherLifeListViewAdapter[] listViewAdapter;
    private WeatherLifeListViewArrayAdapter[] listViewArrayAdapter;

    /* ViewPager */
    private ViewPager mViewPager;
    /* ViewPagerIndicator */
    private TabLayout mTabLayout;
    /* 생활기상 타입 */
    private TextView mLifewatherType;
    /* 생활기상 지역 */
    private EditText mLifeWeatherAreaSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_life);

        mTabLayout = (TabLayout) findViewById(R.id.tabDots);

        mLifewatherType = (TextView) findViewById(R.id.lifewather_type);
        mLifeWeatherAreaSearch = (EditText) findViewById(R.id.edit_weatherlife_area_search);
        mLifeWeatherAreaSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(WeatherLifeActivity.this, WeatherLifeAreaActivity.class);
                startActivityForResult(intent, REQUEST_WEATHERLIFE_AREA);
            }
        });

        listViewAdapter = new WeatherLifeListViewAdapter[7];
        listViewArrayAdapter = new WeatherLifeListViewArrayAdapter[7];

        //MakeListView();
        // 테스트 아이템 생성하기
        //itemAdapaterAdd();
        InitLayout();
    }

    private void InitLayout(){
        //ListView 생성하기
        ListView[] listViews = new ListView[7];
        Vector<View> pages = new Vector<View>();
        for(int i = 0 ; i < 7 ; i++)
        {
            listViews[i] = new ListView(this);
            pages.add(listViews[i]);
        }
        mViewPager = (ViewPager)findViewById(R.id.viewpager);
        WeatherLifePagerAdapter adapter = new WeatherLifePagerAdapter(this,pages);
        mViewPager.setAdapter(adapter);

        //ViewSetup
        Test();

        for(int i = 0 ; i < 7 ; i++)
        {
            ListView listView = listViews[i];
            //listView.setAdapter(listViewAdapter[i]);          //BaseAdapter 사용
            listView.setAdapter(listViewArrayAdapter[i]);       //ArrayAdapter 사용
        }
        mTabLayout.setupWithViewPager(mViewPager, true);
    }

    private void RequestWeatherLifeInfo(){
        // no jobs
    }


    /**
     *  생활기상 정보 리스트 뷰를 생성한다.
     */
    private void Test()
    {
        // 총 7개의 생활기상 정보를 생성한다.
        //Dummy 넣는다.

        WeatherLifeBase[] items = new WeatherLifeBase[7];
        items[0] = new WeatherLifeItemType1(WeatherLifeType.FOOD_POISONING, "00", "1100", "2017061206", "71", "74", "63");
        items[1] = new WeatherLifeItemType1(WeatherLifeType.UV_INDEX, "00", "1100", "2017061206", "71", "74", "63");

        ArrayList<WeatherLifePredictionItem> items1 = new ArrayList<WeatherLifePredictionItem>();

        WeatherLifePredictionItem a1 = new WeatherLifePredictionItem("h1", "30");
        WeatherLifePredictionItem a2 = new WeatherLifePredictionItem("h2", "40");
        WeatherLifePredictionItem a3 = new WeatherLifePredictionItem("h3", "50");
        WeatherLifePredictionItem a4 = new WeatherLifePredictionItem("h4", "60");
        WeatherLifePredictionItem a5 = new WeatherLifePredictionItem("h6", "70");
        items1.add(a1);
        items1.add(a2);
        items1.add(a3);
        items1.add(a4);
        items1.add(a5);

        items[2] = new WeatherLifeItemType2(WeatherLifeType.AIR_POLLUTANT_INDEX, items1);
        items[3] = new WeatherLifeItemType2(WeatherLifeType.FREEZING_INDEX, items1);
        items[4] = new WeatherLifeItemType2(WeatherLifeType.HEAT_INDEX, items1);
        items[5] = new WeatherLifeItemType2(WeatherLifeType.SENSORY_TEMPERATURE, items1);
        items[6] = new WeatherLifeItemType2(WeatherLifeType.HUMIDEX, items1);


        //Adapter 등록하기
        for(int i = 0 ; i < 7 ; i++)
        {
            WeatherLifeBase base = items[i];
            listViewAdapter[i] = new WeatherLifeListViewAdapter(this,base.GetWeatherLifeItems());
            listViewArrayAdapter[i] = new WeatherLifeListViewArrayAdapter(this, R.layout.list_item_weather_life, base.GetWeatherLifeItems());
        }

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

                mLifeWeatherAreaSearch.setText(areaName);

            } else if (resultCode == RESULT_CANCELED) {
                // Indicates that the activity closed before a selection was made. For example if
                // the user pressed the back button.
            }
        }
    }

    @Override
    public void onChangeWeatherLifeType(String type) {
        mLifewatherType.setText(type);
    }
}
