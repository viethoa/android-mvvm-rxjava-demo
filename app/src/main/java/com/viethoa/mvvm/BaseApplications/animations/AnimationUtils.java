package com.viethoa.mvvm.BaseApplications.animations;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.TextView;

import com.viethoa.mvvm.R;

/**
 * Created by VietHoa on 20/05/16.
 */
public class AnimationUtils {

    public static void shakeAnimationEditText(final EditText editText) {
        // fade-in color
        Integer errorColor = Color.RED;
        Integer currentColor = R.color.colorHint;
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), errorColor, currentColor);
        colorAnimation.setDuration(1000);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                editText.setHintTextColor((Integer) animator.getAnimatedValue());
                editText.setTextColor((Integer) animator.getAnimatedValue());
            }
        });

        // start animation
        Shake shakeAnimation = new Shake();
        shakeAnimation.setDuration(1000);
        shakeAnimation.start(editText);
        colorAnimation.start();
    }

    public static void shakeAnimationTextView(final TextView textView) {
        // fade-in color
        Integer errorColor = Color.RED;
        Integer currentColor = R.color.colorHint;
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), errorColor, currentColor);
        colorAnimation.setDuration(500);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                textView.setTextColor((Integer) animator.getAnimatedValue());
            }
        });

        // start animation
        Shake shakeAnimation = new Shake();
        shakeAnimation.setDuration(500);
        shakeAnimation.start(textView);
        colorAnimation.start();
    }
}
