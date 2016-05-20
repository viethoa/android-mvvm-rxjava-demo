package com.viethoa.mvvm.BaseApplications.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viethoa.mvvm.BaseApplications.dialogs.LoadingDialog;
import com.viethoa.mvvm.Components.modules.AppComponent;
import com.viethoa.mvvm.Features.MVVMApplication;

import butterknife.ButterKnife;

/**
 * Created by VietHoa on 19/05/16.
 */
public abstract class BaseFragment extends Fragment {
    private LoadingDialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = setContentView(inflater, container);

        // ButterKnife injection.
        ButterKnife.bind(this, contentView);
        // Loading dialog initialize;
        loadingDialog = new LoadingDialog(getContext());

        return contentView;
    }

    protected abstract View setContentView(LayoutInflater inflater, ViewGroup container);

    //----------------------------------------------------------------------------------------------
    // dagger 2 injection
    //----------------------------------------------------------------------------------------------

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MVVMApplication application = MVVMApplication.newInstance();
        injectModule(application.getComponent());
    }

    protected abstract void injectModule(AppComponent appComponent);

    //----------------------------------------------------------------------------------------------
    // Loading Dialog
    //----------------------------------------------------------------------------------------------

    protected void showLoadingDialog() {
        if (loadingDialog == null)
            return;

        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }

        loadingDialog.show();
    }

    protected void dismissLoadingDialog() {
        if (loadingDialog == null)
            return;
        if (!loadingDialog.isShowing())
            return;

        loadingDialog.dismiss();
    }

}
