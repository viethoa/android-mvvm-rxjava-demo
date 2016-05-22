package com.viethoa.mvvm.BaseApplications.cachings.UserSessionManager;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.viethoa.mvvm.BaseApplications.BaseApplication;
import com.viethoa.mvvm.Features.Models.User;

import javax.inject.Inject;

/**
 * Created by VietHoa on 17/01/16.
 */
public class UserSessionManagerImpl implements UserSessionManager {
    private static final String PREF_KEY_USER_INFO = "key_user_info";

    private static User cacheUser;
    private static String cacheAccessToken;
    private SharedPreferences mSharedPreferences;

    @Inject
    public UserSessionManagerImpl() {
        Application application = BaseApplication.getApplication();
        mSharedPreferences = application.getSharedPreferences("pref_user_session_data", Context.MODE_PRIVATE);
    }

    @Override
    public void clearAllSavedUserData() {
        cacheUser = null;
        cacheAccessToken = null;
        mSharedPreferences.edit().putString(PREF_KEY_USER_INFO, "").apply();
        mSharedPreferences.edit().clear().apply();
    }

    @Override
    public String getCurrentAccessToken() {
        if (!TextUtils.isEmpty(cacheAccessToken))
            return cacheAccessToken;

        String accessToken = null;
        User user = getCurrentUser();
        if (user != null)
            accessToken = user.getToken();
        if (accessToken != null)
            cacheAccessToken = accessToken;

        return cacheAccessToken;
    }

    @Override
    public void saveCurrentUser(User user) {
        if (user == null)
            return;

        // memory cache
        cacheUser = user;
        cacheAccessToken = user.getToken();

        String json = (new Gson()).toJson(user);
        if (TextUtils.isEmpty(json))
            return;

        mSharedPreferences.edit().putString(PREF_KEY_USER_INFO, json).apply();
    }

    @Override
    public User getCurrentUser() {
        if (cacheUser != null)
            return cacheUser;

        User user;
        String jsonData = mSharedPreferences.getString(PREF_KEY_USER_INFO, "");

        //Convert back to User data model
        try {
            user = (new Gson()).fromJson(jsonData, User.class);
        } catch (Exception e) {
            e.printStackTrace();
            user = null;
        }

        cacheUser = user;
        if (user != null)
            cacheAccessToken = user.getToken();

        return cacheUser;
    }

    @Override
    public Number getCurrentUserID() {
        User currentUser = getCurrentUser();
        if (currentUser != null)
            return currentUser.getId();
        return null;
    }

}
