package com.hist.weatherview.weatherlife.area.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.hist.weatherview.R;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class WeatherLifeAreaExpandableListViewAdapter extends BaseExpandableListAdapter{
    private List<String> cityList;
    private HashMap<String, List<String>> cityListDetail;
    private String[] keys;
    private Context context;

    public WeatherLifeAreaExpandableListViewAdapter(Context context, List<String> cityList,
                                 HashMap<String, List<String>> cityListDetail) {
        this.context = context;
        this.cityList = cityList;
        this.cityListDetail = cityListDetail;
    }

    @Override
    public int getGroupCount() {
        return this.cityList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.cityListDetail.get(this.cityList.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return this.cityList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.cityListDetail.get(this.cityList.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String listTitle = (String) getGroup(i);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_group_item_weather_life_area, null);
        }
        TextView listTitleTextView = (TextView) view
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final String expandedListText = (String) getChild(i, i1);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_item_weather_life_area, null);
        }
        TextView expandedListTextView = (TextView) view
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
