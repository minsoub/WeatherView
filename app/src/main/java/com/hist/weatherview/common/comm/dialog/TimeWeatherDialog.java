package com.hist.weatherview.common.comm.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hist.item.common.SharedPlaceInfo;
import com.hist.weatherview.R;
import com.hist.weatherview.timeweather.main.view.TimeWeatherView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 주간 날씨 정보 Bottom 다이얼로그
 * Author:JJW
 * Date: 2018.04.02
 * Remark:
 *
 */


public class TimeWeatherDialog extends BottomSheetDialog {

    private Context context;
    private TimeWeatherView timeWeatherView;
    private ArrayList<SharedPlaceInfo> placeInfos;
    final String areas[] = {"등촌1동", "염창1동", "가양1동"};

    @BindView(R.id.ll_dialog_weekly_weather)
    LinearLayout llDialogWeeklyWeather;

    public TimeWeatherDialog(@NonNull Context context, ArrayList<SharedPlaceInfo> placeInfos) {
        super(context);
        this.context = context;
        this.timeWeatherView = (TimeWeatherView)context;
        this.placeInfos = placeInfos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_weekly_weather);
        ButterKnife.bind(this);
        init();
    }

    public void init()
    {
        // 동적 레이아웃 생성 및 버튼 이벤트 등록

        // 즐겨찾기 버튼 생성
        // 랜덤 생성
    }

    public void refreshWeeklyWeatherFavoritePlaceList()
    {

        if(llDialogWeeklyWeather != null) {
            if (llDialogWeeklyWeather.getChildCount() > 0) {
                llDialogWeeklyWeather.removeAllViews();
            }
        }
        if(placeInfos != null) {
            for (int i = 0; i < placeInfos.size(); i++) {
                RelativeLayout relativeLayout = createRelativeLayout();
                SharedPlaceInfo placeInfo = placeInfos.get(i);
                createFavoriteTextViews(relativeLayout, i, placeInfo.getPlaceName());
                llDialogWeeklyWeather.addView(relativeLayout);
            }
        }
    }

    public LinearLayout createLinearLayout() {
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.setLayoutParams(LLParams);

        return  ll;
    }

    public RelativeLayout createRelativeLayout() {
        RelativeLayout rl = new RelativeLayout(context);
        RelativeLayout.LayoutParams LLParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        rl.setLayoutParams(LLParams);

        return  rl;
    }

    public void createFavoriteTextViews(RelativeLayout relativeLayout, int i, String name)
    {
        // dummy로 사용
        final int temp = i;
        TextView tv = new TextView(context);
        tv.setText(name);
        tv.setId(i);
        tv.setTextColor(Color.GRAY);
        tv.setTextSize(25);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            tv.setBackground(ContextCompat.getDrawable(context, R.drawable.weatherlife_circle_danger));
        }*/

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        tv.setLayoutParams(params);

  /*      tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)tv.getLayoutParams();
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);*/


        // 마이너스
        Button button = new Button(context);
        button.setText("-");

        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        button.setLayoutParams(params2);

        /*button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams)tv.getLayoutParams();
        params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);*/


        relativeLayout.addView(tv);
        relativeLayout.addView(button);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeWeatherView.setWeeklyWeatherFavoriteArea(areas[temp]);
                dismiss();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeWeatherView.deleteWeeklyWeatherFavoriteArea(placeInfos, temp);
                dismiss();
            }
        });
    }

    @OnClick(R.id.tv_dialog_weekly_weather)
    public void onClickWeeklyWeatherSearchArea() {
        timeWeatherView.navigateToWeeklyWeatherSearchArea();
    }

    @OnClick(R.id.tv_dialog_weekly_weather_favorite)
    public void onClickWeeklyWeatherFavoriteArea() {
        timeWeatherView.navigateToWeeklyWeatherFavoriteArea();
    }

    @OnClick(R.id.tv_dialog_weekly_weather_cancel)
    public void onClickWeeklyWeatherDialogCancel() {
        dismiss();
    }

}
