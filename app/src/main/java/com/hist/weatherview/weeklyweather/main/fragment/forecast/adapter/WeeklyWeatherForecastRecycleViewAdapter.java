package com.hist.weatherview.weeklyweather.main.fragment.forecast.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hist.item.weeklyweather.WeeklyWeatherBaseItem;
import com.hist.item.weeklyweather.WeeklyWeatherData;
import com.hist.item.weeklyweather.WeeklyWeatherItem;
import com.hist.item.weeklyweather.WeeklyWeatherBase;
import com.hist.item.weeklyweather.WeeklyWeatherResult;
import com.hist.weatherview.R;
import com.hist.weatherview.common.util.WeatherUtil;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.presenter.WeeklyWeatherForecastPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 주간 날씨 정보 예보 정보 어답터
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */

public class WeeklyWeatherForecastRecycleViewAdapter extends RecyclerView.Adapter<WeeklyWeatherForecastRecycleViewAdapter.WeeklyWeatherForecastViewHolder> {

    private static final String TAG = "WeeklyWeatherForecastRecycleViewAdapter";
    private WeeklyWeatherForecastPresenter weeklyWeatherForecastPresenter;
    private Context context;
    private List<WeeklyWeatherBase> days;
    private List<WeeklyWeatherBaseItem> weeklyWeatherDataList;
    private List<WeeklyWeatherResult> weeklyWeatherItemResultOfMiddleTemp;
    private List<WeeklyWeatherResult> weeklyWeatherItemResultOfMiddleLand;
    private int layout;

    public WeeklyWeatherForecastRecycleViewAdapter(WeeklyWeatherForecastPresenter weeklyWeatherForecastPresenter, List<WeeklyWeatherBaseItem> data, Context context) {
        this.weeklyWeatherForecastPresenter = weeklyWeatherForecastPresenter;
        this.weeklyWeatherDataList = data;
        this.context = context;
        this.weeklyWeatherItemResultOfMiddleTemp = data.get(1).getItem().getResult();
        this.weeklyWeatherItemResultOfMiddleLand = data.get(2).getItem().getResult();
    }


    @Override
    public WeeklyWeatherForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WeeklyWeatherForecastViewHolder weeklyWeatherForecastViewHolder = null;
        weeklyWeatherForecastViewHolder = new WeeklyWeatherForecastViewHolder(weeklyWeatherForecastPresenter, weeklyWeatherItemResultOfMiddleTemp, weeklyWeatherItemResultOfMiddleLand, LayoutInflater.from(context).inflate(R.layout.list_item_weekly_weather_forecast, parent, false));

        // 아래는 아이템 인덱스에 따라 다른 view를 현시한다.
        /*        if(viewType == 0){
            guiderListViewHolder = new WeeklyWeatherForecastViewHolder(weeklyWeatherForecastPresenter, days, LayoutInflater.from(context).inflate(R.layout.list_item_weekly_weather_forecast_today, parent, false));
        }else
        {
            guiderListViewHolder = new WeeklyWeatherForecastViewHolder(weeklyWeatherForecastPresenter, days, LayoutInflater.from(context).inflate(R.layout.list_item_weekly_weather_forecast, parent, false));
        }*/


