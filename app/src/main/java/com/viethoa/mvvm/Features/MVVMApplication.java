package com.viethoa.mvvm.Features;

import com.viethoa.mvvm.BaseApplications.BaseApplication;

/**
 * Created by VietHoa on 27/04/16.
 */
public class MVVMApplication extends BaseApplication {

    public static MVVMApplication newInstance() {
        return (MVVMApplication) getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
