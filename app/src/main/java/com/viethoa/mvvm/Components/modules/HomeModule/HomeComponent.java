package com.viethoa.mvvm.Components.modules.HomeModule;

import com.viethoa.mvvm.Components.annotations.ActivityScope;
import com.viethoa.mvvm.Components.modules.AppComponent;
import com.viethoa.mvvm.Features.Views.Home.MainActivity;

import dagger.Component;

/**
 * Created by VietHoa on 14/05/16.
 */
@ActivityScope
@Component(
        dependencies = {AppComponent.class},
        modules = HomeModule.class
)
public interface HomeComponent {

    void inject(MainActivity mainActivity);

    MainViewModel getMainViewModel();
}
