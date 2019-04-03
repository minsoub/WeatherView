package com.hist.weatherview.weeklyweather.main.fragment.forecast.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hist.item.weeklyweather.WeeklyWeather;
import com.hist.weatherview.R;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.presenter.WeeklyWeatherForecastPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 주간 날씨 정보 예보 정보 어답터
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */

public class WeeklyWeatherForecastRecycleViewAdapter extends RecyclerView.Adapter<WeeklyWeatherForecastRecycleViewAdapter.WeeklyWeatherForecastViewHolder> {

    private static final String TAG = "PlaceAttractionRecycleViewAdapter";
    private WeeklyWeatherForecastPresenter weeklyWeatherForecastPresenter;
    private Context context;
    private List<WeeklyWeather> days;
    private int layout;

    public WeeklyWeatherForecastRecycleViewAdapter(WeeklyWeatherForecastPresenter weeklyWeatherForecastPresenter, List<WeeklyWeather> attractions, Context context, int layout) {
        this.weeklyWeatherForecastPresenter = weeklyWeatherForecastPresenter;
        this.days = attractions;
        this.context = context;
        this.layout = layout;
    }

    public WeeklyWeatherForecastRecycleViewAdapter(WeeklyWeatherForecastPresenter weeklyWeatherForecastPresenter, List<WeeklyWeather> weeklyWeatherList, Context context) {
        this.weeklyWeatherForecastPresenter = weeklyWeatherForecastPresenter;
        this.days = weeklyWeatherList;
        this.context = context;
    }


    @Override
    public WeeklyWeatherForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WeeklyWeatherForecastViewHolder guiderListViewHolder = null;
        guiderListViewHolder = new WeeklyWeatherForecastViewHolder(weeklyWeatherForecastPresenter, days, LayoutInflater.from(context).inflate(R.layout.list_item_weekly_weather_forecast, parent, false));
/*        if(viewType == 0){
            guiderListViewHolder = new WeeklyWeatherForecastViewHolder(weeklyWeatherForecastPresenter, days, LayoutInflater.from(context).inflate(R.layout.list_item_weekly_weather_forecast_today, parent, false));
        }else
        {
            guiderListViewHolder = new WeeklyWeatherForecastViewHolder(weeklyWeatherForecastPresenter, days, LayoutInflater.from(context).inflate(R.layout.list_item_weekly_weather_forecast, parent, false));
        }*/


        return guiderListViewHolder;
    }

    @Override
    public void onBindViewHolder(WeeklyWeatherForecastViewHolder viewHolder, final int i) {
        //viewHolder.imageView.setImageResource(items.get(position).getDrawableId());
        viewHolder.CvWeeklyWeatherForecast.setTag(days.get(i).getDate());
        viewHolder.IvImage.setContentDescription(days.get(i).getWeatherDescription());

            //viewHolder.IvImage.setImageResource(Util.getFeaturedWeatherIcon(days.get(i).getIconCode()));
/*            viewHolder.IvImage.setImageResource(R.drawable.art_clear);
            viewHolder.TvDay.setText("오늘");
            viewHolder.TvWeatherType.setText("맑음");
            viewHolder.TvTempMax.setText("30");
            viewHolder.TvTempMin.setText("10");*/

/*            viewHolder.dayOfWeek.setText(context.getString(R.string.today).concat(", ")
                    .concat(days.get(i).getDate().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()))
                    .concat(" ")
                    .concat(Integer.toString(days.get(i).getDate().get(Calendar.DAY_OF_MONTH))));*/
        if (i == 0) {
            //viewHolder.IvImage.setImageResource(Util.getItemWeatherIcon(days.get(i).getIconCode()));
            viewHolder.IvImage.setImageResource(R.drawable.art_clouds);
            viewHolder.TvDay.setText("내일");
            viewHolder.TvWeatherType.setText("흐림");
            viewHolder.TvTempMax.setText("20");
            viewHolder.TvTempMin.setText("10");
            /*dayViewHolder.dayOfWeek.setText(context.getString(R.string.tomorrow));*/
        }
        else {
            viewHolder.IvImage.setImageResource(R.drawable.art_light_rain);
            viewHolder.TvDay.setText("그외");
            viewHolder.TvWeatherType.setText("흐림");
            viewHolder.TvTempMax.setText("20");
            viewHolder.TvTempMin.setText("10");
            //dayViewHolder.dayOfWeek.setText(days.get(i).getDate().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));
        }
        //dayViewHolder.weatherType.setText(days.get(i).getWeatherDescription());
    }

    @Override
    public int getItemCount() {
        return days.size();
    }


    //포지션에 따라 뷰 타입을 제공한다.
    //현재 0번 포지션은 뷰 0을, 1번 포지션은 2번 뷰, 그외에는 2번 뷰를 반환한다.
    /*@Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return 1;
            case 1:
                return 1;
            default:
                return 2;
        }
    }*/
    /**
     * View holder to display each RecylerView item
     */
    public static class WeeklyWeatherForecastViewHolder extends RecyclerView.ViewHolder {

        private WeeklyWeatherForecastPresenter weeklyWeatherForecastPresenter;
        private List<WeeklyWeather> weeklyWeathers;


        @BindView(R.id.cv_weekly_weather_forecast)
        CardView CvWeeklyWeatherForecast;
        @BindView(R.id.iv_image)
        ImageView IvImage;
        @BindView(R.id.tv_day)
        TextView TvDay;
        @BindView(R.id.tv_temp_max)
        TextView TvTempMax;
        @BindView(R.id.tv_temp_min)
        TextView TvTempMin;
        @BindView(R.id.tv_weather_type)
        TextView TvWeatherType;



        public WeeklyWeatherForecastViewHolder(WeeklyWeatherForecastPresenter weeklyWeatherForecastPresenter, List<WeeklyWeather> weeklyWeathers, View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            this.weeklyWeatherForecastPresenter = weeklyWeatherForecastPresenter;
            this.weeklyWeathers = weeklyWeathers;
        }

        //Click 바인딩 한다.
/*        @OnClick({R.id.rl_place_main_layout, R.id.iv_place_attraction_img})
        public void onClickImage() {

            int position = getAdapterPosition();
            PlaceAttraction attraction = placeAttractionList.get(position);
            placeAttractionPresenter.onClickImage(attraction, position);
        }*/

    }
}
