package com.sevencstudio.harish.networksyncvolley;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by harish on 16/03/16.
 */
public class SharedPreferenceStorage {

    static SharedPreferences sharedpreferences;
    static SharedPreferences.Editor editor;

    public static void SetContext(Context context) {
        sharedpreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }

    public static void Store(String key, String value) {

        editor.putString(key, value);
        editor.commit();

    }

    public static void Store(String key, int value) {

        editor.putInt(key, value);
        editor.commit();

    }

    public static void Store(String key, float value) {

        editor.putFloat(key, value);
        editor.commit();

    }

    public static void Store(String key, boolean value) {

        editor.putBoolean(key, value);
        editor.commit();

    }

    public static void Store(String key, long value) {

        editor.putLong(key, value);
        editor.commit();

    }

    public static String GetString(String key) {
        return sharedpreferences.getString(key, null);
    }

    public static int GetInt(String key) {
        return sharedpreferences.getInt(key, 0);
    }

    public static float GetFloat(String key) {
        return sharedpreferences.getFloat(key, 0.0f);
    }

    public static float GetLong(String key) {
        return sharedpreferences.getLong(key, 0);
    }

    public static boolean GetBoolean(String key) {
        return sharedpreferences.getBoolean(key, false);
    }

}
