package com.viethoa.mvvm.BaseApplications.animations;

import android.animation.ObjectAnimator;
import android.view.View;

public class Fall extends BaseEffects{

    @Override
    protected void setupAnimation(View view) {
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "scaleX", 2, 1.5f, 1).setDuration(mDuration),
                ObjectAnimator.ofFloat(view,"scaleY",2,1.5f,1).setDuration(mDuration),
                ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(mDuration*3/2)

        );
    }
}
