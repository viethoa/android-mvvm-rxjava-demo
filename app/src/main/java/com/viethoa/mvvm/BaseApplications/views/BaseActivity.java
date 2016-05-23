package com.viethoa.mvvm.BaseApplications.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.viethoa.mvvm.BaseApplications.dialogs.LoadingDialog;
import com.viethoa.mvvm.BaseApplications.modules.AppComponent;
import com.viethoa.mvvm.BaseApplications.snackbars.BottomSnackBarMessage;
import com.viethoa.mvvm.BaseApplications.snackbars.TopSnackBarMessage;
import com.viethoa.mvvm.Features.MVVMApplication;
import com.viethoa.mvvm.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by VietHoa on 27/04/16.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private LoadingDialog loadingDialog;
    private TopSnackBarMessage topSnackBarMessage;
    private BottomSnackBarMessage bottomSnackBarMessage;

    @Nullable
    @Bind(R.id.toolbar)
    protected Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeDagger();
        loadingDialog = LoadingDialog.newInstance(this);
        topSnackBarMessage = new TopSnackBarMessage(this);
        bottomSnackBarMessage = new BottomSnackBarMessage(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        if (toolBar == null)
            return;

        setSupportActionBar(toolBar);
    }

    //----------------------------------------------------------------------------------------------
    // Setup dagger
    //----------------------------------------------------------------------------------------------

    protected abstract void injectModule(AppComponent appComponent);

    private void initializeDagger() {
        MVVMApplication application = MVVMApplication.newInstance();
        injectModule(application.getComponent());
    }

    //----------------------------------------------------------------------------------------------
    // Toolbar
    //----------------------------------------------------------------------------------------------

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void showToolbarBackIcon() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null)
            return;

        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    protected void showToolbarBackIcon(@DrawableRes int backIcon) {
        if (toolBar == null)
            return;

        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null)
            return;

        actionBar.setDisplayHomeAsUpEnabled(true);
        toolBar.setNavigationIcon(backIcon);
    }

    protected void showToolbarLogo(@DrawableRes int logoIcon) {
        if (toolBar == null)
            return;

        toolBar.setLogo(logoIcon);
    }

    protected void showToolbarMenuIcon(@DrawableRes int menuIcon) {
        if (toolBar == null)
            return;

        toolBar.setNavigationIcon(menuIcon);
    }

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





    protected void showTopErrorMessageCloseable(int messageRes) {
        topSnackBarMessage.showErrorMessageCloseable(messageRes);
    }

    protected void showTopWarningMessageCloseable(int messageRes) {
        topSnackBarMessage.showWarningMessageCloseable(messageRes);
    }

    protected void showTopSuccessMessageCloseable(int messageRes) {
        topSnackBarMessage.showSuccessMessageCloseable(messageRes);
    }

    protected void showTopErrorMessageCloseable(String messageRes) {
        topSnackBarMessage.showErrorMessageCloseable(messageRes);
    }

    protected void showTopWarningMessageCloseable(String messageRes) {
        topSnackBarMessage.showWarningMessageCloseable(messageRes);
    }

    protected void showTopSuccessMessageCloseable(String messageRes) {
        topSnackBarMessage.showSuccessMessageCloseable(messageRes);
    }

    protected void showBottomErrorMessageCloseable(int messageRes) {
        bottomSnackBarMessage.showErrorMessage(messageRes);
    }

    protected void showBottomWarningMessageCloseable(int messageRes) {
        bottomSnackBarMessage.showWarningMessage(messageRes);
    }

    protected void showBottomSuccessMessageCloseable(int messageRes) {
        bottomSnackBarMessage.showSuccessMessageCloseable(messageRes);
    }

    protected void showBottomErrorMessageCloseable(String messageRes) {
        bottomSnackBarMessage.showErrorMessageCloseable(messageRes);
    }

    protected void showBottomWarningMessageCloseable(String messageRes) {
        bottomSnackBarMessage.showWarningMessageCloseable(messageRes);
    }

    protected void showBottomSuccessMessageCloseable(String messageRes) {
        bottomSnackBarMessage.showSuccessMessageCloseable(messageRes);
    }

    //----------------------------------------------------------------------------------------------
    // Start Activity
    //----------------------------------------------------------------------------------------------

    protected void slideOutFromLeft(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.acitivity_in_from_left_to_right, R.anim.activity_out_from_right);
    }

    protected void slideInFromRight(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.acitivity_in_from_right_to_left, R.anim.acitivity_out_from_left);
    }

    protected void slideOutFromLeftZoom(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.activity_zoom_in, R.anim.activity_out_from_right);
    }

    protected void slideInFromRightZoom(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.acitivity_in_from_right_to_left, R.anim.activity_zoom_out);
    }

    protected void slideInFromLeftZoom(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.acitivity_in_from_left_to_right, R.anim.activity_zoom_out);
    }

    //----------------------------------------------------------------------------------------------
    // Helpers function
    //----------------------------------------------------------------------------------------------

    protected void replaceFragment(final Fragment fg, final int containerResId, final boolean animated) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        if (animated) {
            tx.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        }

        tx.replace(containerResId, fg);
        //tx.addToBackStack(LOG_TAG);
        tx.commit();
        fm.executePendingTransactions();
    }

    protected void openUrlInBrowser(String url) {
        if (url == null || url.length() <= 0)
            return;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        try {
            intent.setData(Uri.parse(url));
        } catch (Exception e) {
            return;
        }
        startActivity(intent);
    }

}
