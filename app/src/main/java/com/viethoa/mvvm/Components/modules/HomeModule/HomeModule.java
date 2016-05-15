package com.viethoa.mvvm.Components.modules.HomeModule;

import com.viethoa.mvvm.Features.ViewModels.MainViewModel.MainConstructor;
import com.viethoa.mvvm.Features.ViewModels.MainViewModel.MainViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by VietHoa on 19/04/16.
 */
@Module
public class HomeModule {

    private MainConstructor.MainView mainView;

    public HomeModule(MainConstructor.MainView mainView) {
        this.mainView = mainView;
    }

    @Provides
    MainConstructor.MainView provideMainView() {
        return mainView;
    }

    @Provides
    MainConstructor.UserActions provideMainViewModel(MainViewModel viewModel) {
        return viewModel;
    }
}
