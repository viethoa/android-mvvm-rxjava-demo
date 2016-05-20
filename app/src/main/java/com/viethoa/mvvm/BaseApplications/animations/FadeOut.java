package com.viethoa.mvvm.BaseApplications.animations;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by VietHoa on 23/04/16.
 */
public class FadeOut extends BaseEffects {

    @Override
    protected void setupAnimation(View view) {
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "alpha", 1, 0).setDuration(mDuration)
        );
    }
}
