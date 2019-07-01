package com.hist.weatherview.timeweather.main.fragment.forecast.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hist.item.timeweather.TimeWeatherResult;
import com.hist.item.timeweather.TimeWeatherResultTime;
import com.hist.item.weeklyweather.WeeklyWeatherBase;
import com.hist.item.weeklyweather.WeeklyWeatherData;
import com.hist.item.weeklyweather.WeeklyWeatherItem;
import com.hist.weatherview.R;
import com.hist.weatherview.timeweather.main.fragment.forecast.presenter.TimeWeatherForecastPresenter;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.presenter.WeeklyWeatherForecastPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 시간별 날씨 정보 예보 정보 어답터
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */

public class TimeWeatherForecastRecycleViewAdapter extends RecyclerView.Adapter<TimeWeatherForecastRecycleViewAdapter.TimeWeatherForecastViewHolder> {

    private static final String TAG = "TimeWeatherForecastRecycleViewAdapter";
    private TimeWeatherForecastPresenter timeWeatherForecastPresenter;
    private Context context;
    private List<TimeWeatherResult> timeWeatherResults;
    private int layout;

    public TimeWeatherForecastRecycleViewAdapter(TimeWeatherForecastPresenter timeWeatherForecastPresenter, List<TimeWeatherResult> data, Context context) {
        this.timeWeatherForecastPresenter = timeWeatherForecastPresenter;
        this.timeWeatherResults = data;
        this.context = context;
    }


    @Override
    public TimeWeatherForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TimeWeatherForecastViewHolder guiderListViewHolder = null;
        guiderListViewHolder = new TimeWeatherForecastViewHolder(timeWeatherForecastPresenter, timeWeatherResults, LayoutInflater.from(context).inflate(R.layout.list_item_time_weather_forecast, parent, false));
/*        if(viewType == 0){
            guiderListViewHolder = new WeeklyWeatherForecastViewHolder(weeklyWeatherForecastPresenter, days, LayoutInflater.from(context).inflate(R.layout.list_item_weekly_weather_forecast_today, parent, false));
        }else
        {
            guiderListViewHolder = new WeeklyWeatherForecastViewHolder(weeklyWeatherForecastPresenter, days, LayoutInflater.from(context).inflate(R.layout.list_item_weekly_weather_forecast, parent, false));
        }*/


        return guiderListViewHolder;
    }

    @Override
    public void onBindViewHolder(TimeWeatherForecastViewHolder viewHolder, final int i) {

        TimeWeatherResult timeWeatherResult = timeWeatherResults.get(i);

        if(i == 0)
        {
            viewHolder.IvTimeWeatherSky.setImageResource(getSkyImageByValue(getTimeWeatherResultTimeValueByCategory(timeWeatherResult, "SKY")));
            viewHolder.TvTimeWeatherDate.setText("오늘");
            viewHolder.TvTimeWeatherTime.setText(insertString(timeWeatherResult.getTime().get(0).getFcstTime().toString(), ":", 2));
            viewHolder.TvTimeWeatherTemp.setText(context.getString(R.string.format_temperature, Double.parseDouble(getTimeWeatherResultTimeValueByCategory(timeWeatherResult, "T3H"))));
            viewHolder.TvTimeWeatherSkyDesc.setText("맑음");
        }else{
            viewHolder.IvTimeWeatherSky.setImageResource(getSkyImageByValue(getTimeWeatherResultTimeValueByCategory(timeWeatherResult, "SKY")));
            viewHolder.TvTimeWeatherDate.setText(insertString(timeWeatherResult.getTime().get(0).getFcstDate().toString().substring(4),"/",2));
            viewHolder.TvTimeWeatherTime.setText(insertString(timeWeatherResult.getTime().get(0).getFcstTime().toString(), ":", 2));
            viewHolder.TvTimeWeatherTemp.setText(context.getString(R.string.format_temperature, Double.parseDouble(getTimeWeatherResultTimeValueByCategory(timeWeatherResult, "T3H"))));
            viewHolder.TvTimeWeatherSkyDesc.setText("맑음");
            //viewHolder.TvWeatherType.setText(morningSky);
            //viewHolder.TvTempMax.setText(context.getString(R.string.format_temperature, 21.0));
            //viewHolder.TvTempMin.setText(context.getString(R.string.format_temperature, 10.1));
        }

        //dayViewHolder.weatherType.setText(days.get(i).getWeatherDescription());
    }

    public String getTimeWeatherResultTimeValueByCategory(TimeWeatherResult result, String category)
    {
        String retVal = "";
        for(int i = 0 ; i < result.getTime().size() ; i++)
        {
            TimeWeatherResultTime time = result.getTime().get(i);
            if(category.equals(time.getCategory()))
            {
                retVal = time.getFcstValue().toString();
            }

        }

        return retVal;
    }

    public int getSkyImageByValue(String value)
    {
        double intValue = Double.parseDouble(value);
        int retDrawable = R.drawable.art_clear;
        if(intValue <= 2)
        {
            retDrawable = R.drawable.art_clear;
        }else if(intValue > 2 && intValue <= 5)
        {
            retDrawable = R.drawable.art_clouds;
        }else if(intValue > 5 && intValue <= 8)
        {
            retDrawable = R.drawable.art_clouds;        //흐림
        }

        return retDrawable;

    }

    public String insertString(String str, String iStr, int position)
    {
        StringBuilder strBuf = new StringBuilder(str);

        strBuf.insert(position, iStr);

        return strBuf.toString();
    }

    @Override
    public int getItemCount() {
        return timeWeatherResults.size();
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
    public static class TimeWeatherForecastViewHolder extends RecyclerView.ViewHolder {

        private TimeWeatherForecastPresenter timeWeatherForecastPresenter;
        private List<TimeWeatherResult> timeWeatherResults;


        @BindView(R.id.iv_time_weather_sky)
        ImageView IvTimeWeatherSky;
        @BindView(R.id.tv_time_weather_sky_desc)
        TextView TvTimeWeatherSkyDesc;
        @BindView(R.id.tv_time_weather_date)
        TextView TvTimeWeatherDate;
        @BindView(R.id.tv_time_weather_temp)
        TextView TvTimeWeatherTemp;
        @BindView(R.id.tv_time_weather_time)
        TextView TvTimeWeatherTime;



        public TimeWeatherForecastViewHolder(TimeWeatherForecastPresenter timeWeatherForecastPresenter, List<TimeWeatherResult> timeWeatherResults, View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            this.timeWeatherForecastPresenter = timeWeatherForecastPresenter;
            this.timeWeatherResults = timeWeatherResults;
        }


    }
}
