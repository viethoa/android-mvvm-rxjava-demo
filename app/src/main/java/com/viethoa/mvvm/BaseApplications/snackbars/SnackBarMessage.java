package com.viethoa.mvvm.BaseApplications.snackbars;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import com.viethoa.mvvm.BaseApplications.animations.BaseEffects;
import com.viethoa.mvvm.BaseApplications.animations.SlideBottom;
import com.viethoa.mvvm.BaseApplications.animations.SlideTop;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by VietHoa on 21/05/16.
 */
public class SnackBarMessage<T extends SnackBar> implements BaseEffects.AnimationListener, SnackBar.SnackBarListener {
    private final String TAG = SnackBarMessage.class.getSimpleName();

    private Activity context;
    private Subscription timeInterval;
    private LinkedList<T> snackBarMessages;
    private ViewGroup.LayoutParams snackBarParams;
    private SnackBar snackBarShowing;

    public SnackBarMessage(Activity activity) {
        context = activity;
        snackBarMessages = new LinkedList<>();
        snackBarParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
    }

    protected void showNotification(T snackBar) {
        snackBar.setListener(this);
        snackBarMessages.add(snackBar);
        startTimeInterval();
    }

    //----------------------------------------------------------------------------------------------
    // Time interval
    //----------------------------------------------------------------------------------------------

    private void startTimeInterval() {
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
            stopNotificationInterval();
            return;
        }

        snackBarShowing = snackBarMessages.poll();
        if (snackBarShowing.isCloseable()) {
            stopNotificationInterval();
        }

        //Log.d(TAG, "show snack bar");
        showSnackBarMessage(snackBarShowing);
    }

    private void stopNotificationInterval() {
        if (timeInterval == null || timeInterval.isUnsubscribed())
            return;

        //Log.d(TAG, "stop interval");
        timeInterval.unsubscribe();
        timeInterval = null;
    }

    //----------------------------------------------------------------------------------------------
    // Shower
    //----------------------------------------------------------------------------------------------

    public void showSnackBarMessage(SnackBar snackBar) {
        if (snackBar == null || context == null || context.isFinishing())
            return;

        // This Message is showing, should remove first.
        if (snackBar.getParent() != null)
            return;

        // Append snack bar view to window.
        context.addContentView(snackBar.getView(), snackBarParams);

        if (snackBar.isAnimationFromTop()) {        // Top snack bar animation
            SlideTop slideTop = new SlideTop();
            slideTop.setDuration(SnackBarConfig.ANIMATION);
            slideTop.setListener(this);
            slideTop.start(snackBar.getView());
        } else {                                    // Bottom snack bar animation
            SlideBottom slideBottom = new SlideBottom();
            slideBottom.setDuration(SnackBarConfig.ANIMATION);
            slideBottom.setListener(this);
            slideBottom.start(snackBar.getView());
        }
    }

    private void dismissSnackBarMessage(View viewGroup) {
        if (viewGroup == null)
            return;

        ViewGroup parent = (ViewGroup) viewGroup.getParent();
        if (parent == null)
            return;

        //Log.d(TAG, "remove snack from window");
        parent.removeView(viewGroup);
    }

    @Override
    public void onAnimationEnd(View view) {
        if (view == null)
            return;
        if (snackBarShowing != null && snackBarShowing.isCloseable())
            return;

        // The delay time give user read notification message.
        // Subtract 100ms: for multiple messages => should close first before show new one.
        int delayTimeInterval = SnackBarConfig.TIME_INTERVAL - SnackBarConfig.ANIMATION - 100;
        new Handler().postDelayed(() -> {
            dismissSnackBarMessage(view);
        }, delayTimeInterval);
    }

    @Override
    public void OnBtnCloseClicked(View snackBar) {
        dismissSnackBarMessage(snackBar);

        // should show next?
        if (snackBarMessages == null || snackBarMessages.size() <= 0)
            return;

        // This SnackBar have Closeable button, should show next item when closed.
        T nextSnackBar = snackBarMessages.peek();
        if (nextSnackBar.isCloseable()) {
            startTimeInterval();
        }
    }
}
