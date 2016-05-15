package com.viethoa.mvvm.BaseApplications.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Toast;

import com.viethoa.mvvm.Components.modules.AppComponent;
import com.viethoa.mvvm.Features.MVVMApplication;

import butterknife.ButterKnife;

/**
 * Created by VietHoa on 27/04/16.
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDagger();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
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

    //----------------------------------------------------------------------------------------------
    // Setup dagger
    //----------------------------------------------------------------------------------------------

    protected abstract void injectModule(AppComponent appComponent);

    private void initializeDagger() {
        MVVMApplication application = MVVMApplication.newInstance();
        injectModule(application.getComponent());
    }

    //----------------------------------------------------------------------------------------------
    // Base Event
    //----------------------------------------------------------------------------------------------

    @Override
    public void showErrorMessage(String message) {
        if (TextUtils.isEmpty(message))
            return;

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
