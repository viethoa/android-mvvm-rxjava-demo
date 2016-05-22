package com.viethoa.mvvm.Features.Views.Detail;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viethoa.mvvm.BaseApplications.modules.AppComponent;
import com.viethoa.mvvm.BaseApplications.views.RxBaseFragment;
import com.viethoa.mvvm.R;

import java.util.Random;

import butterknife.OnClick;

/**
 * Created by VietHoa on 20/05/16.
 */
public class DetailFragment extends RxBaseFragment {

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragmet_detail_layout, container, false);
    }

    @Override
    protected void injectModule(AppComponent appComponent) {

    }

    //----------------------------------------------------------------------------------------------
    // Loading dialog
    //----------------------------------------------------------------------------------------------

    @OnClick(R.id.btn_show_loading_dialog)
    protected void BtnShowLoadingDialogClicked() {
        showLoadingDialog();
        // auto dismiss after 5s.
        new Handler().postDelayed(this::dismissLoadingDialog, 5000);
    }

    @OnClick(R.id.btn_show_top_message)
    protected void BtnShowTopNotificationClicked() {
        showTopSuccessMessage("message " + new Random().nextInt());
    }

    @OnClick(R.id.btn_show_bottom_message)
    protected void BtnShowBottomNotificationClicked() {
        showBottomSuccessMessage("message " + new Random().nextInt());
    }
}
