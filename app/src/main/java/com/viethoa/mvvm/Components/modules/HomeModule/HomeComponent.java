package com.viethoa.mvvm.Components.modules.HomeModule;

import com.viethoa.mvvm.BaseApplications.annotations.ActivityScope;
import com.viethoa.mvvm.BaseApplications.modules.AppComponent;
import com.viethoa.mvvm.Features.ViewModels.MainViewModel.MainViewModelImpl;
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

    MainViewModelImpl getMainViewModel();
}
