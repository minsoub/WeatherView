package com.hist.weatherview.weatherlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hist.adapater.weatherlife.WeatherLifeAreaListViewAdapter;
import com.hist.weatherview.R;

import java.util.LinkedHashMap;


/**
 * 생활 기상 장소 선택 액티비티
 * Author:JJW
 * Date: 2018.01.29
 * Remark: 현재 사용 안함
 */
public class WeatherLifeAreaActivity extends AppCompatActivity {

    ArrayAdapter mAdapter;
    WeatherLifeAreaListViewAdapter mAreaAdapter;
    ListView mListView;
    TextView mEmptyView;

    //Test용
    LinkedHashMap<String, String> linkedHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_life_area);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mListView = (ListView) findViewById(R.id.list);
        mEmptyView = (TextView) findViewById(R.id.emptyView);

        /*mAdapter = new ArrayAdapter(WeatherLifeAreaActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.months_array));*/

        MakeDummy();

        mAreaAdapter = new WeatherLifeAreaListViewAdapter(WeatherLifeAreaActivity.this, linkedHashMap);

        //mListView.setAdapter(mAdapter);
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

    //Dummy 리스트를 만든다.
    private void MakeDummy()
    {
        linkedHashMap = new LinkedHashMap<String, String>();

        linkedHashMap.put("1100000000", "서울");
        linkedHashMap.put("1111000000", "종로구");
    }
}
