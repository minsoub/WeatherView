package com.hist.weatherview.weatherlife.main.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hist.item.weatherlife.WeatherLifeItem;
import com.hist.weatherview.R;

import java.util.ArrayList;


/***
 *  Author : JJW
 *  Date : 20180208
 *  Desc : 생활 기상 관련 리스트뷰 어뎁터 (BaseAdapter 기반)
 *  Remark : 현재사용안함
 */
public class WeatherLifeListViewAdapter extends BaseAdapter{
    private ArrayList<WeatherLifeItem> list = new ArrayList<WeatherLifeItem>();
    private Context ctx;

    public WeatherLifeListViewAdapter(){}

    public WeatherLifeListViewAdapter(Context ctx, ArrayList<WeatherLifeItem> items){
        this.list = items;
        this.ctx = ctx;
    }

    // 아이템 개수를 리턴한다.
    @Override
    public int getCount() {
        return list.size();
    }

    // 지정한 위치(position)에 있는 데이터 리턴
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // main_menu_item.xml의 main_menu_item Layout을 inflat하여 convertView 참조 획득.
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) ((Context) context).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_weather_life, parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.lifeweather_item_imgview);
        TextView txtStatus = (TextView) convertView.findViewById(R.id.txt_lifeweather_item_status);
        TextView txtDesc = (TextView) convertView.findViewById(R.id.txt_lifeweather_item_desc);


        // Data Set(listViewImteList)에서 position에 위치한 데이터 참조 획득
        WeatherLifeItem item = list.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        imageView.setImageDrawable(item.getIcon());
        txtStatus.setText(item.getStatus());
        txtDesc.setText(item.getStatus());

        return convertView;
    }

    // 아이템 데이터 추가를 위한 함수
    public void addItem(Drawable icon, String status, String desc)
    {
        WeatherLifeItem item = new WeatherLifeItem();
        item.setIcon(icon);
        item.setStatus(status);
        item.setDescription(desc);

        list.add(item);
    }
}
