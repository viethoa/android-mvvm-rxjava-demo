package com.viethoa.mvvm.BaseApplications.animations;

import android.animation.ObjectAnimator;
import android.view.View;

public class SlideTop extends BaseEffects{

    @Override
    protected void setupAnimation(View view) {
        int height = view.getMeasuredHeight();
        if (height == 0)
            height = 300;

        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "translationY", -height, 0).setDuration(mDuration),
                ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(mDuration*3/2)
        );
    }
}
