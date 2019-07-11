package com.hist.weatherview.weatherlife.main.adapter;

import com.hist.item.weatherlife.WeatherLifeItem;

/***
 *  Author : JJW
 *  Date : 20180208
 *  Desc : 생활 기상 관련 아이템 Base 클래스
 */
public interface WeatherLifeTypeListener {
    // ViewPager가 이동하면서 생활기상 타입을 변경하는 리스너
    void onChangeWeatherLifeType(WeatherLifeItem weatherLifeItem, String type);
}
