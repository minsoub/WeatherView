package com.hist.adapater.weatherlife;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/***
 *  Author : JJW
 *  Date : 20180211
 *  Desc : 생활 기상 지역 선택 시 지역을 현시 하기 위한 리스트뷰 어뎁터 (BaseAdapter 기반)
 */
public class WeatherLifeAreaListViewAdapter extends BaseAdapter implements Filterable{

    private LinkedHashMap<String, String> areas;
    private String[] keys;
    private Context context;
    private List<String> arrayListNames;

    public WeatherLifeAreaListViewAdapter(Context context, LinkedHashMap<String, String> areaMap) {
        this.areas = areaMap;
        this.keys = areas.keySet().toArray(new String[areaMap.size()]);
        this.context = context;
    }

    @Override
    public int getCount() {
        return areas.size();
    }

    @Override
    public Object getItem(int i) {
        return areas.get(keys[i]);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String key = keys[position];
        String value = getItem(position).toString();

        final int pos = position;
        final Context context = parent.getContext();

        // main_menu_item.xml의 main_menu_item Layout을 inflat하여 convertView 참조 획득.
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) ((Context) context).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView txtAreaName = (TextView) convertView.findViewById(android.R.id.text1);
        txtAreaName.setText(value);

        return convertView;
    }

    public String GetKeyName(int position) {
        String retVal = this.keys[position];
        return retVal;
    }


    // 필드 구현 예정..
    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                arrayListNames = (List<String>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<String> FilteredArrayNames = new ArrayList<String>();

                // perform your search here using the searchConstraint String.

                constraint = constraint.toString().toLowerCase();
                /*for (int i = 0; i < mDatabaseOfNames.size(); i++) {
                    String dataNames = mDatabaseOfNames.get(i);
                    if (dataNames.toLowerCase().startsWith(constraint.toString()))  {
                        FilteredArrayNames.add(dataNames);
                    }
                }*/

                results.count = FilteredArrayNames.size();
                results.values = FilteredArrayNames;
                Log.e("VALUES", results.values.toString());

                return results;
            }
        };

        return filter;
    }
}
