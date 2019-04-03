package com.hist.weatherview.weeklyweather.area.fragment.list.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.weatherview.R;
import com.hist.weatherview.weeklyweather.area.fragment.list.presenter.WeeklyWeatherAreaListPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 주간 날씨 정보 지역설정 리사이클 뷰 어뎁터
 * Author:JJW
 * Date: 2018.03.29
 * Remark:
 */
public class WeeklyWeatherAreaListRecycleViewAdapter extends RecyclerView.Adapter<WeeklyWeatherAreaListRecycleViewAdapter.ViewHolder> {

    private static final String TAG = "WeeklyWeatherAreaListRecycleViewAdapter";
    private WeeklyWeatherAreaListPresenter weeklyWeatherAreaListPresenter;
    private Context context;
    private List<WeeklyWeatherArea> areas;


    // 생성자
    public WeeklyWeatherAreaListRecycleViewAdapter(WeeklyWeatherAreaListPresenter weeklyWeatherAreaListPresenter, List<WeeklyWeatherArea> areas, Context context) {
        this.weeklyWeatherAreaListPresenter = weeklyWeatherAreaListPresenter;
        this.context = context;
        this.areas = areas;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(weeklyWeatherAreaListPresenter, areas, LayoutInflater.from(context).inflate(R.layout.list_item_weekly_weather_area, parent, false));
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        //viewHolder.imageView.setImageResource(items.get(position).getDrawableId());
        viewHolder.TvAreaName.setText(areas.get(position).getAreaName());
    }

    @Override
    public int getItemCount() {
        return areas.size();
    }

    /**
     * View holder to display each RecylerView item
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        private WeeklyWeatherAreaListPresenter weeklyWeatherAreaListPresenter;
        private List<WeeklyWeatherArea> areas;


        @BindView(R.id.tv_area_name)
        TextView TvAreaName;

        public ViewHolder(WeeklyWeatherAreaListPresenter weeklyWeatherAreaListPresenter, List<WeeklyWeatherArea> areas, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.weeklyWeatherAreaListPresenter = weeklyWeatherAreaListPresenter;
            this.areas = areas;
        }

        //Click 바인딩 한다.
        @OnClick(R.id.tv_area_name)
        public void onClickArea() {

            int position = getAdapterPosition();
            WeeklyWeatherArea area = areas.get(position);
            weeklyWeatherAreaListPresenter.onClickArea(area, position);
        }
    }
}
