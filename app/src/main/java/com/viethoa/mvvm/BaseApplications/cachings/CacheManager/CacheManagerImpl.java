package com.viethoa.mvvm.BaseApplications.cachings.CacheManager;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.viethoa.mvvm.BaseApplications.BaseApplication;

import java.lang.reflect.Type;

import javax.inject.Inject;

/**
 * Created by VietHoa on 17/01/16.
 */
public class CacheManagerImpl implements CacheManager {
    private static final String TAG = "CacheManagerImpl";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Inject
    public CacheManagerImpl() {
        Application application = BaseApplication.getApplication();
        mSharedPreferences = application.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    @Override
    public void putString(String key, String value) {
        mEditor.putString(key, value);
        mEditor.apply();
    }

    @Override
    public void put(String key, Object object) {
        if (object == null) {
            mEditor.putString(key, "");
        } else {
            Gson gson = new Gson();
            String json = gson.toJson(object, object.getClass());
            mEditor.putString(key, json);
        }

        mEditor.apply();
    }

    @Override
    public String getString(String key) {
        return mSharedPreferences.getString(key, "");
    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        String json = mSharedPreferences.getString(key, "");
        if (TextUtils.isEmpty(json))
            return null;

        try {
            Gson gson = new Gson();
            T object = gson.fromJson(json, clazz);
            return object;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public <T> T get(String key, Type type) {
        String json = mSharedPreferences.getString(key, "");
        if (TextUtils.isEmpty(json))
            return null;

        try {
            Gson gson = new Gson();
            T object = gson.fromJson(json, type);
            return object;
        } catch (Exception ex) {
            return null;
        }
    }
}
