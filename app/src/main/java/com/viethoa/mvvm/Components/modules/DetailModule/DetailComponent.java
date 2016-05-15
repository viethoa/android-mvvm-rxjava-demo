package com.viethoa.mvvm.Components.modules.DetailModule;

import com.viethoa.mvvm.Components.annotations.ActivityScope;
import com.viethoa.mvvm.Components.modules.AppComponent;
import com.viethoa.mvvm.Features.ViewModels.DetailViewModel.DetailViewModel;
import com.viethoa.mvvm.Features.Views.Detail.DetailActivity;

import dagger.Component;

/**
 * Created by VietHoa on 14/05/16.
 */
@ActivityScope
@Component(
        dependencies = {AppComponent.class},
        modules = DetailModule.class
)
public interface DetailComponent {

    void inject(DetailActivity detailActivity);

    DetailViewModel getDetailViewModel();
}
