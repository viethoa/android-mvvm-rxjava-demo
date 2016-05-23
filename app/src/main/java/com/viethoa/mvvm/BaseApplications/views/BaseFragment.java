package com.viethoa.mvvm.BaseApplications.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viethoa.mvvm.BaseApplications.dialogs.LoadingDialog;
import com.viethoa.mvvm.BaseApplications.modules.AppComponent;
import com.viethoa.mvvm.BaseApplications.snackbars.BottomSnackBarMessage;
import com.viethoa.mvvm.BaseApplications.snackbars.TopSnackBarMessage;
import com.viethoa.mvvm.Features.MVVMApplication;

import butterknife.ButterKnife;

/**
 * Created by VietHoa on 19/05/16.
 */
public abstract class BaseFragment extends Fragment {
    private LoadingDialog loadingDialog;
    private TopSnackBarMessage topSnackBarMessage;
    private BottomSnackBarMessage bottomSnackBarMessage;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MVVMApplication application = MVVMApplication.newInstance();
        injectModule(application.getComponent());
    }

    protected abstract void injectModule(AppComponent appComponent);

    //----------------------------------------------------------------------------------------------
    // Configuration
    //----------------------------------------------------------------------------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = setContentView(inflater, container);
        ButterKnife.bind(this, contentView);

        loadingDialog = new LoadingDialog(getContext());
        topSnackBarMessage = new TopSnackBarMessage(getActivity());
        bottomSnackBarMessage = new BottomSnackBarMessage(getActivity());

        return contentView;
    }

    protected abstract View setContentView(LayoutInflater inflater, ViewGroup container);

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

    //----------------------------------------------------------------------------------------------
    // SnackBar Notification Messages
    //----------------------------------------------------------------------------------------------

    protected void showTopErrorMessage(int messageRes) {
        topSnackBarMessage.showErrorMessage(messageRes);
    }

    protected void showTopWarningMessage(int messageRes) {
        topSnackBarMessage.showWarningMessage(messageRes);
    }

    protected void showTopSuccessMessage(int messageRes) {
        topSnackBarMessage.showSuccessMessage(messageRes);
    }

    protected void showTopErrorMessage(String messageRes) {
        topSnackBarMessage.showErrorMessage(messageRes);
    }

    protected void showTopWarningMessage(String messageRes) {
        topSnackBarMessage.showWarningMessage(messageRes);
    }

    protected void showTopSuccessMessage(String messageRes) {
        topSnackBarMessage.showSuccessMessage(messageRes);
    }

    protected void showBottomErrorMessage(int messageRes) {
        bottomSnackBarMessage.showErrorMessage(messageRes);
    }

    protected void showBottomWarningMessage(int messageRes) {
        bottomSnackBarMessage.showWarningMessage(messageRes);
    }

    protected void showBottomSuccessMessage(int messageRes) {
        bottomSnackBarMessage.showSuccessMessage(messageRes);
    }

    protected void showBottomErrorMessage(String messageRes) {
        bottomSnackBarMessage.showErrorMessage(messageRes);
    }

    protected void showBottomWarningMessage(String messageRes) {
        bottomSnackBarMessage.showWarningMessage(messageRes);
    }

    protected void showBottomSuccessMessage(String messageRes) {
        bottomSnackBarMessage.showSuccessMessage(messageRes);
    }

}
