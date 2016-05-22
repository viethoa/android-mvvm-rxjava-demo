package com.viethoa.mvvm.BaseApplications.modules.UserSessionModule;

import com.viethoa.mvvm.BaseApplications.cachings.UserSessionManager.UserSessionManager;
import com.viethoa.mvvm.BaseApplications.cachings.UserSessionManager.UserSessionManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by VietHoa on 21/05/16.
 */
@Module
public class UserSessionModule {

    @Provides
    @Singleton
    UserSessionManager provideUserSessionManager(UserSessionManagerImpl userSessionManager) {
        return userSessionManager;
    }
}
