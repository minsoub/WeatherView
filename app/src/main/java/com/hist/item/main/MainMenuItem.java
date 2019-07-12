package com.hist.item.main;

public class MainMenuItem {

    private int image;
    private String title;
    private String description;
    private String key;

    public MainMenuItem(int image, String title, String description, String key) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.key = key;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
