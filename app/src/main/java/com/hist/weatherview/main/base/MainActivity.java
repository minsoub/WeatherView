package com.hist.weatherview.main.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hist.item.main.MainMenuItem;
import com.hist.repository.local.SharedPrefersManager;
import com.hist.weatherview.AirportActivity;
import com.hist.weatherview.MenuItemAdapter;
import com.hist.weatherview.MenuViewItem;
import com.hist.weatherview.R;
import com.hist.weatherview.main.adapter.MainMenuRecycleViewAdapter;
import com.hist.weatherview.main.presenter.MainPresenter;
import com.hist.weatherview.main.presenter.impl.MainPresenterImpl;
import com.hist.weatherview.main.view.MainView;
import com.hist.weatherview.thunderstroke.ThunderStrokeDetailActivity;
import com.hist.weatherview.timeweather.main.base.TimeWeatherActivity;
import com.hist.weatherview.weatherlife.main.base.WeatherLifeActivity;
import com.hist.weatherview.weeklyweather.main.base.WeeklyWeatherActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mainPresenter;
    private MainMenuRecycleViewAdapter mainMenuRecycleViewAdapter;


    @BindView(R.id.rv_main_menu)
    RecyclerView RvMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mainPresenter = new MainPresenterImpl(this);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        this.mainPresenter.init();     //View Init
        this.mainPresenter.onCreateView();

    }

    public void test()
    {
        ListView listview;
        MenuItemAdapter adapter;

        // Adapter 생성
        adapter = new MenuItemAdapter();

        // 리스트뷰 참조 및 Adapter 달기
        listview = (ListView)findViewById(R.id.listview1);  // main.xml
        listview.setAdapter(adapter);

        // 아이템 추가
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_account_balance_black_36dp),
                "공항 기상정보", "공항별 기상정보를 안내합니다", "0001");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_account_balance_wallet_black_36dp),
                "낙뢰 정보", "낙뢰정보를 안내합니다", "0002");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_account_box_black_36dp),
                "날씨 정보", "날시정보를 안내합니다", "0003");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_alarm_add_black_36dp),
                "주간 날씨", "주간날씨 정보를 안내합니다", "0004");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_alarm_black_36dp),
                "생활 기상", "생활기상 정보를 안내합니다", "0005");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_assessment_black_36dp),
                "미세먼지 정보", "미세먼지 정보를 안내합니다", "0006");

        // 이벤트 처리
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long ld) {
                // get Item
                MenuViewItem item = (MenuViewItem)parent.getItemAtPosition(position);
                String titleStr = item.getTitleStr();
                String descStr = item.getDescStr();
                String key = item.getKey();
                Toast.makeText(MainActivity.this, key, Toast.LENGTH_LONG).show();

                Intent intent = null;
                switch (key)
                {
                    case "0001":   // 공항기상정보
                        intent = new Intent(MainActivity.this, AirportActivity.class);
                        break;
                    case "0002":   // 낙뢰정보
                        intent = new Intent(MainActivity.this, ThunderStrokeDetailActivity.class);
                        break;
                    case "0003":   // 날씨 정보
                        intent = new Intent(MainActivity.this, TimeWeatherActivity.class);
                        break;
                    case "0004":   // 주간 날씨
                        intent = new Intent(MainActivity.this, WeeklyWeatherActivity.class);
                        break;
                    case "0005":   // 생활 기상
                        intent = new Intent(MainActivity.this, WeatherLifeActivity.class);
                        break;
                    case "0006":   // 지진 정보
                        break;
                    default:
                        break;
                }
                startActivity(intent);
            }
        });
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void goneProgressDialog() {

    }

    @Override
    public void init() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void clearMainMenuRecycleViewAdapter() {

    }

    @Override
    public void setMainMenuRecycleViewAdapterItem(List<MainMenuItem> data) {
        mainMenuRecycleViewAdapter = new MainMenuRecycleViewAdapter(mainPresenter, data, this, R.layout.list_item_main);
        RvMainMenu.setAdapter(mainMenuRecycleViewAdapter);
        RvMainMenu.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void navigateToMainMenuActivity(MainMenuItem mainMenuItem, int position) {

        String key = mainMenuItem.getKey();
        Intent intent = null;
        switch (key)
        {
            case "0001":   // 공항기상정보
                intent = new Intent(MainActivity.this, AirportActivity.class);
                break;
            case "0002":   // 낙뢰정보
                intent = new Intent(MainActivity.this, ThunderStrokeDetailActivity.class);
                break;
            case "0003":   // 날씨 정보
                intent = new Intent(MainActivity.this, TimeWeatherActivity.class);
                break;
            case "0004":   // 주간 날씨
                intent = new Intent(MainActivity.this, WeeklyWeatherActivity.class);
                break;
            case "0005":   // 생활 기상
                intent = new Intent(MainActivity.this, WeatherLifeActivity.class);
                break;
            case "0006":   // 지진 정보
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
