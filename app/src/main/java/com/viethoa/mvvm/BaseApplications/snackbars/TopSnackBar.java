package com.viethoa.mvvm.BaseApplications.snackbars;

import android.app.Activity;

import com.viethoa.mvvm.R;

/**
 * Created by VietHoa on 21/05/16.
 */
public class TopSnackBar extends SnackBar {

    public TopSnackBar(Activity activity) {
        super(activity, R.layout.snackbar_top_layout, R.id.snack_bar_content_message,
                R.id.snack_bar_tv_message, R.id.snack_bar_divider, R.id.snack_bar_btn_close, true);
    }
}
