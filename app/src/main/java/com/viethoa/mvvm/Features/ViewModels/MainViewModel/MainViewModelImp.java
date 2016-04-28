package com.viethoa.mvvm.Features.ViewModels.MainViewModel;

import com.jakewharton.rxrelay.PublishRelay;
import com.viethoa.mvvm.Components.networks.FakeDataAPI;
import com.viethoa.mvvm.Features.Models.Vocabulary;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by VietHoa on 27/04/16.
 */
public class MainViewModelImp implements MainViewModel {

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
    public MainViewModelImp() {

        getVocabulariesCommand
                .debounce(500, TimeUnit.MILLISECONDS)
                .map(_void -> this.getVocabularies())
                .subscribe(vocabulariesSubject::onNext);
    }

    //----------------------------------------------------------------------------------------------
    // API Helper
    //----------------------------------------------------------------------------------------------

    private List<Vocabulary> getVocabularies() {
        return FakeDataAPI.getVocabularies();
    }
}
