package com.hist.adapater;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hist.item.AirportInfoItem;
import com.hist.weatherview.R;

import java.util.ArrayList;

public class AirportItemAdapter  extends BaseAdapter {

    private ArrayList<AirportInfoItem> list = new ArrayList<AirportInfoItem>();

    public AirportItemAdapter(){}

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
            convertView = inflater.inflate(R.layout.airport_list_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflat된)로부터 위젯에 대한 참조 획득
        ImageView iconImageView = (ImageView)convertView.findViewById(R.id.icao_img);
        TextView titleTextView = (TextView) convertView.findViewById(R.id.icao_cd);
        TextView descTextView = (TextView)convertView.findViewById(R.id.icao_nm);

        // Data Set(listViewImteList)에서 position에 위치한 데이터 참조 획득
        AirportInfoItem item = list.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        iconImageView.setImageDrawable(item.getIcon());

        titleTextView.setText(item.getIcao_cd());
        descTextView.setText(item.getIcao_nm());

        return convertView;
    }

    // 아이템 데이터 추가를 위한 함수
    public void addItem(Drawable icon, String icao_cd, String icao_nm, String lat, String lon)
    {
        AirportInfoItem item = new AirportInfoItem();
        item.setIcon(icon);
        item.setIcao_cd(icao_cd);
        item.setIcao_nm(icao_nm);
        item.setLat(lat);
        item.setLon(lon);

        list.add(item);
    }
}
