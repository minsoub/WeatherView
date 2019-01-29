package com.hist.weatherview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

// 참조 URL : https://recipes4dev.tistory.com/43
public class MenuItemAdapter extends BaseAdapter {

    private ArrayList<MenuViewItem> listViewItemList = new ArrayList<MenuViewItem>();

    public MenuItemAdapter() { }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴 : 필수
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // main_menu_item.xml의 main_menu_item Layout을 inflat하여 convertView 참조 획득.
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) ((Context) context).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.main_menu_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflat된)로부터 위젯에 대한 참조 획득
        ImageView iconImageView = (ImageView)convertView.findViewById(R.id.imageView1);
        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView1);
        TextView descTextView = (TextView)convertView.findViewById(R.id.textView2);

        // Data Set(listViewImteList)에서 position에 위치한 데이터 참조 획득
        MenuViewItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        iconImageView.setImageDrawable(listViewItem.getIconDrawable());
        titleTextView.setText(listViewItem.getTitleStr());
        descTextView.setText(listViewItem.getDescStr());

        return convertView;
    }
    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }
    // 지정한 위치(position)에 있는 데이터 리턴
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    // 아이템 데이터 추가를 위한 함수
    public void addItem(Drawable icon, String title, String desc, String key)
    {
        MenuViewItem item = new MenuViewItem();
        item.setIconDrawable(icon);
        item.setTitleStr(title);
        item.setDescStr(desc);
        item.setKey(key);

        listViewItemList.add(item);
    }
}
