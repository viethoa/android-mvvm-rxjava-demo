package com.viethoa.mvvm.Components.modules.ItemMainModule;

import android.content.Context;

import com.viethoa.mvvm.BaseApplications.annotations.ActivityScope;
import com.viethoa.mvvm.BaseApplications.modules.AppComponent;
import com.viethoa.mvvm.Features.ViewModels.MainViewModel.MainHolderViewModel.ItemMainViewModel;
import com.viethoa.mvvm.Features.Views.Home.MainViewHolder;

import dagger.Component;

/**
 * Created by VietHoa on 14/05/16.
 */
@ActivityScope
@Component(
        dependencies = {AppComponent.class},
        modules = ItemMainModule.class
)
public interface ItemMainComponent {

    void inject(MainViewHolder mainViewHolder);

    ItemMainViewModel getItemMainViewModel();

    Context getContext();
}
