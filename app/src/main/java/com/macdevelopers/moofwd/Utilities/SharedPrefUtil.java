package com.macdevelopers.moofwd.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;

/**
 * Created by Suraj Nirmal
 */

public class SharedPrefUtil {
    public static String PREF_NAME = "Media";
    private static String KEY_USER = "user";
    private static String KEY_TIME_TRACKER_DATA = "TimeTrackerData";
    private static String KEY_LAT = "latitude";
    private static String KEY_LONGI = "longitude";

    public static SharedPreferences preferences;
    public static String KEY_USER_TOKEN = "token";
    public static String KEY_BRAND_ID = "brandID";
    public static String KEY_BRAND_NAME = "brandName";
    public static String KEY_MODEL_ID = "modelID";
    public static String KEY_MODEL_NAME = "modelName";
    public static String KEY_FUEL_TYPE = "fuelType";

    /*public static UserLoginPOJO getUserLogin(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String data = preferences.getString(KEY_USER, null);
        Gson gson = new Gson();
        if (data == null){

            return null;

        } else{

            return gson.fromJson(data, UserLoginPOJO.class);
        }
    }*/


    public static void setUserLogin(Context context, String value) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_USER, value).apply();
    }

    public static void setClear(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().remove(KEY_USER).apply();
    }

    public static void setLocation(Context context, float lat, float lon) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().putFloat(KEY_LAT, lat).putFloat(KEY_LONGI, lon).apply();
    }

    public static Location getLocation(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Location location = new Location(LocationManager.GPS_PROVIDER);
        location.setLatitude(preferences.getFloat(KEY_LAT, 0.0f));
        location.setLongitude(preferences.getFloat(KEY_LONGI, 0.0f));

        return location;
    }

    public static void setToken(Context context, String Token) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_USER_TOKEN, Token).apply();
    }

    public static String getToken(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String Token = preferences.getString(KEY_USER_TOKEN, "");

        return Token;
    }

    public static void setTimeTrackerData(Context context, String timeTrackerData) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_TIME_TRACKER_DATA, timeTrackerData).apply();
    }

    public static String getTimeTrackerData(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String TimeTrackerData = preferences.getString(KEY_TIME_TRACKER_DATA, "");

        return TimeTrackerData;
    }

    public static void setClearTimeTrackerData(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().remove(KEY_TIME_TRACKER_DATA).remove(KEY_TIME_TRACKER_DATA).apply();
    }


    //TODO BRAND SharedPreferences
    public static void setBrandID(Context context, String brandId) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_BRAND_ID, brandId).apply();
    }

    public static String getBrandID(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String brandId = preferences.getString(KEY_BRAND_ID, "");

        return brandId;
    }

    public static void setBrandName(Context context, String brandName) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_BRAND_NAME, brandName).apply();
    }

    public static String getBrandName(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String brandName = preferences.getString(KEY_BRAND_NAME, "");

        return brandName;
    }

    //TODO MODEL SharedPreferences
    public static void setModelID(Context context, String modelId) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_MODEL_ID, modelId).apply();
    }

    public static String getModelID(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String modelId = preferences.getString(KEY_MODEL_ID, "");

        return modelId;
    }

    public static void setModelName(Context context, String modelName) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_MODEL_NAME, modelName).apply();
    }

    public static String getModelName(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String modelName = preferences.getString(KEY_MODEL_NAME, "");

        return modelName;
    }

    //TODO FUEL TYPE SharedPreferences
    public static void setFuelType(Context context, String fuelType) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_FUEL_TYPE, fuelType).apply();
    }

    public static String getFuelType(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String fuelType = preferences.getString(KEY_FUEL_TYPE, "");

        return fuelType;
    }

}
