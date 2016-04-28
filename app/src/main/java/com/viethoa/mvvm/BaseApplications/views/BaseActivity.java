package com.viethoa.mvvm.BaseApplications.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.viethoa.mvvm.Components.modules.ApplicationComponent;
import com.viethoa.mvvm.Components.modules.ApplicationGraph;

import butterknife.ButterKnife;

/**
 * Created by VietHoa on 27/04/16.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private ApplicationGraph mObjectGraph;

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

    protected abstract void injectModule(ApplicationGraph objectGraph);

    private void initializeDagger() {
        if (mObjectGraph == null) {
            mObjectGraph = ApplicationComponent.Initializer.init(this);
        }
        injectModule(mObjectGraph);
    }


}