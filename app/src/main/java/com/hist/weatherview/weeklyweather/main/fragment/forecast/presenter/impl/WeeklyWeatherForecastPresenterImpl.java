package com.hist.weatherview.weeklyweather.main.fragment.forecast.presenter.impl;


import com.hist.item.PlaceInfo.PlaceInfoResult;
import com.hist.item.weeklyweather.WeeklyWeatherBaseItem;
import com.hist.item.weeklyweather.WeeklyWeatherData;
import com.hist.item.weeklyweather.WeeklyWeatherItem;
import com.hist.item.weeklyweather.WeeklyWeatherBase;
import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.item.weeklyweather.dto.WeeklyWeatherDto;
import com.hist.repository.util.HttpError;
import com.hist.weatherview.common.util.DateUtil;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.interactor.WeeklyWeatherForecastInteractor;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.interactor.impl.WeeklyWeatherForecastInteractorImpl;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.presenter.WeeklyWeatherForecastPresenter;
import com.hist.weatherview.weeklyweather.main.fragment.forecast.view.WeeklyWeatherForecastView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 주간 날씨 정보 예보 정보 프레젠터 인터페이스 구현
 * Author:JJW
 * Date: 2018.04.01
 * Remark:
 */
public class WeeklyWeatherForecastPresenterImpl implements WeeklyWeatherForecastPresenter {
    private WeeklyWeatherForecastView weeklyWeatherForecastView;
    private WeeklyWeatherForecastInteractor weeklyWeatherForecastInteractor;

    public WeeklyWeatherForecastPresenterImpl(WeeklyWeatherForecastView weeklyWeatherForecastView) {
        this.weeklyWeatherForecastView = weeklyWeatherForecastView;
        this.weeklyWeatherForecastInteractor = new WeeklyWeatherForecastInteractorImpl(this);
    }




    @Override
    public void init(WeeklyWeatherArea area) {
       // weeklyWeatherForecastView.showProgressDialog();
        //repository 등록
        weeklyWeatherForecastInteractor.setWeeklyWeatherRepository();
    }

    @Override
    public void onCreateView() {
        // db onnect
        weeklyWeatherForecastView.showProgressDialog();
        String dayTime = getTodayDate();
        String landRegId = "11B00000";
        String tempRegId = "11B10101";
        String stnId = "133";

        String time = DateUtil.getCurrentDateByYYYYMMDD() + "0600";

        weeklyWeatherForecastInteractor.getWeeklyWeatherAll(time,landRegId,tempRegId,stnId);

    }

    @Override
    public void onNetworkError(HttpError httpError) {
        // 네트워크 실패

    }


    @Override
    public void onClickSearch() {

    }

    @Override
    public void onSuccessGetWeeklyWeatherByAreaID(WeeklyWeatherBase weeklyWeathersBase) {
        // 네트워크 성공 후 API 서버로 부터 주간 기상 정보를 받아온다.
        int size = weeklyWeathersBase.getData().getItems().size();
        List<WeeklyWeatherBaseItem> oldWeeklyWeatherItems = weeklyWeatherForecastInteractor.getWeeklyWeatherBaseList().getData().getItems();
        int oldSize = (oldWeeklyWeatherItems == null) ? 0 : oldWeeklyWeatherItems.size();

        // 기존에 데이터가 없는 경우
        if (oldSize == 0) {
            //1. 신규 데이터를 Interactor에 설정 한다.
            //weeklyWeatherForecastInteractor.setWeeklyWeather(weeklyWeathersBase);
            //2. 어뎁타를 초기화한다.
            //weeklyWeatherForecastView.clearWeeklyWeatherAdapter();
            //3. 데이터를 어답에 등록한다.
            //weeklyWeatherForecastView.setWeeklyWeatherListRecycleViewAdapterItem(weeklyWeathersBase);
            weeklyWeatherForecastInteractor.setWeeklyWeather(weeklyWeathersBase);
            weeklyWeatherForecastView.clearWeeklyWeatherAdapter();
            weeklyWeatherForecastView.setWeeklyWeatherListRecycleViewAdapterItem(weeklyWeathersBase.getData().getItems());
        } else {
            // 기존 데이터가 있는 경우..
            // 1. 추가 하지 않고 갱신한다.
           // weeklyWeatherForecastInteractor.setWeeklyWeather(weeklyWeathersBase);
            // 1.1 만약 Add 인경우
            //weeklyWeatherForecastInteractor.setWeeklyWeatherAddAll(weeklyWeathersBase);
            // 2.1 만약 NotifyItemChanged 필요한경우
            //weeklyWeatherForecastInteractor.AdapterNotifyItemRangeInserted(oldSize, newData);
            // 2.2 단순히 새로운 데이터 업데이트 시
            //2. 어뎁타를 초기화한다.
            weeklyWeatherForecastInteractor.setWeeklyWeather(weeklyWeathersBase);
            weeklyWeatherForecastView.clearWeeklyWeatherAdapter();
            weeklyWeatherForecastView.setWeeklyWeatherListRecycleViewAdapterItem(weeklyWeathersBase.getData().getItems());
            //3. 데이터를 어답에 등록한다.
           // weeklyWeatherForecastView.setWeeklyWeatherListRecycleViewAdapterItem(weeklyWeathersBase);
        }

        weeklyWeatherForecastView.goneProgressDialog();
    }

