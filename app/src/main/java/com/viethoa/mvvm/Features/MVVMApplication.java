package com.viethoa.mvvm.Features;

import android.app.Application;

import com.viethoa.mvvm.Components.modules.AppComponent;
import com.viethoa.mvvm.Components.modules.DaggerAppComponent;
import com.viethoa.mvvm.Components.modules.InteractorModule.VocabularyInteractorModule;

/**
 * Created by VietHoa on 27/04/16.
 */
public class MVVMApplication extends Application {
    private static Application application;
    private AppComponent appComponent;

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
        appComponent = DaggerAppComponent.builder()
                .vocabularyInteractorModule(new VocabularyInteractorModule())
                .build();
    }

    public AppComponent getComponent() {
        return appComponent;
    }
}
