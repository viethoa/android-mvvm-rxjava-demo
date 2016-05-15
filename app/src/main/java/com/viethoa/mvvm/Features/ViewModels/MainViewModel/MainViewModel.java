package com.viethoa.mvvm.Features.ViewModels.MainViewModel;

import com.jakewharton.rxrelay.PublishRelay;
import com.viethoa.mvvm.Features.Interactor.VocabularyInteractor;
import com.viethoa.mvvm.Features.Models.Vocabulary;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by VietHoa on 27/04/16.
 */
public class MainViewModel implements MainConstructor.UserActions {

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

    private PublishRelay<Void> getVocabulariesCommand = PublishRelay.create();

    @Override
    public PublishRelay<Void> getGetVocabulariesCommand() {
        return getVocabulariesCommand;
    }

    //----------------------------------------------------------------------------------------------
    // Constructor
    //----------------------------------------------------------------------------------------------

    @Inject
    MainConstructor.MainView mainView;
    @Inject
    VocabularyInteractor vocabularyInteractor;

    @Inject
    public MainViewModel() {

        getVocabulariesCommand
                .concatMap(_void -> vocabularyInteractor.getVocabularies())
                .subscribe( vocabularies -> {
                    vocabulariesSubject.onNext(vocabularies);
                }, throwable -> {
                    mainView.showErrorMessage(throwable.getMessage());
                });
    }
}
