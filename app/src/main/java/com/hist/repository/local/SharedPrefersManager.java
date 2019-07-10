package com.hist.repository.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hist.item.common.SharedPlaceInfo;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class SharedPrefersManager {
    private Context context;

    private SharedPreferences sharedWeeklyWeatherPlacePreferences;
    private SharedPreferences sharedTimeWeatherPlacePreferences;
    private SharedPreferences sharedWeatherLifePlacePreferences;

    public SharedPrefersManager(Context context) {
        this.context = context;

        if (sharedWeeklyWeatherPlacePreferences == null) {
            sharedWeeklyWeatherPlacePreferences = context.getSharedPreferences("weeklyWeatherFavoritePlace", Context.MODE_PRIVATE);
        }
        if (sharedTimeWeatherPlacePreferences == null) {
            sharedTimeWeatherPlacePreferences = context.getSharedPreferences("timeWeatherFavoritePlace", Context.MODE_PRIVATE);
        }
        if (sharedWeatherLifePlacePreferences == null) {
            sharedWeatherLifePlacePreferences = context.getSharedPreferences("weatherLifePlace", Context.MODE_PRIVATE);
        }

/*        if(sharedNotificationPreferences == null){
            sharedNotificationPreferences = context.getSharedPreferences("notification", Context.MODE_PRIVATE);
        }*/
    }

    public void setWeeklyWeatherFavoritePlaceArrayListPref(ArrayList<SharedPlaceInfo> values) {
        //SharedPreferences prefs = getSharedPreferences(SETTINGS_PLAYER, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedWeeklyWeatherPlacePreferences.edit();
        Gson gson = new Gson();
        String stringUser = gson.toJson(values);
        editor.putString("weeklyWeatherFavoritePlace", stringUser).apply();
        editor.commit();
    }

    public ArrayList<SharedPlaceInfo> getWeeklyWeatherFavoritePlaceArrayListPref() {

        Gson gson = new Gson();
        String response=sharedWeeklyWeatherPlacePreferences.getString("weeklyWeatherFavoritePlace" , "");
        ArrayList<SharedPlaceInfo> lstArrayList = gson.fromJson(response,
                new TypeToken<List<SharedPlaceInfo>>(){}.getType());

        return lstArrayList;
    }

    public void setTimeWeatherFavoritePlaceArrayListPref(ArrayList<SharedPlaceInfo> values) {
        //SharedPreferences prefs = getSharedPreferences(SETTINGS_PLAYER, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedWeeklyWeatherPlacePreferences.edit();
        Gson gson = new Gson();
        String stringUser = gson.toJson(values);
        editor.putString("timeWeatherFavoritePlace", stringUser).apply();
        editor.commit();
    }

    public ArrayList<SharedPlaceInfo> getTimeWeatherFavoritePlaceArrayListPref() {

        Gson gson = new Gson();
        String response=sharedWeeklyWeatherPlacePreferences.getString("timeWeatherFavoritePlace" , "");
        ArrayList<SharedPlaceInfo> lstArrayList = gson.fromJson(response,
                new TypeToken<List<SharedPlaceInfo>>(){}.getType());

        return lstArrayList;
    }

    public void setWeatherLifePlacePref(SharedPlaceInfo values) {
        //SharedPreferences prefs = getSharedPreferences(SETTINGS_PLAYER, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedWeeklyWeatherPlacePreferences.edit();
        Gson gson = new Gson();
        String stringUser = gson.toJson(values);
        editor.putString("weatherLifePlace", stringUser).apply();
        editor.commit();
    }

    public ArrayList<SharedPlaceInfo> getWeatherLifePlacePref() {

        Gson gson = new Gson();
        String response=sharedWeeklyWeatherPlacePreferences.getString("weatherLifePlace" , "");
        ArrayList<SharedPlaceInfo> lstArrayList = gson.fromJson(response,
                new TypeToken<List<SharedPlaceInfo>>(){}.getType());

        return lstArrayList;
    }


    public void setTimeWeatherStartPlace(SharedPlaceInfo place){
        SharedPreferences.Editor editor = sharedWeeklyWeatherPlacePreferences.edit();
        Gson gson = new Gson();
        String stringPlace = gson.toJson(place);
        editor.putString(SharedPrefersFlag.TIME_WEATHER_START_PLACE, stringPlace).apply();
        editor.apply();
    }

    public SharedPlaceInfo getTimeWeatherStartPlace() {

        Gson gson = new Gson();
        String response = sharedWeeklyWeatherPlacePreferences.getString(SharedPrefersFlag.TIME_WEATHER_START_PLACE, "");
        SharedPlaceInfo placeInfo = gson.fromJson(response, SharedPlaceInfo.class);

        return placeInfo;
    }

    public void setWeeklyWeatherStartPlace(SharedPlaceInfo place){
        SharedPreferences.Editor editor = sharedWeeklyWeatherPlacePreferences.edit();
        Gson gson = new Gson();
        String stringPlace = gson.toJson(place);
        editor.putString(SharedPrefersFlag.WEEKLY_WEATHER_START_PLACE, stringPlace).apply();
        editor.apply();
    }

    public SharedPlaceInfo getWeeklyWeatherStartPlace() {

        Gson gson = new Gson();
        String response = sharedWeeklyWeatherPlacePreferences.getString(SharedPrefersFlag.WEEKLY_WEATHER_START_PLACE, "");
        SharedPlaceInfo placeInfo = gson.fromJson(response, SharedPlaceInfo.class);

        return placeInfo;
    }

    public void setWeatherLifeStartPlace(SharedPlaceInfo place){
        SharedPreferences.Editor editor = sharedWeeklyWeatherPlacePreferences.edit();
        Gson gson = new Gson();
        String stringPlace = gson.toJson(place);
        editor.putString(SharedPrefersFlag.WEATHER_LIFE_START_PLACE, stringPlace).apply();
        editor.apply();
    }

    public SharedPlaceInfo getWeatherLifeStartPlace() {

        Gson gson = new Gson();
        String response = sharedWeeklyWeatherPlacePreferences.getString(SharedPrefersFlag.WEATHER_LIFE_START_PLACE, "");
        SharedPlaceInfo placeInfo = gson.fromJson(response, SharedPlaceInfo.class);

        return placeInfo;
    }



/*
    public User getUser() {
        Gson gson = new Gson();
        String json = sharedUserPreferences.getString("user", "");
        User user = gson.fromJson(json, User.class);
        return user;
    }

    public void setUser(User user) {
        SharedPreferences.Editor editor = sharedUserPreferences.edit();

        Gson gson = new Gson();
        String stringUser = gson.toJson(user);
        editor.putString("user", stringUser).apply();
        editor.commit();
    }

    public void removeUser(){
        SharedPreferences.Editor editor = sharedUserPreferences.edit();
        editor.remove("user").apply();
        editor.commit();
    }

    public boolean isAllowedForNotification(){
        return  sharedNotificationPreferences.getBoolean("isAllowed", true);
    }

    public void setAllowedForNotification(boolean isAllowed){
        SharedPreferences.Editor editor = sharedNotificationPreferences.edit();
        editor.putBoolean("isAllowed", isAllowed);
        editor.apply();
    }*/
}
