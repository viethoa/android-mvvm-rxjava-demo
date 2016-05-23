package com.viethoa.mvvm.BaseApplications.snackbars;

import android.app.Activity;
import android.support.annotation.StringRes;

import com.viethoa.mvvm.R;

/**
 * Created by VietHoa on 22/05/16.
 */
public class TopSnackBarMessage extends SnackBarMessage<TopSnackBar> {
    private Activity activity;

    public TopSnackBarMessage(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    //----------------------------------------------------------------------------------------------
    // Show message without closeable button.
    //----------------------------------------------------------------------------------------------

    public void showErrorMessage(@StringRes int message) {
        TopSnackBar topSnackBar = new TopSnackBar(activity);
        topSnackBar.init(message, R.color.colorNotificationError, false);
        showNotification(topSnackBar);
    }

    public void showWarningMessage(@StringRes int message) {
        TopSnackBar topSnackBar = new TopSnackBar(activity);
        topSnackBar.init(message, R.color.colorNotificationWaring, false);
        showNotification(topSnackBar);
    }

    public void showSuccessMessage(@StringRes int message) {
        TopSnackBar topSnackBar = new TopSnackBar(activity);
        topSnackBar.init(message, R.color.colorNotificationSuccess, false);
        showNotification(topSnackBar);
    }

    public void showErrorMessage(String message) {
        TopSnackBar topSnackBar = new TopSnackBar(activity);
        topSnackBar.init(message, R.color.colorNotificationError, false);
        showNotification(topSnackBar);
    }

    public void showWarningMessage(String message) {
        TopSnackBar topSnackBar = new TopSnackBar(activity);
        topSnackBar.init(message, R.color.colorNotificationWaring, false);
        showNotification(topSnackBar);
    }

    public void showSuccessMessage(String message) {
        TopSnackBar topSnackBar = new TopSnackBar(activity);
        topSnackBar.init(message, R.color.colorNotificationSuccess, false);
        showNotification(topSnackBar);
    }

    //----------------------------------------------------------------------------------------------
    // Show message within closeable button
    //----------------------------------------------------------------------------------------------

    public void showErrorMessageCloseable(@StringRes int message) {
        TopSnackBar topSnackBar = new TopSnackBar(activity);
        topSnackBar.init(message, R.color.colorNotificationError, true);
        showNotification(topSnackBar);
    }

    public void showWarningMessageCloseable(@StringRes int message) {
        TopSnackBar topSnackBar = new TopSnackBar(activity);
        topSnackBar.init(message, R.color.colorNotificationWaring, true);
        showNotification(topSnackBar);
    }

    public void showSuccessMessageCloseable(@StringRes int message) {
        TopSnackBar topSnackBar = new TopSnackBar(activity);
        topSnackBar.init(message, R.color.colorNotificationSuccess, true);
        showNotification(topSnackBar);
    }

    public void showErrorMessageCloseable(String message) {
        TopSnackBar topSnackBar = new TopSnackBar(activity);
        topSnackBar.init(message, R.color.colorNotificationError, true);
        showNotification(topSnackBar);
    }

    public void showWarningMessageCloseable(String message) {
        TopSnackBar topSnackBar = new TopSnackBar(activity);
        topSnackBar.init(message, R.color.colorNotificationWaring, true);
        showNotification(topSnackBar);
    }

    public void showSuccessMessageCloseable(String message) {
        TopSnackBar topSnackBar = new TopSnackBar(activity);
        topSnackBar.init(message, R.color.colorNotificationSuccess, true);
        showNotification(topSnackBar);
    }
}
