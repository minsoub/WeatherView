package com.hist.item.weatherlife.old;

import android.graphics.drawable.Drawable;

import static com.hist.item.weatherlife.old.WeatherLifeType.*;

/***
 *  Author : JJW
 *  Date : 20180208
 *  Desc : 생활 기상 관련 아이템 클래스
 */
public class WeatherLifeItem {
    private Drawable icon;
    private String mStatus;
    private String mDescription;
    private String value;               // 지수 값
    private WeatherLifeType type;
    private WeatherLifeLevelChecker weatherLifeLevelChecker;
    public WeatherLifeItem() {

    }

    public WeatherLifeItem(String value, String desc) {
        this.value = value;
        this.mDescription = desc;
    }

    public WeatherLifeItem(String value, String desc, WeatherLifeType type) {
        this.value = value;
        this.mDescription = desc;
        this.type = type;
        this.weatherLifeLevelChecker = new WeatherLifeLevelChecker();
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public WeatherLifeType getType() {
        return type;
    }

    public void setType(WeatherLifeType type) {
        this.type = type;
    }

    public String GetWeatherLifeSeverityLevel(){

        return this.weatherLifeLevelChecker.GetWeatherLifeLevel(this);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String GetWeatherTypeName() {
        String retVal;

        switch (type)
        {
            case FOOD_POISONING:
                retVal = "식중독 지수";
                break;
            case UV_INDEX:
                retVal = "자외선 지수";
                break;
            case AIR_POLLUTANT_INDEX:
                retVal = "대기오염확산지수";
                break;
            case FREEZING_INDEX:
                retVal = "동파가능지수";
                break;
            case HEAT_INDEX:
                retVal = "열 지수";
                break;
            case SENSORY_TEMPERATURE:
                retVal = "체감온도 지수";
                break;
            case HUMIDEX:
                retVal = "불쾌 지수";
                break;
            default:
                retVal = "없음";
                break;
        }
        return retVal;
    }

    private class WeatherLifeLevelChecker {

        public String GetWeatherLifeLevel(WeatherLifeItem item)
        {
            // 타입 별 값의 범위에 따라 Level을 반환한다.
            String retVal = "low";
            int status = Integer.parseInt(item.getValue());
            String retStatus = "";
            if(item.getType().equals(AIR_POLLUTANT_INDEX) || item.getType().equals(FREEZING_INDEX))
            {
                if(status >= 100) {
                    retVal = "low";

                } else if(status >= 75) {
                    retVal = "normal";
                } else if(status >= 50) {
                    retVal = "high";
                } else if(status >= 25) {
                    retVal = "veryHigh";
                } else
                {
                    retVal = "veryHigh";
                }
            } else if(item.getType().equals(UV_INDEX)) {
                if(status >= 11) {
                    retVal = "danger";
                } else if(status >= 8) {
                    retVal = "veryHigh";
                } else if(status >= 6) {
                    retVal = "high";
                } else if(status >= 3) {
                    retVal = "normal";
                } else
                {
                    retVal = "low";
                }
            } else if(item.getType().equals(HUMIDEX)) {
                if(status >= 80) {
                    retVal = "veryHigh";
                } else if(status >= 75) {
                    retVal = "high";
                } else if(status >= 68) {
                    retVal = "normal";
                } else
                {
                    retVal = "low";
                }
            } else if(item.getType().equals(HEAT_INDEX)) {
                if (status >= 66) {
                    retVal = "danger";
                } else if (status >= 54) {
                    retVal = "veryHigh";
                } else if (status >= 41) {
                    retVal = "high";
                } else if (status >= 32) {
                    retVal = "normal";
                }
                else {
                    retVal = "low";
                }
            } else if(item.getType().equals(FOOD_POISONING)) {
                if (status >= 95) {
                    retVal = "danger";
                } else if (status >= 70) {
                    retVal = "veryHigh";
                } else if (status >= 35) {
                    retVal = "high";
                } else if (status >= 0) {
                    retVal = "normal";
                }
                else {
                    retVal = "low";
                }
            }

            retStatus = ConvertLevelToString(retVal);
            item.setStatus(retStatus);

            return retVal;
        }

        public String ConvertLevelToString(String level)
        {
            String retVal = "";
            if("danger".equals(level))
            {
                retVal = "위험";
            }else if("veryHigh".equals(level))
            {
                retVal = "매우높음";
            }else if("high".equals(level))
            {
                retVal = "높음";
            }else if("normal".equals(level))
            {
                retVal = "보통";
            }else
            {
                retVal = "낮음";
            }

            return retVal;
        }
    }
}
