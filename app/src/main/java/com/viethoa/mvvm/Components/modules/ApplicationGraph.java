package com.viethoa.mvvm.Components.modules;

import com.viethoa.mvvm.Features.Interactor.VocabularyInteractor;
import com.viethoa.mvvm.Features.Views.Detail.DetailActivity;
import com.viethoa.mvvm.Features.Views.Home.MainActivity;
import com.viethoa.mvvm.Features.Views.Home.MainViewHolder;

/**
 * Created by VietHoa on 19/04/16.
 */
public interface ApplicationGraph {

    void inject(MainActivity mainActivity);

    void inject(DetailActivity detailActivity);

    void inject(MainViewHolder mainViewHolder);

    VocabularyInteractor vocabularyInteractor();
}
