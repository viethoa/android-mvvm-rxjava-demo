package com.viethoa.mvvm.BaseApplications.snackbars;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;

import com.viethoa.mvvm.R;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by VietHoa on 21/05/16.
 */
public class SnackBarMessage {
    private final String TAG = SnackBarMessage.class.getSimpleName();

    private Activity context;
    private int snackBarLayoutRes;
    private int snackBarTextViewRes;
    private LayoutInflater layoutInflater;
    private LinkedList<SnackBarItem> snackBarMessages;
    private SnackBarShower snackBarMessage;
    private Subscription timeInterval;

    public SnackBarMessage(Activity activity, @LayoutRes int layoutRes, @IdRes int textViewMessageRes, boolean isTopMessage) {
        context = activity;
        snackBarLayoutRes = layoutRes;
        snackBarTextViewRes = textViewMessageRes;

        snackBarMessages = new LinkedList<>();
        snackBarMessage = new SnackBarShower(context, isTopMessage);
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //----------------------------------------------------------------------------------------------
    // Options
    //----------------------------------------------------------------------------------------------

    public void showErrorMessage(@StringRes int message) {
        showNotification(context.getString(message), R.color.colorNotificationError);
    }

    public void showWarningMessage(@StringRes int message) {
        showNotification(context.getString(message), R.color.colorNotificationWaring);
    }

    public void showSuccessMessage(@StringRes int message) {
        showNotification(context.getString(message), R.color.colorNotificationSuccess);
    }

    public void showErrorMessage(String message) {
        showNotification(message, R.color.colorNotificationError);
    }

    public void showWarningMessage(String message) {
        showNotification(message, R.color.colorNotificationWaring);
    }

    public void showSuccessMessage(String message) {
        showNotification(message, R.color.colorNotificationSuccess);
    }

    //----------------------------------------------------------------------------------------------
    // Logistically
    //----------------------------------------------------------------------------------------------

    private void showNotification(String message, @ColorRes int color) {
        // Add new message to stack.
        View topSnackBarMessage = layoutInflater.inflate(snackBarLayoutRes, null);
        SnackBarItem topSnackBar = new SnackBarItem(color, topSnackBarMessage, message);
        snackBarMessages.add(topSnackBar);

        // Should run interval stage.
        if (timeInterval != null && !timeInterval.isUnsubscribed())
            return;

        //Log.d(TAG, "run interval");
        timeInterval = Observable.interval(100, TimeUnit.MILLISECONDS, Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::performShowNotification);
    }

    private void performShowNotification(long tick) {
        //Log.d(TAG, "tick: " + tick);
        if (tick % (SnackBarConfig.TIME_INTERVAL / 100) != 0) {
            return;
        }
        if (snackBarMessages == null || snackBarMessages.size() <= 0) {
            //Log.d(TAG, "stop interval");
            stopNotificationInterval();
            return;
        }

        //Log.d(TAG, "show snack bar");
        SnackBarItem snackBar = snackBarMessages.poll();
        snackBarMessage.showSnackBarMessage(snackBar.getView(), snackBar.getMessage(), snackBarTextViewRes, snackBar.getColor());
    }

    private void stopNotificationInterval() {
        if (timeInterval == null || timeInterval.isUnsubscribed())
            return;

        timeInterval.unsubscribe();
        timeInterval = null;
    }
}
