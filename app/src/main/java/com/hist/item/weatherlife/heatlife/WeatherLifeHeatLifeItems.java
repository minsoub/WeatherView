package com.hist.item.weatherlife.heatlife;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "code",
        "areaNo",
        "date",
        "h3",
        "h6",
        "h9",
        "h12",
        "h15",
        "h18",
        "h21",
        "h24",
        "h27",
        "h30",
        "h33",
        "h36",
        "h39",
        "h42",
        "h45",
        "h48",
        "h51",
        "h54",
        "h57",
        "h60",
        "h63",
        "h66"
})
public class WeatherLifeHeatLifeItems {
    @JsonProperty("code")
    private String code;
    @JsonProperty("areaNo")
    private String areaNo;
    @JsonProperty("date")
    private String date;
    @JsonProperty("h3")
    private String h3;
    @JsonProperty("h6")
    private String h6;
    @JsonProperty("h9")
    private String h9;
    @JsonProperty("h12")
    private String h12;
    @JsonProperty("h15")
    private String h15;
    @JsonProperty("h18")
    private String h18;
    @JsonProperty("h21")
    private String h21;
    @JsonProperty("h24")
    private String h24;
    @JsonProperty("h27")
    private String h27;
    @JsonProperty("h30")
    private String h30;
    @JsonProperty("h33")
    private String h33;
    @JsonProperty("h36")
    private String h36;
    @JsonProperty("h39")
    private String h39;
    @JsonProperty("h42")
    private String h42;
    @JsonProperty("h45")
    private String h45;
    @JsonProperty("h48")
    private String h48;
    @JsonProperty("h51")
    private String h51;
    @JsonProperty("h54")
    private String h54;
    @JsonProperty("h57")
    private String h57;
    @JsonProperty("h60")
    private String h60;
    @JsonProperty("h63")
    private String h63;
    @JsonProperty("h66")
    private String h66;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("areaNo")
    public String getAreaNo() {
        return areaNo;
    }

    @JsonProperty("areaNo")
    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("h3")
    public String getH3() {
        return h3;
    }

    @JsonProperty("h3")
    public void setH3(String h3) {
        this.h3 = h3;
    }

    @JsonProperty("h6")
    public String getH6() {
        return h6;
    }

    @JsonProperty("h6")
    public void setH6(String h6) {
        this.h6 = h6;
    }

    @JsonProperty("h9")
    public String getH9() {
        return h9;
    }

    @JsonProperty("h9")
    public void setH9(String h9) {
        this.h9 = h9;
    }

    @JsonProperty("h12")
    public String getH12() {
        return h12;
    }

    @JsonProperty("h12")
    public void setH12(String h12) {
        this.h12 = h12;
    }

    @JsonProperty("h15")
    public String getH15() {
        return h15;
    }

    @JsonProperty("h15")
    public void setH15(String h15) {
        this.h15 = h15;
    }

    @JsonProperty("h18")
    public String getH18() {
        return h18;
    }

    @JsonProperty("h18")
    public void setH18(String h18) {
        this.h18 = h18;
    }

    @JsonProperty("h21")
    public String getH21() {
        return h21;
    }

    @JsonProperty("h21")
    public void setH21(String h21) {
        this.h21 = h21;
    }

    @JsonProperty("h24")
    public String getH24() {
        return h24;
    }

    @JsonProperty("h24")
    public void setH24(String h24) {
        this.h24 = h24;
    }

    @JsonProperty("h27")
    public String getH27() {
        return h27;
    }

    @JsonProperty("h27")
    public void setH27(String h27) {
        this.h27 = h27;
    }

    @JsonProperty("h30")
    public String getH30() {
        return h30;
    }

    @JsonProperty("h30")
    public void setH30(String h30) {
        this.h30 = h30;
    }

    @JsonProperty("h33")
    public String getH33() {
        return h33;
    }

    @JsonProperty("h33")
    public void setH33(String h33) {
        this.h33 = h33;
    }

    @JsonProperty("h36")
    public String getH36() {
        return h36;
    }

    @JsonProperty("h36")
    public void setH36(String h36) {
        this.h36 = h36;
    }

    @JsonProperty("h39")
    public String getH39() {
        return h39;
    }

    @JsonProperty("h39")
    public void setH39(String h39) {
        this.h39 = h39;
    }

    @JsonProperty("h42")
    public String getH42() {
        return h42;
    }

    @JsonProperty("h42")
    public void setH42(String h42) {
        this.h42 = h42;
    }

    @JsonProperty("h45")
    public String getH45() {
        return h45;
    }

    @JsonProperty("h45")
    public void setH45(String h45) {
        this.h45 = h45;
    }

    @JsonProperty("h48")
    public String getH48() {
        return h48;
    }

    @JsonProperty("h48")
    public void setH48(String h48) {
        this.h48 = h48;
    }

    @JsonProperty("h51")
    public String getH51() {
        return h51;
    }

    @JsonProperty("h51")
    public void setH51(String h51) {
        this.h51 = h51;
    }

    @JsonProperty("h54")
    public String getH54() {
        return h54;
    }

    @JsonProperty("h54")
    public void setH54(String h54) {
        this.h54 = h54;
    }

    @JsonProperty("h57")
    public String getH57() {
        return h57;
    }

    @JsonProperty("h57")
    public void setH57(String h57) {
        this.h57 = h57;
    }

    @JsonProperty("h60")
    public String getH60() {
        return h60;
    }

    @JsonProperty("h60")
    public void setH60(String h60) {
        this.h60 = h60;
    }

    @JsonProperty("h63")
    public String getH63() {
        return h63;
    }

    @JsonProperty("h63")
    public void setH63(String h63) {
        this.h63 = h63;
    }

    @JsonProperty("h66")
    public String getH66() {
        return h66;
    }

    @JsonProperty("h66")
    public void setH66(String h66) {
        this.h66 = h66;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