    @Override
    public void onSuccessGetWeeklyWeatherByDateAndTimeAndArea(WeeklyWeatherBase weeklyWeathersBase) {
        // 네트워크 성공 후 API 서버로 부터 주간 기상 정보를 받아온다.
        weeklyWeatherForecastView.goneProgressDialog();

        int size = weeklyWeathersBase.getData().getItems().size();
        List<WeeklyWeatherBaseItem> oldWeeklyWeatherItems = weeklyWeatherForecastInteractor.getWeeklyWeatherBaseList().getData().getItems();
        int oldSize = (oldWeeklyWeatherItems == null) ? 0 : oldWeeklyWeatherItems.size();

        // 기존에 데이터가 없는 경우
        if (oldSize == 0) {
            //1. 신규 데이터를 Interactor에 설정 한다.
            //weeklyWeatherForecastInteractor.setWeeklyWeather(weeklyWeathersBase);
            //2. 어뎁타를 초기화한다.
            //weeklyWeatherForecastView.clearWeeklyWeatherAdapter();
            //3. 데이터를 어답에 등록한다.
            //weeklyWeatherForecastView.setWeeklyWeatherListRecycleViewAdapterItem(weeklyWeathersBase);
            weeklyWeatherForecastInteractor.setWeeklyWeather(weeklyWeathersBase);
            weeklyWeatherForecastView.clearWeeklyWeatherAdapter();
            weeklyWeatherForecastView.setWeeklyWeatherListRecycleViewAdapterItem(weeklyWeathersBase.getData().getItems());
        } else {
            // 기존 데이터가 있는 경우..
            // 1. 추가 하지 않고 갱신한다.
            // weeklyWeatherForecastInteractor.setWeeklyWeather(weeklyWeathersBase);
            // 1.1 만약 Add 인경우
            //weeklyWeatherForecastInteractor.setWeeklyWeatherAddAll(weeklyWeathersBase);
            // 2.1 만약 NotifyItemChanged 필요한경우
            //weeklyWeatherForecastInteractor.AdapterNotifyItemRangeInserted(oldSize, newData);
            // 2.2 단순히 새로운 데이터 업데이트 시
            //2. 어뎁타를 초기화한다.
            weeklyWeatherForecastInteractor.setWeeklyWeather(weeklyWeathersBase);
            weeklyWeatherForecastView.clearWeeklyWeatherAdapter();
            //3. 데이터를 어답에 등록한다.
            weeklyWeatherForecastView.setWeeklyWeatherListRecycleViewAdapterItem(weeklyWeathersBase.getData().getItems());
        }
    }