        return weeklyWeatherForecastViewHolder;
    }

    @Override
    public void onBindViewHolder(WeeklyWeatherForecastViewHolder viewHolder, final int i) {

        int firstIdx = i * 2;
        int secondIdx = firstIdx + 1;
        WeeklyWeatherResult weeklyWeatherResultOfMiddleTemp = weeklyWeatherItemResultOfMiddleTemp.get(firstIdx);
        WeeklyWeatherResult weeklyWeatherResultOfMiddleTemp2 = weeklyWeatherItemResultOfMiddleTemp.get(secondIdx);

        WeeklyWeatherResult weatherResultOfLand1 = weeklyWeatherItemResultOfMiddleLand.get((firstIdx >= weeklyWeatherItemResultOfMiddleLand.size()) ? 0 : firstIdx);
        WeeklyWeatherResult weatherResultOfLand2 = weeklyWeatherItemResultOfMiddleLand.get((secondIdx >= weeklyWeatherItemResultOfMiddleLand.size()) ? 0 : secondIdx);

        viewHolder.IvImage.setImageResource(WeatherUtil.getSkyImageByString(weatherResultOfLand1.getValue()));              //오전 이미지
        viewHolder.IvImageAfternoon.setImageResource(WeatherUtil.getSkyImageByString(weatherResultOfLand2.getValue()));     //오후 이미지

        viewHolder.TvDay.setText(weeklyWeatherResultOfMiddleTemp.getTitle());

        viewHolder.TvAmImageDescription.setText(weatherResultOfLand1.getValue());
        viewHolder.TvPmImageDescription.setText(weatherResultOfLand2.getValue());

        viewHolder.TvTempMax.setText(context.getString(R.string.format_temperature, Double.parseDouble(weeklyWeatherResultOfMiddleTemp2.getValue())));
        viewHolder.TvTempMin.setText(context.getString(R.string.format_temperature, Double.parseDouble(weeklyWeatherResultOfMiddleTemp.getValue())));

    }

    /*public String getTimeWeatherResultTimeValueByCategory(WeeklyWeatherResult result, String category)
    {
        String retVal = "";
        for(int i = 0 ; i < result.getTime().size() ; i++)
        {
            WeeklyWeatherResult time = result.getTime().get(i);
            if(category.equals(time.getCategory()))
            {
                retVal = time.getFcstValue().toString();
            }
        }

        return retVal;
    }*/

    public int getSkyImageByValue(String value)
    {
        String strValue = value;
        int retDrawable;
        if("맑음".equals(strValue))
        {
            //맑음
            retDrawable = R.drawable.art_clear;
        }else if("구름조금".equals(strValue))
        {
            //구름조금
            retDrawable = R.drawable.art_light_clouds;
        }else if("구름많음".equals(strValue))
        {
            retDrawable = R.drawable.art_clouds;
        }else if("흐림".equals(strValue))
        {
            retDrawable = R.drawable.art_clouds;
        }
        else {
            // 흐림
            retDrawable = R.drawable.art_clouds;
        }

        return retDrawable;
    }

    public String getSkyTypeStringByValue(String value)
    {
        double intValue = Double.parseDouble(value);
        String retDrawable;
        if(intValue == 1 )
        {
            //맑음
            retDrawable = "맑음";
        }else if(intValue == 2)
        {
            //구름조금
            retDrawable = "구름조금";
        }else if(intValue == 3)
        {
            retDrawable = "구름많음";
        }else {
            // 구름 많음
            retDrawable = "흐림";
        }

        return retDrawable;
    }


    @Override
    public int getItemCount() {
        return weeklyWeatherItemResultOfMiddleTemp.size() / 2;
    }

    /**
     * View holder to display each RecylerView item
     */
    public static class WeeklyWeatherForecastViewHolder extends RecyclerView.ViewHolder {

        private WeeklyWeatherForecastPresenter weeklyWeatherForecastPresenter;
        private List<WeeklyWeatherResult> weeklyWeatherResults1;
        private List<WeeklyWeatherResult> weeklyWeatherResults2;


        @BindView(R.id.iv_image)
        ImageView IvImage;
        @BindView(R.id.iv_image_afternoon)
        ImageView IvImageAfternoon;
        @BindView(R.id.tv_day)
        TextView TvDay;
        @BindView(R.id.tv_temp_max)
        TextView TvTempMax;
        @BindView(R.id.tv_temp_min)
        TextView TvTempMin;
        @BindView(R.id.tv_pm_image_description)
        TextView TvPmImageDescription;
        @BindView(R.id.tv_am_image_description)
        TextView TvAmImageDescription;


        public WeeklyWeatherForecastViewHolder(WeeklyWeatherForecastPresenter weeklyWeatherForecastPresenter, List<WeeklyWeatherResult> weeklyWeatherResults1, List<WeeklyWeatherResult> weeklyWeatherResults2,  View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            this.weeklyWeatherForecastPresenter = weeklyWeatherForecastPresenter;
            this.weeklyWeatherResults1 = weeklyWeatherResults1;
            this.weeklyWeatherResults2 = weeklyWeatherResults2;
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
