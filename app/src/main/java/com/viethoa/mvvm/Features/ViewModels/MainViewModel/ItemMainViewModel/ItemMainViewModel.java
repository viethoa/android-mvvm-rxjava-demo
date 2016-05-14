package com.viethoa.mvvm.Features.ViewModels.MainViewModel.ItemMainViewModel;

import android.content.Intent;

import com.jakewharton.rxrelay.PublishRelay;
import com.viethoa.mvvm.Features.Models.Vocabulary;

import rx.Observable;

/**
 * Created by VietHoa on 27/04/16.
 */
public interface ItemMainViewModel {
    Observable<String> word();

    Observable<String> vocalization();

    Observable<String> wordDefinition();

    Observable<String> wordImageMeaning();

    Observable<Intent> openDetailActivity();

    PublishRelay<Vocabulary> setDetailCommand();

    PublishRelay<Intent> navigateToDetailActivityCommand();
}
