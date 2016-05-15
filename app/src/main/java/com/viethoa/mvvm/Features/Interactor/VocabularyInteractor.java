package com.viethoa.mvvm.Features.Interactor;

import com.viethoa.mvvm.Features.Models.Vocabulary;

import java.util.List;

import rx.Observable;

/**
 * Created by VietHoa on 14/05/16.
 */
public interface VocabularyInteractor {

    Observable<List<Vocabulary>> getVocabularies();
}
