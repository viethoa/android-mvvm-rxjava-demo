package com.viethoa.mvvm.Features;

import android.app.Application;

import com.viethoa.mvvm.Components.modules.ApplicationComponent;
import com.viethoa.mvvm.Components.modules.ApplicationGraph;

/**
 * Created by VietHoa on 27/04/16.
 */
public class MVVMApplication extends Application {
    private static Application application;
    private ApplicationGraph mObjectGraph;

    public static MVVMApplication newInstance() {
        return (MVVMApplication) application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        initializeDagger();
    }

    //----------------------------------------------------------------------------------------------
    // Setup dagger
    //----------------------------------------------------------------------------------------------

    private void initializeDagger() {
        mObjectGraph = ApplicationComponent.Initializer.init(this);
    }

    public ApplicationGraph getApplicationGraph() {
        return mObjectGraph;
    }
}
