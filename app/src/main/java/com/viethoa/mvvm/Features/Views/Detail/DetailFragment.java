package com.viethoa.mvvm.Features.Views.Detail;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viethoa.mvvm.BaseApplications.views.BaseFragment;
import com.viethoa.mvvm.Components.modules.AppComponent;
import com.viethoa.mvvm.R;

import butterknife.OnClick;

/**
 * Created by VietHoa on 20/05/16.
 */
public class DetailFragment extends BaseFragment {

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
}
