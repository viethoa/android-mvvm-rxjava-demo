package com.viethoa.mvvm.Components.modules.HomeModule;

import com.viethoa.mvvm.Features.ViewModels.MainViewModel.MainViewModel;
import com.viethoa.mvvm.Features.ViewModels.MainViewModel.MainViewModelImp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by VietHoa on 14/05/16.
 */
@Module
public class HomeModuleTest extends HomeModule {

    @Provides
    @Singleton
    MainViewModel provideMainViewModel(MainViewModelImp viewModel) {
        return viewModel;
    }
}
