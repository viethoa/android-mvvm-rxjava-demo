package com.viethoa.mvvm.BaseApplications.snackbars;

import android.app.Activity;
import android.support.annotation.StringRes;

import com.viethoa.mvvm.R;

/**
 * Created by VietHoa on 22/05/16.
 */
public class BottomSnackBarMessage extends SnackBarMessage<BottomSnackBar> {
    private Activity activity;
    
    public BottomSnackBarMessage(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    //----------------------------------------------------------------------------------------------
    // Show message without closeable button.
    //----------------------------------------------------------------------------------------------

    public void showErrorMessage(@StringRes int message) {
        BottomSnackBar bottomSnackBar = new BottomSnackBar(activity);
        bottomSnackBar.init(message, R.color.colorNotificationError, false);
        showNotification(bottomSnackBar);
    }

    public void showWarningMessage(@StringRes int message) {
        BottomSnackBar bottomSnackBar = new BottomSnackBar(activity);
        bottomSnackBar.init(message, R.color.colorNotificationWaring, false);
        showNotification(bottomSnackBar);
    }

    public void showSuccessMessage(@StringRes int message) {
        BottomSnackBar bottomSnackBar = new BottomSnackBar(activity);
        bottomSnackBar.init(message, R.color.colorNotificationSuccess, false);
        showNotification(bottomSnackBar);
    }

    public void showErrorMessage(String message) {
        BottomSnackBar bottomSnackBar = new BottomSnackBar(activity);
        bottomSnackBar.init(message, R.color.colorNotificationError, false);
        showNotification(bottomSnackBar);
    }

    public void showWarningMessage(String message) {
        BottomSnackBar bottomSnackBar = new BottomSnackBar(activity);
        bottomSnackBar.init(message, R.color.colorNotificationWaring, false);
        showNotification(bottomSnackBar);
    }

    public void showSuccessMessage(String message) {
        BottomSnackBar bottomSnackBar = new BottomSnackBar(activity);
        bottomSnackBar.init(message, R.color.colorNotificationSuccess, false);
        showNotification(bottomSnackBar);
    }

    //----------------------------------------------------------------------------------------------
    // Show message within closeable button
    //----------------------------------------------------------------------------------------------

    public void showErrorMessageCloseable(@StringRes int message) {
        BottomSnackBar bottomSnackBar = new BottomSnackBar(activity);
        bottomSnackBar.init(message, R.color.colorNotificationError, true);
        showNotification(bottomSnackBar);
    }

    public void showWarningMessageCloseable(@StringRes int message) {
        BottomSnackBar bottomSnackBar = new BottomSnackBar(activity);
        bottomSnackBar.init(message, R.color.colorNotificationWaring, true);
        showNotification(bottomSnackBar);
    }

    public void showSuccessMessageCloseable(@StringRes int message) {
        BottomSnackBar bottomSnackBar = new BottomSnackBar(activity);
        bottomSnackBar.init(message, R.color.colorNotificationSuccess, true);
        showNotification(bottomSnackBar);
    }

    public void showErrorMessageCloseable(String message) {
        BottomSnackBar bottomSnackBar = new BottomSnackBar(activity);
        bottomSnackBar.init(message, R.color.colorNotificationError, true);
        showNotification(bottomSnackBar);
    }

    public void showWarningMessageCloseable(String message) {
        BottomSnackBar bottomSnackBar = new BottomSnackBar(activity);
        bottomSnackBar.init(message, R.color.colorNotificationWaring, true);
        showNotification(bottomSnackBar);
    }

    public void showSuccessMessageCloseable(String message) {
        BottomSnackBar bottomSnackBar = new BottomSnackBar(activity);
        bottomSnackBar.init(message, R.color.colorNotificationSuccess, true);
        showNotification(bottomSnackBar);
    }
}
