package com.viethoa.mvvm.Components.modules.InteractorModule;

import com.viethoa.mvvm.Features.Interactor.VocabularyInteractor;
import com.viethoa.mvvm.Features.Interactor.VocabularyInteractorImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by VietHoa on 14/05/16.
 */
@Module
public class VocabularyInteractorModule {

    @Provides
    @Singleton
    VocabularyInteractor provideVocabularyInteractor(VocabularyInteractorImpl interactor) {
        return interactor;
    }
}
