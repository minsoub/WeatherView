package com.hist.weatherview.weatherlife.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Vector;

import static android.support.constraint.Constraints.TAG;

/***
 *  Author : JJW
 *  Date : 20180208
 *  Desc : 생활 기상 관련 뷰 페이저 어뎁터
 */
public class WeatherLifePagerAdapter extends PagerAdapter {

    private Context mContext;
    private Vector<View> pages;
    private WeatherLifeTypeListener listener;
    private View currentView;

    public WeatherLifePagerAdapter(Context context, Vector<View> pages) {
        this.mContext=context;
        this.pages=pages;
        this.listener = (WeatherLifeTypeListener)mContext;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View page = pages.get(position);
        container.addView(page);

        return page;
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
        currentView = (View)object;
        Log.d(TAG, "position : " + position);
        // Type을 가져온다.
        try {
            WeatherLifeListViewArrayAdapter arrayAdapter = (WeatherLifeListViewArrayAdapter) ((ListView) currentView).getAdapter();
            String type = arrayAdapter.getWeatherLifeItemTypName();
            listener.onChangeWeatherLifeType(type);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
