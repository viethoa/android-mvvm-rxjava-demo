package com.viethoa.mvvm.Components.modules;

import android.content.Context;

import com.viethoa.mvvm.Components.modules.DetailModule.DetailModule;
import com.viethoa.mvvm.Components.modules.HomeModule.HomeModule;
import com.viethoa.mvvm.Components.modules.InteractorModule.VocabularyInteractorModule;
import com.viethoa.mvvm.Components.modules.ItemMainModule.ItemMainModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by VietHoa on 19/04/16.
 */
@Singleton
@Component(
        modules = {
                HomeModule.class,
                DetailModule.class,
                ItemMainModule.class,
                VocabularyInteractorModule.class
        }
)
public interface ApplicationComponent extends ApplicationGraph {

    class Initializer {

        public static ApplicationComponent init(Context context)
        {
            return DaggerApplicationComponent.builder()
                    .homeModule(new HomeModule())
                    .detailModule(new DetailModule())
                    .itemMainModule(new ItemMainModule(context))
                    .vocabularyInteractorModule(new VocabularyInteractorModule())
                    .build();
        }
    }
}
