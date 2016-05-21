package com.viethoa.mvvm.BaseApplications.snackbars;

import android.support.annotation.ColorRes;
import android.view.View;

/**
 * Created by VietHoa on 21/05/16.
 */
public class SnackBarItem {
    @ColorRes
    private int color;
    private View view;
    private String message;

    public SnackBarItem(int color, View view, String message) {
        this.color = color;
        this.view = view;
        this.message = message;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
