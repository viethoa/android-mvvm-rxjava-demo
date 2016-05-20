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
import com.viethoa.mvvm.Components.modules.AppComponent;
import com.viethoa.mvvm.Features.MVVMApplication;
import com.viethoa.mvvm.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by VietHoa on 27/04/16.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private LoadingDialog loadingDialog;

    @Nullable
    @Bind(R.id.toolbar)
    protected Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.loadingDialog = LoadingDialog.newInstance(this);
        initializeDagger();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        if (toolBar == null)
            return;

        setSupportActionBar(toolBar);
    }

    @Override
    protected void onPause() {
        super.onPause();
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
    // Start Activity
    //----------------------------------------------------------------------------------------------

    protected void startActivity(Class clazz) {
        startActivity(clazz, null);
    }

    protected void startActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void startNewSingleTaskActivity(Class clazz) {
        startNewSingleTaskActivity(clazz, null);
    }

    protected void startNewSingleTaskActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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
