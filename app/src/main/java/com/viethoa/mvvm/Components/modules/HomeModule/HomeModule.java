package com.viethoa.mvvm.Components.modules.HomeModule;

import com.viethoa.mvvm.Features.ViewModels.MainViewModel.MainViewModel;
import com.viethoa.mvvm.Features.ViewModels.MainViewModel.MainViewModelImp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by VietHoa on 19/04/16.
 */
@Module
public class HomeModule {

    @Provides
    MainViewModel provideMainViewModel(MainViewModelImp viewModel) {
        return viewModel;
    }
}
