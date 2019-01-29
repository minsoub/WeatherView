package com.hist.weatherview;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.hist.adapater.AirportItemAdapter;
import com.hist.item.AirportInfoItem;

public class AirportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport);

        ListView listview;
        AirportItemAdapter adapter;

        // Adapter 생성
        adapter = new AirportItemAdapter();

        // ListView 참조 및 Adapater 달기
        listview = (ListView)findViewById(R.id.airport_listview);       // activity_airport.xml에 define
        listview.setAdapter(adapter);

        // 아이템 추가
        itemAdapaterAdd(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get Item
                AirportInfoItem item = (AirportInfoItem)parent.getItemAtPosition(position);

                // Activity call
                Intent intent = new Intent(AirportActivity.this, AirportDetailActivity.class);
                intent.putExtra("icao_cd", item.getIcao_cd());
                intent.putExtra("icao_nm", item.getIcao_nm());
                intent.putExtra("lat", item.getLat());
                intent.putExtra("lon", item.getLon());
                startActivity(intent);
            }
        });

    }
    // Adapater에 데이터를 추가한다.
    private void itemAdapaterAdd(AirportItemAdapter adapter)
    {
        String[] icao_cd = { "RKSI", "RKSS", "RKPC", "RKPK", "RKNY", "RKNW", "RKTU", "RKTN", "RKTH", "RKJJ", "RKJB", "RKJY", "RKPU", "RKPS", "RKJK"};
        String[] icao_nm = { "인천공항", "김포공항", "제주공항", "김해공항", "양양공항", "원주공항", "청주공항", "대구공항", "포항공항", "광주공항", "무안공항", "여수공항", "울산공항", "사천공항", "군산공항"};
        String[] lat = {"37.4692", "37.5583", "33.5114", "35.1403", "38.0588", "37.4592", "36.7167", "35.8942", "35.9847", "35.1264", "34.9914", "34.8422", "35.5936", "35.0886", "35.9039" };
        String[] lon = {"126.451", "126.791", "126.493", "129.063", "128.663", "127.977", "127.499", "128.659", "129.434", "126.809", "126.383",  "127.617", "129.352", "128.07", "126.616"};

        for (int i=0; i<icao_cd.length; i++) {
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_airplanemode_active_black_24dp),
                    icao_cd[i], icao_nm[i], lat[i], lon[i]);
        }
    }
}
