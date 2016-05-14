package com.viethoa.mvvm.Features.ViewModels.MainViewModel;

import com.jakewharton.rxrelay.PublishRelay;
import com.viethoa.mvvm.Features.Interactor.VocabularyInteractor;
import com.viethoa.mvvm.Features.Models.Vocabulary;

import java.util.List;

import rx.Observable;

/**
 * Created by VietHoa on 27/04/16.
 */
public interface MainViewModel {

    Observable<List<Vocabulary>> vocabularies();

    PublishRelay<Void> getGetVocabulariesCommand();
}
