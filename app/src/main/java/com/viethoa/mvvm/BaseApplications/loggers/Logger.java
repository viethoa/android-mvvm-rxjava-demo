package com.viethoa.mvvm.BaseApplications.loggers;

import android.support.design.BuildConfig;
import android.util.Log;

/**
 * Created by VietHoa on 22/05/16.
 */
public class Logger {
    private static void sendLog(String tag, String message) {
        //Crashlytics.log(String.format("%s : %s", tag, message));
    }

    private static void sendException(Exception ex) {
        //Crashlytics.logException(ex);
    }

    private static void sendException(String message) {
        //Crashlytics.logException(new Throwable(message));
    }

    public static void i(String tag, String message) {
        if (BuildConfig.DEBUG)
            Log.i(tag, message);
        else {
            sendLog(tag, message);
        }
    }

    public static void e(String tag, Exception ex) {
        if (BuildConfig.DEBUG)
            Log.e(tag, ex.getMessage());
        else {
            sendException(ex);
        }
    }

    public static void e(String tag, String message) {
        if (BuildConfig.DEBUG)
            Log.e(tag, message + " |error|");
        else {
            sendException(message);
        }
    }

    public static void d(String tag, String message) {
        if (BuildConfig.DEBUG)
            Log.d(tag, message);
        else {
            sendLog(tag, message);
        }
    }

    public static void v(String tag, String message) {
        if (BuildConfig.DEBUG)
            Log.v(tag, message);
        else {
            sendLog(tag, message);
        }
    }
}
