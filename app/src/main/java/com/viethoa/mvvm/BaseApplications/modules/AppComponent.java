package com.viethoa.mvvm.BaseApplications.modules;

import com.viethoa.mvvm.BaseApplications.cachings.CacheManager.CacheManager;
import com.viethoa.mvvm.BaseApplications.cachings.UserSessionManager.UserSessionManager;
import com.viethoa.mvvm.BaseApplications.modules.CacheModule.CacheModule;
import com.viethoa.mvvm.BaseApplications.modules.Network.NetworkModule;
import com.viethoa.mvvm.BaseApplications.modules.UserSessionModule.UserSessionModule;
import com.viethoa.mvvm.Components.modules.InteractorModule.VocabularyInteractorModule;
import com.viethoa.mvvm.Components.networks.MVVMApiService;
import com.viethoa.mvvm.Features.Interactor.VocabularyInteractor;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by VietHoa on 19/04/16.
 */
@Singleton
@Component(
        modules = {
                CacheModule.class,
                NetworkModule.class,
                UserSessionModule.class,
                VocabularyInteractorModule.class
        }
)
public interface AppComponent {

    CacheManager getCacheManager();

    MVVMApiService getMVVMApiService();

    UserSessionManager getUserSessionManger();

    VocabularyInteractor getVocabularyInteractor();
}
