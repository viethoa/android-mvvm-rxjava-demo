package com.viethoa.mvvm.BaseApplications.animations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.view.View;

public abstract class BaseEffects {

    private static final int DURATION = 300;
    protected long mDuration = DURATION;
    protected AnimationListener listener;

    public interface AnimationListener {
        void onAnimationEnd(View view);
    }

    private AnimatorSet mAnimatorSet;
    {
        mAnimatorSet = new AnimatorSet();
    }

    protected abstract void setupAnimation(View view);

    public void start(View view) {
        mAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (listener == null)
                    return;
                listener.onAnimationEnd(view);
            }
        });

        setupAnimation(view);
        mAnimatorSet.start();
    }

    public void reset(View view) {
        view.setPivotX(view.getMeasuredWidth() / 2.0f);
        view.setPivotY(view.getMeasuredHeight() / 2.0f);
    }


    public AnimatorSet getAnimatorSet() {
        return mAnimatorSet;
    }

    public void setDuration(long duration) {
        this.mDuration = duration;
    }

    public void setListener(AnimationListener listener) {
        this.listener = listener;
    }

}
