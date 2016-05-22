package com.viethoa.mvvm.BaseApplications;

import android.app.Application;

import com.viethoa.mvvm.BaseApplications.modules.AppComponent;
import com.viethoa.mvvm.BaseApplications.modules.DaggerAppComponent;
import com.viethoa.mvvm.Components.modules.InteractorModule.VocabularyInteractorModule;

/**
 * Created by VietHoa on 21/05/16.
 */
public class BaseApplication extends Application {
    private static Application appContext;
    private AppComponent appComponent;

    public void onCreate() {
        super.onCreate();
        appContext = this;

        initializeDagger();
    }

    public static Application getApplication() {
        return appContext;
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
