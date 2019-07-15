package com.hist.weatherview.common.util;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {


    public static String getCalculateDateByDateTime(String dateTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long currentTimeMillis = System.currentTimeMillis();
        long createdTimeMillis = date.getTime();
        long differentTimeMillis = (currentTimeMillis - createdTimeMillis) / 1000;

        String message = null;

        if (differentTimeMillis < 60) {
            message = differentTimeMillis + "초전";
        } else if ((differentTimeMillis /= 60) < 60) {
            message = differentTimeMillis + "분전";
        } else if ((differentTimeMillis /= 60) < 24) {
            message = (differentTimeMillis) + "시간전";
        } else if ((differentTimeMillis /= 24) < 7) {
            message = (differentTimeMillis) + "일전";
        } else {
            simpleDateFormat= new SimpleDateFormat("yy.M.d aa h:mm");
            message = simpleDateFormat.format(date);
        }

        return message;
    }

    public static String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();

        String currentDay = date.toString();

        return currentDay;
    }

    public static String getCurrentDateByYYYYMMDD() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
//Add one to month {0 - 11}
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        //timeCheck
        int hour = calendar.get(Calendar.HOUR);
        if(hour < 5)
        {
            day -= 1;
        }

        StringBuilder stringBuilder = new StringBuilder(Integer.toString(year));
        stringBuilder.append((month < 10) ? new StringBuilder("0").append(Integer.toString(month)) : Integer.toString(month));
        stringBuilder.append((day < 10) ? new StringBuilder("0").append(Integer.toString(day)) : Integer.toString(day));


        return stringBuilder.toString();
    }

    public static String getMiddleDateStringByValue(String value) {

        String retVal = "";
        if(value.contains("3"))
        {
            retVal = "3일후";
        }else if(value.contains("4"))
        {
            retVal = "4일후";
        }else if(value.contains("5"))
        {
            retVal = "5일후";
        }else if(value.contains("6"))
        {
            retVal = "6일후";
        }else if(value.contains("7"))
        {
            retVal = "7일후";
        }else if(value.contains("8"))
        {
            retVal = "8일후";
        }else if(value.contains("9"))
        {
            retVal = "9일후";
        }

        return retVal;
    }
}
