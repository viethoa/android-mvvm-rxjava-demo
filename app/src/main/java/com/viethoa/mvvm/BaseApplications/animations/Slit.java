package com.viethoa.mvvm.BaseApplications.animations;

import android.animation.ObjectAnimator;
import android.view.View;

public class Slit extends BaseEffects {

    @Override
    protected void setupAnimation(View view) {
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "rotationY", 90, 88, 88, 45, 0).setDuration(mDuration),
                ObjectAnimator.ofFloat(view, "alpha", 0, 0.4f, 0.8f, 1).setDuration(mDuration * 3 / 2),
                ObjectAnimator.ofFloat(view, "scaleX", 0, 0.5f, 0.9f, 0.9f, 1).setDuration(mDuration),
                ObjectAnimator.ofFloat(view, "scaleY", 0, 0.5f, 0.9f, 0.9f, 1).setDuration(mDuration)
        );
    }
}
