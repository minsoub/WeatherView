package com.hist.weatherview.thunderstroke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.hist.weatherview.R;

import java.time.LocalDateTime;

/**
 * 낙뢰정보 상세 액티비티
 * Author:JJW
 * Date: 2018.01.29
 */

public class ThunderStrokeDetailActivity extends AppCompatActivity implements OnMapReadyCallback{
    private MapView mapView;
    private GoogleMap gmap;
    private static final String MAP_VIEW_BUNDLE_KEY = "AIzaSyAWMwxCCZVniziKPb7ciknuuYRxoum0Pdg";
    private double lat;
    private double lon;

    // TextView
    private TextView mThunderStrokeTime;
    private TextView mThunderStrokePosition;
    private TextView mThunderStrokeLat;
    private TextView mThunderStrokeLon;
    private TextView mThunderStrokeStrong;
    private TextView mThunderStrokeType;

    //Time
    private LocalDateTime mLocalDateTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thunderstroke_detail);

        Intent intent = getIntent();

        mThunderStrokeTime = (TextView)findViewById(R.id.thunderstroke_time);
        mThunderStrokePosition = (TextView)findViewById(R.id.thunderstroke_position);
        mThunderStrokeLat = (TextView)findViewById(R.id.thunderstroke_lat);
        mThunderStrokeLon = (TextView)findViewById(R.id.thunderstroke_lon);
        mThunderStrokeStrong = (TextView)findViewById(R.id.thunderstroke_strong);
        mThunderStrokeType = (TextView)findViewById(R.id.thunderstroke_type);

        SetThunderStrokeInfo();

        // Map
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.map_view);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(12);
        LatLng ny = new LatLng(lat, lon);  // 40.7143528, -74.0059731);
        gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));
    }

    private void SetThunderStrokeInfo()
    {
        mThunderStrokeTime.setText("1월 9일 10시 5분");
        mThunderStrokePosition.setText("춘천\n대관령");
        mThunderStrokeLat.setText("35.854");
        mThunderStrokeLon.setText("126.555");
        mThunderStrokeStrong.setText("-9.4");
        mThunderStrokeType.setText("대지방전");
    }
}
