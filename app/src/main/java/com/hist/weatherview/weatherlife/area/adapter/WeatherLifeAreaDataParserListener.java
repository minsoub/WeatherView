package com.hist.weatherview.weatherlife.area.adapter;

import java.util.HashMap;
import java.util.List;

public interface WeatherLifeAreaDataParserListener {
    void OnStartParsing();
    void OnFinishParsing(HashMap<String, List<String>> expandableListDetail, HashMap<String, List<String>> expandableKeyListDetail);
}
