package com.viethoa.mvvm.Components.modules;

import com.viethoa.mvvm.Components.modules.InteractorModule.VocabularyInteractorModule;
import com.viethoa.mvvm.Features.Interactor.VocabularyInteractor;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by VietHoa on 19/04/16.
 */
@Singleton
@Component(
        modules = {
                VocabularyInteractorModule.class
        }
)
public interface AppComponent {

    VocabularyInteractor getVocabularyInteractor();
}
