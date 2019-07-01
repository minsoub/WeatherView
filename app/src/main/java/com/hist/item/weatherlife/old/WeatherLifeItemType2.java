package com.hist.item.weatherlife.old;

import java.util.ArrayList;

import static com.hist.item.weatherlife.old.WeatherLifeType.AIR_POLLUTANT_INDEX;
import static com.hist.item.weatherlife.old.WeatherLifeType.FREEZING_INDEX;
import static com.hist.item.weatherlife.old.WeatherLifeType.HEAT_INDEX;
import static com.hist.item.weatherlife.old.WeatherLifeType.HUMIDEX;
import static com.hist.item.weatherlife.old.WeatherLifeType.SENSORY_TEMPERATURE;

/***
 *  Author : JJW
 *  Date : 20180208
 *  Desc : 생활 기상 관련 아이템 중 3시간 단위로 저장하는 저장하는 WeatherLifeItem 관리 클래스
 */
public class WeatherLifeItemType2 extends WeatherLifeBase {

    ArrayList<WeatherLifePredictionItem> weatherLifePredictionItemArrayList;
    private ArrayList<WeatherLifeItem> weatherLifeItems;

    public WeatherLifeItemType2(WeatherLifeType weatherLifeType, ArrayList<WeatherLifePredictionItem> weatherLifePredictionItemArrayList) {
        super(weatherLifeType);
        this.weatherLifePredictionItemArrayList = weatherLifePredictionItemArrayList;
    }

    @Override
    public ArrayList<WeatherLifeItem> GetWeatherLifeItems() {

        //테스트용
        String h1 = "";
        String h2 = "";
        String h3 = "";
        String h4 = "";
        String h5 = "";


        WeatherLifeType weatherLifeType = getWeatherLifeType();

        if(weatherLifeType == AIR_POLLUTANT_INDEX)
        {
            h1 = "3시간 후\n옥외활동금지";
            h2 = "6시간 후\n옥외활동금지";
            h3 = "9시간 후\n옥외활동금지";
            h4 = "12시간 후\n옥외활동금지";
            h5 = "15시간 후\n옥외활동금지";
        }else if(weatherLifeType == FREEZING_INDEX)
        {
            h1 = "3시간 후\n수도관이 얼지 않도록 수도꼭지를 조금틀어 수도관에 물이흐르도록 함";
            h2 = "6시간 후\n수도관이 얼지 않도록 수도꼭지를 조금틀어 수도관에 물이흐르도록 함";
            h3 = "9시간 후\n수도꼭지 및 계량기 보온에 유의";
            h4 = "12시간 후\n수도꼭지 및 계량기 보온에 유의";
            h5 = "15시간 후\n수도꼭지 및 계량기 보온에 유의";
        }
        else if(weatherLifeType == HEAT_INDEX)
        {
            h1 = "3시간 후\n보통 사람이 열에 지속적으로 노출되면 신체 활동시 피로 위험 높음";
            h2 = "6시간 후\n보통 사람이 열에 지속적으로 노출되면, 신체 활동 시 열사병, 열경련, 열피폐 가능성 있음";
            h3 = "9시간 후\n보통사람이 열에 지속적으로 노출 되면 신체 활동시 일사병, 열경련, 열피폐 위험 높음";
            h4 = "12시간 후\n보통사람이 열에 지속적으로 노출 되면 신체 활동시 일사병, 열경련, 열피폐 위험 매우 높음";
            h5 = "15시간 후\n보통사람이 열에 지속적으로 노출 되면 신체 활동시 일사병, 열경련, 열피폐 매우 위험";
        }
        else if(weatherLifeType == SENSORY_TEMPERATURE)
        {
            h1 = "3시간 후\n추위를 느끼는 불편함 증가";
            h2 = "6시간 후\n추위를 느끼는 불편함 증가";
            h3 = "9시간 후\n추위를 느끼는 불편함 증가";
            h4 = "12시간 후\n추위를 느끼는 불편함 증가";
            h5 = "15시간 후\n추위를 느끼는 불편함 증가";
        }
        else if(weatherLifeType == HUMIDEX)
        {
            h1 = "3시간 후\n전원 쾌적함을 느낌";
            h2 = "6시간 후\n전원 쾌적함을 느낌";
            h3 = "9시간 후\n전원 쾌적함을 느낌";
            h4 = "12시간 후\n전원 쾌적함을 느낌";
            h5 = "15시간 후\n불쾌감을 나타내기 시작함";
        }

        WeatherLifeItem s1 = new WeatherLifeItem(weatherLifePredictionItemArrayList.get(0).getPreductionTimeValue(), h1, weatherLifeType);
        WeatherLifeItem s2 = new WeatherLifeItem(weatherLifePredictionItemArrayList.get(1).getPreductionTimeValue(), h2, weatherLifeType);
        WeatherLifeItem s3 = new WeatherLifeItem(weatherLifePredictionItemArrayList.get(2).getPreductionTimeValue(), h3, weatherLifeType);
        WeatherLifeItem s4 = new WeatherLifeItem(weatherLifePredictionItemArrayList.get(3).getPreductionTimeValue(), h4, weatherLifeType);
        WeatherLifeItem s5 = new WeatherLifeItem(weatherLifePredictionItemArrayList.get(4).getPreductionTimeValue(), h5, weatherLifeType);

        weatherLifeItems = new ArrayList<>();
        weatherLifeItems.add(s1);
        weatherLifeItems.add(s2);
        weatherLifeItems.add(s3);
        weatherLifeItems.add(s4);
        weatherLifeItems.add(s5);

        return weatherLifeItems;
    }
}
