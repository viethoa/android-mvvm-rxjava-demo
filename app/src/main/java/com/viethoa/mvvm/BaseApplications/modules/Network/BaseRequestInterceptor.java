package com.viethoa.mvvm.BaseApplications.modules.Network;

import android.text.TextUtils;

import com.viethoa.mvvm.BaseApplications.cachings.UserSessionManager.UserSessionManager;
import com.viethoa.mvvm.Components.networks.MVVMConfig;

import javax.inject.Inject;

import retrofit.RequestInterceptor;

/**
 * Created by VietHoa on 18/01/16.
 */
public class BaseRequestInterceptor implements RequestInterceptor {

    @Inject
    UserSessionManager mUserSessionManager;

    @Inject
    public BaseRequestInterceptor() {
        // requirement
    }

    @Override
    public void intercept(RequestFacade request) {
        //request.addHeader("Content-Type", "application/json");
        //request.addHeader("Content-Type","multipart/form-data");
        String accessToken = mUserSessionManager.getCurrentAccessToken();
        if (!TextUtils.isEmpty(accessToken)) {
            request.addHeader(MVVMConfig.AUTHORIZATION_KEY, accessToken);
            request.addQueryParam(MVVMConfig.AUTHORIZATION_KEY, accessToken);
        }
    }
}
