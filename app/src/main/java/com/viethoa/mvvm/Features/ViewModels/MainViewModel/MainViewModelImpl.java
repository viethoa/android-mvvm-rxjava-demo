package com.viethoa.mvvm.Features.ViewModels.MainViewModel;

import com.jakewharton.rxrelay.PublishRelay;
import com.viethoa.mvvm.BaseApplications.cachings.CacheManager.CacheManager;
import com.viethoa.mvvm.BaseApplications.cachings.UserSessionManager.UserSessionManager;
import com.viethoa.mvvm.Components.networks.MVVMApiService;
import com.viethoa.mvvm.Features.Interactor.VocabularyInteractor;
import com.viethoa.mvvm.Features.Models.Vocabulary;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by VietHoa on 27/04/16.
 */
public class MainViewModelImpl implements MainViewModel {

    //----------------------------------------------------------------------------------------------
    // Members
    //----------------------------------------------------------------------------------------------

    private BehaviorSubject<List<Vocabulary>> vocabulariesSubject = BehaviorSubject.create();

    @Override
    public Observable<List<Vocabulary>> vocabularies() {
        return vocabulariesSubject.asObservable();
    }

    //----------------------------------------------------------------------------------------------
    // Command
    //----------------------------------------------------------------------------------------------

    private PublishRelay<String> getVocabulariesCommand = PublishRelay.create();

    @Override
    public PublishRelay<String> getGetVocabulariesCommand() {
        return getVocabulariesCommand;
    }

    //----------------------------------------------------------------------------------------------
    // Constructor
    //----------------------------------------------------------------------------------------------

    @Inject
    MVVMApiService apiService;
    @Inject
    CacheManager cacheManager;
    @Inject
    UserSessionManager userSessionManager;
    @Inject
    VocabularyInteractor vocabularyInteractor;

    @Inject
    public MainViewModelImpl() {

        // Get Vocabularies
        getVocabulariesCommand
                .subscribeOn(Schedulers.io())
                .concatMap(searchText -> vocabularyInteractor.searchVocabularies(searchText))
                .subscribe( vocabularies -> {
                    vocabulariesSubject.onNext(vocabularies);
                }, throwable -> {
                    vocabulariesSubject.onError(throwable);
                });
    }
}
