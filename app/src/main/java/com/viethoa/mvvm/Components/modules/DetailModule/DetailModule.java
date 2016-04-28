package com.viethoa.mvvm.Components.modules.DetailModule;

import com.viethoa.mvvm.Features.ViewModels.DetailViewModel.DetailViewModel;
import com.viethoa.mvvm.Features.ViewModels.DetailViewModel.DetailViewModelImp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by VietHoa on 27/04/16.
 */
@Module
public class DetailModule {

    @Provides
    @Singleton
    DetailViewModel provideDetailViewModel(DetailViewModelImp viewModel) {
        return viewModel;
    }
}
