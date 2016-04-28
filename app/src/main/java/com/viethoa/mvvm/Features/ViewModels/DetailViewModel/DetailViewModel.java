package com.viethoa.mvvm.Features.ViewModels.DetailViewModel;

import com.jakewharton.rxrelay.PublishRelay;
import com.viethoa.mvvm.Features.Models.Vocabulary;

import rx.Observable;

/**
 * Created by VietHoa on 27/04/16.
 */
public interface DetailViewModel {

    Observable<String> word();

    Observable<String> vocalization();

    Observable<String> wordDefinition();

    Observable<String> wordImageMeaning();

    PublishRelay<Vocabulary> setDetailCommand();
}
