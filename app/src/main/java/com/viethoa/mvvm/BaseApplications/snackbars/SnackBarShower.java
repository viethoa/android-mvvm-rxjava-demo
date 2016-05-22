package com.viethoa.mvvm.BaseApplications.snackbars;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viethoa.mvvm.BaseApplications.animations.BaseEffects;
import com.viethoa.mvvm.BaseApplications.animations.SlideBottom;
import com.viethoa.mvvm.BaseApplications.animations.SlideTop;

/**
 * Created by VietHoa on 21/05/16.
 */
public class SnackBarShower implements BaseEffects.AnimationListener {
    private final String TAG = SnackBarShower.class.getSimpleName();

    private Activity context;
    private boolean isTopMessage;
    private ViewGroup.LayoutParams snackBarParams;
    {
        snackBarParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public SnackBarShower(Activity context, boolean isTopMessage) {
        this.context = context;
        this.isTopMessage = isTopMessage;
    }

    public void showSnackBarMessage(View snackBarView, String message, @IdRes int messageRes, @ColorRes int color) {
        if (context == null || context.isFinishing())
            return;
        if (TextUtils.isEmpty(message) || snackBarView == null)
            return;

        // This Message is showing, should remove first.
        if (snackBarView.getParent() != null)
            return;

        // Set message.
        TextView textView = (TextView) snackBarView.findViewById(messageRes);
        textView.setBackgroundResource(color);
        textView.setText(message);

        // Append snack bar view to window.
        context.addContentView(snackBarView, snackBarParams);

        if (isTopMessage) {    // Top snack bar animation
            SlideTop slideTop = new SlideTop();
            slideTop.setDuration(SnackBarConfig.ANIMATION);
            slideTop.setListener(this);
            slideTop.start(snackBarView);
        } else {        // Bottom snack bar animation
            SlideBottom slideBottom = new SlideBottom();
            slideBottom.setDuration(SnackBarConfig.ANIMATION);
            slideBottom.setListener(this);
            slideBottom.start(snackBarView);
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
        // The delay time give user read notification message.
        // Subtract 100ms: for multiple messages => should close first before show new one.
        int delayTimeInterval = SnackBarConfig.TIME_INTERVAL - SnackBarConfig.ANIMATION - 100;

        new Handler().postDelayed(() -> {
            //Log.d(TAG, "dismiss snack bar");
            dismissSnackBarMessage(view);
        }, delayTimeInterval);
    }
}
