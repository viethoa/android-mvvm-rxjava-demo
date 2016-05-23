package com.viethoa.mvvm.BaseApplications.snackbars;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by VietHoa on 22/05/16.
 */
public class SnackBar {
    private View snackbar;
    private View contentMessage;
    private TextView message;
    private View divider;
    private View closeButton;
    private boolean isAnimationFromTop;

    public SnackBar(Activity activity, @LayoutRes int layoutRes, @IdRes int contentMessageRes,
                    @IdRes int textViewRes, @IdRes int dividerRes, @IdRes int btnCloseRes, boolean isAnimationFromTop) {
        // Setup Animation.
        this.isAnimationFromTop = isAnimationFromTop;

        // Find snack bar.
        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        snackbar = layoutInflater.inflate(layoutRes, null);

        // Find content message.
        contentMessage = snackbar.findViewById(contentMessageRes);

        // Find textView message.
        message = (TextView) snackbar.findViewById(textViewRes);

        // Find divider
        divider = snackbar.findViewById(dividerRes);

        // Find button close.
        closeButton = snackbar.findViewById(btnCloseRes);
        closeButton.setOnClickListener(view -> {
            if (listener == null)
                return;

            listener.OnBtnCloseClicked(snackbar);
        });
    }

    //----------------------------------------------------------------------------------------------
    // Listener
    //----------------------------------------------------------------------------------------------

    private SnackBarListener listener;

    public interface SnackBarListener {
        void OnBtnCloseClicked(View snackBar);
    }

    public void setListener(SnackBarListener listener) {
        this.listener = listener;
    }

    //----------------------------------------------------------------------------------------------
    // Init
    //----------------------------------------------------------------------------------------------

    public void init(String messageStr, @ColorRes int colorRes, boolean isCloseable) {
        if (message != null) {
            message.setText(messageStr);
        }

        init(colorRes, isCloseable);
    }

    public void init(@StringRes int messageRes, @ColorRes int colorRes, boolean isCloseable) {
        if (message != null) {
            message.setText(messageRes);
        }

        init(colorRes, isCloseable);
    }

    private void init(@ColorRes int colorRes, boolean isCloseable) {
        if (contentMessage != null) {
            contentMessage.setBackgroundResource(colorRes);
        }
        if (isCloseable) {
            if (divider != null)
                divider.setVisibility(View.VISIBLE);
            if (closeButton != null)
                closeButton.setVisibility(View.VISIBLE);
        } else {
            if (divider != null)
                divider.setVisibility(View.GONE);
            if (closeButton != null)
                closeButton.setVisibility(View.GONE);
        }
    }

    //----------------------------------------------------------------------------------------------
    // Events
    //----------------------------------------------------------------------------------------------

    public ViewGroup getParent() {
        if (snackbar == null || snackbar.getParent() == null)
            return null;

        return (ViewGroup) snackbar.getParent();
    }

    public View getView() {
        return snackbar;
    }

    public boolean isAnimationFromTop() {
        return isAnimationFromTop;
    }

    public boolean isCloseable() {
        return closeButton != null && closeButton.getVisibility() == View.VISIBLE;
    }
}
