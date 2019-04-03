package com.hist.weatherview.weeklyweather.area.fragment.list.presenter.impl;


import com.hist.item.weeklyweather.WeeklyWeatherArea;
import com.hist.weatherview.weeklyweather.area.base.WeeklyWeatherAreaActivity;
import com.hist.weatherview.weeklyweather.area.fragment.list.presenter.WeeklyWeatherAreaListPresenter;
import com.hist.weatherview.weeklyweather.area.fragment.list.view.WeeklyWeatherAreaListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 주간 날씨 정보 지역설정 리스트 프레젠터 구현 클래스
 * Author:JJW
 * Date: 2018.03.29
 * Remark:
 */
public class WeeklyWeatherAreaListPresenterImpl implements WeeklyWeatherAreaListPresenter {
    private WeeklyWeatherAreaListView weeklyWeatherAreaListView;
    private int requestType;
    // private MarketMainInteractor marketMainInteractor;

    public WeeklyWeatherAreaListPresenterImpl(WeeklyWeatherAreaListView weeklyWeatherAreaListView, int requestType) {
        this.weeklyWeatherAreaListView = weeklyWeatherAreaListView;
        this.requestType = requestType;
       // this.marketMainInteractor = new MarketMainInteractorImpl(this);
    }

    @Override
    public void init() {
        weeklyWeatherAreaListView.showProgressDialog();
/*
        marketMainInteractor.setUser(user);
        marketMainInteractor.setMarket(market);

        if (user != null) {
            String accessToken = user.getAccessToken();
            marketMainInteractor.setFileRepository(accessToken);
            marketMainInteractor.setMarketRepository(accessToken);
            marketMainInteractor.setStoryRepository(accessToken);
        } else {
            marketMainInteractor.setFileRepository();
            marketMainInteractor.setMarketRepository();
            marketMainInteractor.setStoryRepository();
        }*/
    }

