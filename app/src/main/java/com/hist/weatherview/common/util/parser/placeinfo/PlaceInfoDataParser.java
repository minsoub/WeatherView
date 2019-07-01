package com.hist.weatherview.common.util.parser.placeinfo;

import android.content.Context;

import com.hist.weatherview.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/***
 *  Author : JJW
 *  Date : 20180225
 *  Desc : 생활기상 지역 코드 데이터 파서. (2단계 까지만 파싱)
 */
public class PlaceInfoDataParser {

    private Context mContext;
    private HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
    private HashMap<String, List<String>> expandableKeyListDetail = new HashMap<String, List<String>>();
    private List<String> list;
    private String prevCityName;
    private PlaceInfoDataParserListener listener;


    public PlaceInfoDataParser(Context mContext) {
        this.mContext = mContext;
        this.listener = (PlaceInfoDataParserListener)mContext;
    }

    public void LoadWeatherLifePlaceInfo()
    {
        LoadWeatherLifePlaceRawDataThread thread = new LoadWeatherLifePlaceRawDataThread();
        listener.OnStartParsing();
        thread.start();
    }


    //Thread로 데이터를 읽어 들인다.
    class LoadWeatherLifePlaceRawDataThread extends Thread {
        @Override
        public void run()
        {

            InputStream inputData = mContext.getResources().openRawResource(R.raw.place_info);
            try {
                //데이터 다운로드..

                StringBuffer buffer = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputData));

                String line;
                while ((line = reader.readLine()) != null) {
                    ParseCSVFile(line);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 종료 시점 listener를 통해서 알린다.
            listener.OnFinishParsing(expandableListDetail, expandableKeyListDetail);
        }

        private void ParseCSVFile(String line) {
            String csvSplitBy = ",";
            String[] areaInfo = line.split(csvSplitBy);
            String key, city, cityDetail = null, cityDetailDetail = null;

            key = areaInfo[0];
            city = areaInfo[1];
            //length가 2인 경우는 city만 있는 경우라서, city를 cityDetail에 추가해준다.
            if(areaInfo.length == 2)
            {
                cityDetail = city;
            }
            if(areaInfo.length > 2){
                cityDetail = areaInfo[2];
            }
            if(areaInfo.length > 3) {
                cityDetailDetail = areaInfo[3];
            }

            if(areaInfo.length == 4){
                return;
            }
            //1. cityDetail이 size가 0 이면 대분류가 된다.

            //1.1 ArrayList가 존재 하는지 본다.
            if(expandableListDetail.get(city) == null)
            {
                List<String> list = new ArrayList<String>();
                List<String> keyList = new ArrayList<String>();
                list.add(cityDetail);
                keyList.add(key);
                expandableListDetail.put(city, list);
                expandableKeyListDetail.put(city, keyList);
            }else
            {
                List<String> list = expandableListDetail.get(city);
                List<String> keyList = expandableKeyListDetail.get(city);
                list.add(cityDetail);
                keyList.add(key);
            }
        }
    }
}
