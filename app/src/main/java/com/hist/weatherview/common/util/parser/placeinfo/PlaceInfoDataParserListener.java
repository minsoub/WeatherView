package com.hist.weatherview.common.util.parser.placeinfo;

import java.util.HashMap;
import java.util.List;

public interface PlaceInfoDataParserListener {
    void OnStartParsing();
    void OnFinishParsing(HashMap<String, List<String>> expandableListDetail, HashMap<String, List<String>> expandableKeyListDetail);
}
