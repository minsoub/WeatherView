package com.hist.weatherview.common.util;



import com.hist.item.timeweather.TimeWeatherResult;
import com.hist.item.timeweather.TimeWeatherResultTime;
import com.hist.weatherview.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class WeatherUtil {


    public static String getTimeWeatherResultTimeValueByCategory(TimeWeatherResult result, String category)
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

    public static int getSkyImageByValue(String value)
    {
        double intValue = Double.parseDouble(value);
        int retDrawable;
        if(intValue == 1 )
        {
            //맑음
            retDrawable = R.drawable.art_clear;
        }else if(intValue == 2)
        {
            //구름조금
            retDrawable = R.drawable.art_light_clouds;
        }else if(intValue == 3)
        {
            retDrawable = R.drawable.art_clouds;
        }else {
            // 구름 많음
            retDrawable = R.drawable.art_clouds;
        }

        return retDrawable;
    }

    public static int getSkyImageByString(String value)
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

    public static String getSkyTypeStringByValue(String value)
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


    public String insertString(String str, String iStr, int position)
    {
        StringBuilder strBuf = new StringBuilder(str);

        strBuf.insert(position, iStr);

        return strBuf.toString();
    }

/*
    public static String getWeeklyDatByValue(String value)
    {
        if(value.contains("3"))
        {

        }
    }
*/

}
