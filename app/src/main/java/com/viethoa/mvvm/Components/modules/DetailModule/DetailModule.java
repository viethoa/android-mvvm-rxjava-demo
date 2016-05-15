package com.viethoa.mvvm.Components.modules.DetailModule;

import com.viethoa.mvvm.Features.ViewModels.DetailViewModel.DetailViewModel;
import com.viethoa.mvvm.Features.ViewModels.DetailViewModel.DetailViewModelImp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by VietHoa on 27/04/16.
 */
@Module
public class DetailModule {

    @Provides
    DetailViewModel provideDetailViewModel(DetailViewModelImp viewModel) {
        return viewModel;
    }
}
