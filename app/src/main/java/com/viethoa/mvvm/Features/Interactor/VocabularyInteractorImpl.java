package com.viethoa.mvvm.Features.Interactor;

import com.viethoa.mvvm.Components.networks.FakeDataAPI;
import com.viethoa.mvvm.Features.Models.Vocabulary;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by VietHoa on 14/05/16.
 */
public class VocabularyInteractorImpl implements VocabularyInteractor {

    @Inject
    public VocabularyInteractorImpl() {

    }

    @Override
    public Observable<List<Vocabulary>> getVocabularies() {
        return Observable.create(subscriber -> {
            subscriber.onNext(FakeDataAPI.getFakeVocabularies());
            subscriber.onCompleted();
        });
    }
}
