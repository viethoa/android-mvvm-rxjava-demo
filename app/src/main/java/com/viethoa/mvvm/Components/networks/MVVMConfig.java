package com.viethoa.mvvm.Components.networks;

import com.viethoa.mvvm.BuildConfig;

/**
 * Created by VietHoa on 21/05/16.
 */
public class MVVMConfig {
    public static final String DEVELOPMENT_SERVER = "https://genesis-gym.herokuapp.com/api";
    public static final String PRODUCTION_SERVER = "https://genesis-gym.herokuapp.com/api";
    public static final String AUTHORIZATION_KEY = "token";

    public static final int DEVELOPMENT = 1;
    public static final int PRODUCTION = 2;

    public static String getBaseUrl() {
        StringBuilder builder = new StringBuilder();
        switch (getAppEnvironment()) {
            case DEVELOPMENT:
                builder.append(DEVELOPMENT_SERVER);
                break;

            case PRODUCTION:
                builder.append(PRODUCTION_SERVER);
                break;
        }
        return builder.toString();
    }

    public static int getAppEnvironment() {
        if (BuildConfig.DEBUG)
            return DEVELOPMENT;

        return PRODUCTION;
    }
}
