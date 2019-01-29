package com.hist.weatherview;
import android.graphics.drawable.Drawable;

public class MenuViewItem {
    private Drawable iconDrawable;
    private String titleStr;
    private String descStr;
    private String key;

    public Drawable getIconDrawable() {
        return iconDrawable;
    }

    public void setIconDrawable(Drawable iconDrawable) {
        this.iconDrawable = iconDrawable;
    }

    public String getTitleStr() {
        return titleStr;
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }

    public String getDescStr() {
        return descStr;
    }

    public void setDescStr(String descStr) {
        this.descStr = descStr;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String id) {
        this.key = id;
    }
}
