package com.hist.weatherview.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hist.item.main.MainMenuItem;
import com.hist.item.timeweather.TimeWeatherResult;
import com.hist.weatherview.R;
import com.hist.weatherview.common.util.WeatherUtil;
import com.hist.weatherview.main.base.MainActivity;
import com.hist.weatherview.main.presenter.MainPresenter;
import com.hist.weatherview.timeweather.main.fragment.forecast.presenter.TimeWeatherForecastPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * 시간별 날씨 정보 예보 정보 어답터
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */

public class MainMenuRecycleViewAdapter extends RecyclerView.Adapter<MainMenuRecycleViewAdapter.MainMenuViewHolder> {

    private static final String TAG = "MainMenuRecycleViewAdapter";
    private MainPresenter mainPresenter;
    private Context context;
    private List<MainMenuItem> mainMenuItems;
    private int layout;


    public MainMenuRecycleViewAdapter(MainPresenter mainPresenter, List<MainMenuItem> data, Context context, int list_item_main) {
        this.mainPresenter = mainPresenter;
        this.mainMenuItems = data;
        this.context = context;
        this.layout = list_item_main;
    }


    @Override
    public MainMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MainMenuViewHolder mainMenuViewHolder = null;
        mainMenuViewHolder = new MainMenuViewHolder(mainPresenter, mainMenuItems, LayoutInflater.from(context).inflate(R.layout.list_item_main, parent, false));



        return mainMenuViewHolder;
    }

    @Override
    public void onBindViewHolder(MainMenuViewHolder viewHolder, final int i) {

        MainMenuItem mainMenuItem = mainMenuItems.get(i);

        String menuNam = mainMenuItem.getTitle();
        viewHolder.IvMainMenu.setImageResource(mainMenuItem.getImage());
        viewHolder.TvMainMenu.setText(menuNam);
        //Glide.with(context).load(holder.storyImageUrl + url).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.iv_marketmain_photo);


    }


    @Override
    public int getItemCount() {
        return mainMenuItems.size();
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
    public static class MainMenuViewHolder extends RecyclerView.ViewHolder {

        private MainPresenter mainPresenter;
        private List<MainMenuItem> mainMenuItems;


        @BindView(R.id.iv_main_menu)
        ImageView IvMainMenu;
        @BindView(R.id.tv_main_menu)
        TextView TvMainMenu;


        public MainMenuViewHolder(MainPresenter mainPresenter, List<MainMenuItem> mainMenuItems, View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            this.mainPresenter = mainPresenter;
            this.mainMenuItems = mainMenuItems;
        }


        @Optional
        @OnClick({R.id.ll_main_menu, R.id.iv_main_menu})
        public void onClickMenuItem(){
            int position = getAdapterPosition();
            MainMenuItem mainMenuItem = mainMenuItems.get(position);
            mainPresenter.onClickMenuItem(mainMenuItem, position);
        }

    }
}
