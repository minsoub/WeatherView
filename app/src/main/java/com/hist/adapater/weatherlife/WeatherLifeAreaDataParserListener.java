package com.hist.adapater.weatherlife;

import java.util.HashMap;
import java.util.List;

public interface WeatherLifeAreaDataParserListener {
    void OnFinishParsing(HashMap<String, List<String>> expandableListDetail, HashMap<String, List<String>> expandableKeyListDetail);
}
