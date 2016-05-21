package com.viethoa.mvvm.BaseApplications.snackbars;

import android.app.Activity;

import com.viethoa.mvvm.R;

/**
 * Created by VietHoa on 21/05/16.
 */
public class BottomSnackBarMessage extends SnackBarMessage {

    public BottomSnackBarMessage(Activity activity) {
        super(activity, R.layout.snackbar_bottom_layout, R.id.tv_snack_bar_message, false);
    }
}
