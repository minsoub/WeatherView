package com.hist.weatherview;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

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
                "날시 정보", "날시정보를 안내합니다", "0003");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_alarm_add_black_36dp),
                "주간 날씨", "주간날씨 정보를 안내합니다", "0004");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_alarm_black_36dp),
                "생활 기상", "생활기상 정보를 안내합니다", "0005");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_assessment_black_36dp),
                "지진 정보", "지진정보를 안내합니다", "0006");

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
                        break;
                    case "0004":   // 주간 날씨
                        break;
                    case "0005":   // 생활 기상
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
}