    @Override
    public void onSuccessGetWeeklyWeatherMiddleForecastAllByTimeAndRegIdAndStnId(WeeklyWeatherBase weeklyWeathersBase) {
        // 네트워크 성공 후 API 서버로 부터 주간 기상 정보를 받아온다.
        weeklyWeatherForecastView.goneProgressDialog();
        int size = weeklyWeathersBase.getData().getItems().size();
        /*List<WeeklyWeatherBaseItem> oldWeeklyWeatherItems = weeklyWeatherForecastInteractor.getWeeklyWeatherBaseList().getData().getItems();
        int oldSize = (oldWeeklyWeatherItems == null) ? 0 : oldWeeklyWeatherItems.size();*/

        int oldSize = 0;
        // 기존에 데이터가 없는 경우
        if (oldSize == 0) {
            //1. 신규 데이터를 Interactor에 설정 한다.
            //weeklyWeatherForecastInteractor.setWeeklyWeather(weeklyWeathersBase);
            //2. 어뎁타를 초기화한다.
            //weeklyWeatherForecastView.clearWeeklyWeatherAdapter();
            //3. 데이터를 어답에 등록한다.
            //weeklyWeatherForecastView.setWeeklyWeatherListRecycleViewAdapterItem(weeklyWeathersBase);
            weeklyWeatherForecastInteractor.setWeeklyWeather(weeklyWeathersBase);
            weeklyWeatherForecastView.setWeeklyWeatherMiddleForecast(weeklyWeathersBase);
            weeklyWeatherForecastView.clearWeeklyWeatherAdapter();
            weeklyWeatherForecastView.setWeeklyWeatherListRecycleViewAdapterItem(weeklyWeathersBase.getData().getItems());
        } else {
            // 기존 데이터가 있는 경우..
            // 1. 추가 하지 않고 갱신한다.
            // weeklyWeatherForecastInteractor.setWeeklyWeather(weeklyWeathersBase);
            // 1.1 만약 Add 인경우
            //weeklyWeatherForecastInteractor.setWeeklyWeatherAddAll(weeklyWeathersBase);
            // 2.1 만약 NotifyItemChanged 필요한경우
            //weeklyWeatherForecastInteractor.AdapterNotifyItemRangeInserted(oldSize, newData);
            // 2.2 단순히 새로운 데이터 업데이트 시
            //2. 어뎁타를 초기화한다.

            weeklyWeatherForecastInteractor.setWeeklyWeather(weeklyWeathersBase);
            weeklyWeatherForecastView.setWeeklyWeatherMiddleForecast(weeklyWeathersBase);
            weeklyWeatherForecastView.clearWeeklyWeatherAdapter();
            weeklyWeatherForecastView.setWeeklyWeatherListRecycleViewAdapterItem(weeklyWeathersBase.getData().getItems());
        }
    }

    @Override
    public void getWeeklyWeatherMiddleForecastByAreaAndDate(String areaCode) {
        // 지역 선택 후 해당 해당 지역의 중기 날씨 정보를 가져온다.
        weeklyWeatherForecastView.showProgressDialog();
        weeklyWeatherForecastInteractor.getPlaceInfoByAreaCode(areaCode);
    }

    @Override
    public void onSuccessGetPlaceInfoByAreaCode(PlaceInfoResult placeInfoResult) {
        // 지역 코드 조회 성공 후 다시 상세 조회를 수행 한다.
        String time = DateUtil.getCurrentDateByYYYYMMDD() + "0600";

        String landRegId = "11D10000";
        String tempRegId = "11B20201";
        String stnId = "109";

        //날짜, 중기 지역 코드, 중기 온도 코드,
        weeklyWeatherForecastInteractor.getWeeklyWeatherAll(time,landRegId,tempRegId,stnId);
    }

    /*@Override
    public void onClickImage(PlaceAttraction placeAttraction, int position) {
        // 어뎁터에서 뷰 아이템 클릭시 발생
        this.placeAttractionView.navigateToPlaceAttractionDetailActivity(placeAttraction, position);
    }


    @Override
    public void onSuccessGetGuiderList(List<Guider> guiderList) {

    }*/
    public String getTodayDate()
    {

        Date currentTime = Calendar.getInstance().getTime();
        String currentDate = DateFormat.getDateTimeInstance().format(new Date());

        return currentDate;
    }
}
