package com.viethoa.mvvm.Components.modules.ItemMainModule;

import android.content.Context;

import com.viethoa.mvvm.Features.ViewModels.MainViewModel.ItemMainViewModel.ItemMainViewModel;
import com.viethoa.mvvm.Features.ViewModels.MainViewModel.ItemMainViewModel.ItemMainViewModelImp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by VietHoa on 27/04/16.
 */
@Module
public class ItemMainModule {

    private Context context;

    public ItemMainModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideParentContext() {
        return context;
    }

    @Provides
    @Singleton
    ItemMainViewModel provideItemMainViewModel(ItemMainViewModelImp viewModel) {
        return viewModel;
    }
}
