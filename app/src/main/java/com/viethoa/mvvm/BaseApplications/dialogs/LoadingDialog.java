package com.viethoa.mvvm.BaseApplications.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.viethoa.mvvm.R;

/**
 * Created by VietHoa on 19/05/16.
 */
public class LoadingDialog extends Dialog {

    public static LoadingDialog newInstance(Context context) {
        return new LoadingDialog(context);
    }

    public LoadingDialog(Context context) {
        super(context, R.style.FullScreen_DialogStyle);

        // Force Content loading to Bottom.
        getWindow ().setLayout (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        getWindow ().setGravity (Gravity.BOTTOM);

        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.dialog_loading_layout);
    }
}
