package com.viethoa.mvvm.BaseApplications.snackbars;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import com.viethoa.mvvm.BaseApplications.animations.BaseEffects;
import com.viethoa.mvvm.BaseApplications.animations.SlideBottom;
import com.viethoa.mvvm.BaseApplications.animations.SlideTop;

/**
 * Created by VietHoa on 21/05/16.
 */
public class SnackBarShower implements BaseEffects.AnimationListener {
    private final String TAG = SnackBarShower.class.getSimpleName();

    private Activity context;
    private ViewGroup.LayoutParams snackBarParams;
    {
        snackBarParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public SnackBarShower(Activity context) {
        this.context = context;
    }

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
        // The delay time give user read notification message.
        // Subtract 100ms: for multiple messages => should close first before show new one.
        int delayTimeInterval = SnackBarConfig.TIME_INTERVAL - SnackBarConfig.ANIMATION - 100;

        new Handler().postDelayed(() -> {
            //Log.d(TAG, "dismiss snack bar");
            dismissSnackBarMessage(view);
        }, delayTimeInterval);
    }
}