    @Override
    public void onCreateView() {
        //User 가져오기..등 DB 연동
        /*User user = marketMainInteractor.getUser();
        Market market = marketMainInteractor.getMarket();
        long marketId = market.getId();
        long offset = InfiniteScrollFlag.DEFAULT_OFFSET;
        marketMainInteractor.getFileListByIdAndTypeAndOffset(marketId, DefaultFileFlag.PHOTO_TYPE, offset);
        marketMainInteractor.getFileListByIdAndTypeAndOffset(marketId, DefaultFileFlag.VIDEO_THUMBNAIL_TYPE, offset);

        marketMainView.setScrollViewOnScrollChangeListener();
        marketMainView.setPhotoRecyclerViewOnScrollListener();

        marketMainInteractor.setFirstCreated(false);
        if (user != null) {
            long userId = user.getId();
            marketMainInteractor.getMarketById(marketId, userId);
            marketMainInteractor.getStoryListByIdAndOffset(marketId, userId, offset);
        } else {
            marketMainInteractor.getMarketById(marketId);
            marketMainInteractor.getStoryListByIdAndOffset(marketId, offset);
        }*/

        //dummy
        List<WeeklyWeatherArea> weeklyWeathers = new ArrayList<>();
        if(requestType == WeeklyWeatherAreaActivity.REQUEST_CITY) {

            WeeklyWeatherArea a1 = new WeeklyWeatherArea();
            a1.setAreaName("서울");
            WeeklyWeatherArea a2 = new WeeklyWeatherArea();
            a2.setAreaName("부산");
            WeeklyWeatherArea a3 = new WeeklyWeatherArea();
            a3.setAreaName("경기");
            WeeklyWeatherArea a4 = new WeeklyWeatherArea();
            a4.setAreaName("대전");
            WeeklyWeatherArea a5 = new WeeklyWeatherArea();
            a5.setAreaName("대구");
            WeeklyWeatherArea a6 = new WeeklyWeatherArea();
            a6.setAreaName("광주");
            WeeklyWeatherArea a7 = new WeeklyWeatherArea();
            a7.setAreaName("인천");
            WeeklyWeatherArea a8 = new WeeklyWeatherArea();
            a8.setAreaName("김포");
            WeeklyWeatherArea a9 = new WeeklyWeatherArea();
            a9.setAreaName("강원");
            WeeklyWeatherArea a10 = new WeeklyWeatherArea();
            a10.setAreaName("충청");

            weeklyWeathers.add(a1);
            weeklyWeathers.add(a2);
            weeklyWeathers.add(a3);
            weeklyWeathers.add(a4);
            weeklyWeathers.add(a5);
            weeklyWeathers.add(a6);
            weeklyWeathers.add(a7);
            weeklyWeathers.add(a8);
            weeklyWeathers.add(a9);
            weeklyWeathers.add(a10);
        }else if(requestType == WeeklyWeatherAreaActivity.REQUEST_GU) {
            WeeklyWeatherArea a1 = new WeeklyWeatherArea();
            a1.setAreaName("강나구");
            WeeklyWeatherArea a2 = new WeeklyWeatherArea();
            a2.setAreaName("강서구");
            WeeklyWeatherArea a3 = new WeeklyWeatherArea();
            a3.setAreaName("중랑구");
            WeeklyWeatherArea a4 = new WeeklyWeatherArea();
            a4.setAreaName("양천구");
            WeeklyWeatherArea a5 = new WeeklyWeatherArea();
            a5.setAreaName("중구");
            WeeklyWeatherArea a6 = new WeeklyWeatherArea();
            a6.setAreaName("마포구");
            WeeklyWeatherArea a7 = new WeeklyWeatherArea();
            a7.setAreaName("영등포구");
            WeeklyWeatherArea a8 = new WeeklyWeatherArea();
            a8.setAreaName("금천구");
            WeeklyWeatherArea a9 = new WeeklyWeatherArea();
            a9.setAreaName("동작구");
            WeeklyWeatherArea a10 = new WeeklyWeatherArea();
            a10.setAreaName("노원구");

            weeklyWeathers.add(a1);
            weeklyWeathers.add(a2);
            weeklyWeathers.add(a3);
            weeklyWeathers.add(a4);
            weeklyWeathers.add(a5);
            weeklyWeathers.add(a6);
            weeklyWeathers.add(a7);
            weeklyWeathers.add(a8);
            weeklyWeathers.add(a9);
            weeklyWeathers.add(a10);
        }else {
            WeeklyWeatherArea a1 = new WeeklyWeatherArea();
            a1.setAreaName("등촌1동");
            WeeklyWeatherArea a2 = new WeeklyWeatherArea();
            a2.setAreaName("등촌2동");
            WeeklyWeatherArea a3 = new WeeklyWeatherArea();
            a3.setAreaName("등촌3동");
            WeeklyWeatherArea a4 = new WeeklyWeatherArea();
            a4.setAreaName("등촌4동");
            WeeklyWeatherArea a5 = new WeeklyWeatherArea();
            a5.setAreaName("염창1동");
            WeeklyWeatherArea a6 = new WeeklyWeatherArea();
            a6.setAreaName("염창2동");
            WeeklyWeatherArea a7 = new WeeklyWeatherArea();
            a7.setAreaName("염창3동");
            WeeklyWeatherArea a8 = new WeeklyWeatherArea();
            a8.setAreaName("염창4동");
            WeeklyWeatherArea a9 = new WeeklyWeatherArea();
            a9.setAreaName("목1동");
            WeeklyWeatherArea a10 = new WeeklyWeatherArea();
            a10.setAreaName("목2동");

            weeklyWeathers.add(a1);
            weeklyWeathers.add(a2);
            weeklyWeathers.add(a3);
            weeklyWeathers.add(a4);
            weeklyWeathers.add(a5);
            weeklyWeathers.add(a6);
            weeklyWeathers.add(a7);
            weeklyWeathers.add(a8);
            weeklyWeathers.add(a9);
            weeklyWeathers.add(a10);
        }

        this.weeklyWeatherAreaListView.setWeeklyWeatherAreaListRecycleViewAdapterItem(weeklyWeathers);
        //this.placeAttractionView.setPlaceAttractionListRecycleViewAdapterItem(guiderList);
    }



    @Override
    public void onClickSearch() {

    }

    @Override
    public void onClickArea(WeeklyWeatherArea area, int position) {
        // 지역 선택 이벤트
        // 뷰로 넘어간다.
        if(requestType == WeeklyWeatherAreaActivity.REQUEST_CITY) {
            this.weeklyWeatherAreaListView.navigateToGuFragment(area);
        }else if (requestType == WeeklyWeatherAreaActivity.REQUEST_GU) {
            this.weeklyWeatherAreaListView.navigateToDongFragment(area);
        }else {
            this.weeklyWeatherAreaListView.navigateToWeeklyWeatherMain(area);
        }

    }

    /*@Override
    public void onClickImage(PlaceAttraction placeAttraction, int position) {
        // 어뎁터에서 뷰 아이템 클릭시 발생
        this.placeAttractionView.navigateToPlaceAttractionDetailActivity(placeAttraction, position);
    }


    @Override
    public void onSuccessGetGuiderList(List<Guider> guiderList) {

    }*/
}
