package com.viethoa.mvvm.Features.ViewModels.DetailViewModel;

import com.jakewharton.rxrelay.PublishRelay;
import com.viethoa.mvvm.Features.Models.Vocabulary;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by VietHoa on 27/04/16.
 */
public class DetailViewModelImp implements DetailViewModel {

    //----------------------------------------------------------------------------------------------
    // Members
    //----------------------------------------------------------------------------------------------

    private BehaviorSubject<String> wordSubject = BehaviorSubject.create();
    private BehaviorSubject<String> vocalizationSubject = BehaviorSubject.create();
    private BehaviorSubject<String> wordDefineSubject = BehaviorSubject.create();
    private BehaviorSubject<String> wordImageMeaningSubject = BehaviorSubject.create();

    @Override
    public Observable<String> word() {
        return wordSubject.asObservable();
    }

    @Override
    public Observable<String> vocalization() {
        return vocalizationSubject.asObservable();
    }

    @Override
    public Observable<String> wordDefinition() {
        return wordDefineSubject.asObservable();
    }

    @Override
    public Observable<String> wordImageMeaning() {
        return wordImageMeaningSubject.asObservable();
    }

    //----------------------------------------------------------------------------------------------
    // Command
    //----------------------------------------------------------------------------------------------

    private PublishRelay<Vocabulary> setDetailCommand = PublishRelay.create();

    @Override
    public PublishRelay<Vocabulary> setDetailCommand() {
        return setDetailCommand;
    }

    //----------------------------------------------------------------------------------------------
    // Constructor
    //----------------------------------------------------------------------------------------------

    @Inject
    public DetailViewModelImp() {

        // Word
        setDetailCommand
                .map(vocabulary -> vocabulary != null ? vocabulary.getWord() : "")
                .subscribe(wordSubject::onNext);

        // vocalization
        setDetailCommand
                .map(vocabulary -> vocabulary != null ? vocabulary.getVocalization() : "")
                .subscribe(vocalizationSubject::onNext);

        // Word define
        setDetailCommand
                .map(vocabulary -> vocabulary != null ? vocabulary.getDefine() : "")
                .subscribe(wordDefineSubject::onNext);

        // word image meaning
        setDetailCommand
                .map(vocabulary -> vocabulary != null ? vocabulary.getUrl() : "")
                .subscribe(wordImageMeaningSubject::onNext);

    }
}
