package com.hist.weatherview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.content.Intent;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class AirportDetailActivity extends AppCompatActivity implements OnMapReadyCallback{
    private MapView mapView;
    private GoogleMap gmap;
    private static final String MAP_VIEW_BUNDLE_KEY = "AIzaSyCtiYft9f3JWTbBICeXInsM0zFUhvPEOFc";
    private double lat;
    private double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_detail);

        Intent intent = getIntent();
        TextView icao_nm = (TextView)findViewById(R.id.icao_name);
        TextView crt_dt = (TextView)findViewById(R.id.airport_crt_dt);
        TextView wind_directiron = (TextView)findViewById(R.id.wind_direction);
        TextView wind_speed = (TextView)findViewById(R.id.wind_speed);
        TextView yang_wind_direction = (TextView)findViewById(R.id.yang_wind_direction);
        TextView eyes_view = (TextView)findViewById(R.id.eyes_view);
        TextView tempo = (TextView)findViewById(R.id.tempo);
        TextView qnh = (TextView)findViewById(R.id.qnh);

        // 데이터 세팅
        icao_nm.setText(intent.getExtras().getString("icao_nm"));
        lat = Double.parseDouble(intent.getExtras().getString("lat"));
        lon = Double.parseDouble(intent.getExtras().getString("lon"));

        // 측정일시
        crt_dt.setText("12월 12일 01시");

        // 풍향
        wind_directiron.setText("350");
        // 풍속
        wind_speed.setText("9 Knot");

        // 양극단풍향
        yang_wind_direction.setText("280V350");

        // 시정
        eyes_view.setText("10km 이상");

        // 기온
        tempo.setText("4 ℃");

        // 기압
        qnh.setText("1,032 hpa");


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
}
