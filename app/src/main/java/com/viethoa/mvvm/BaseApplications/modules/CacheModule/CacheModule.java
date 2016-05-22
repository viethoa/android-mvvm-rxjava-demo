package com.viethoa.mvvm.BaseApplications.modules.CacheModule;

import com.viethoa.mvvm.BaseApplications.cachings.CacheManager.CacheManager;
import com.viethoa.mvvm.BaseApplications.cachings.CacheManager.CacheManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by VietHoa on 21/05/16.
 */
@Module
public class CacheModule {

    @Provides
    @Singleton
    CacheManager provideCacheManager(CacheManagerImpl cacheManager) {
        return cacheManager;
    }
}
