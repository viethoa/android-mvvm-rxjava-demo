package com.viethoa.mvvm.Features.Interactor;

import android.text.TextUtils;

import com.viethoa.mvvm.Components.networks.FakeDataAPI;
import com.viethoa.mvvm.Features.Models.Vocabulary;

import java.util.ArrayList;
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
        return Observable.just(FakeDataAPI.getFakeVocabularies());
    }

    @Override
    public Observable<List<Vocabulary>> searchVocabularies(String searchText) {
        if (TextUtils.isEmpty(searchText)) {
            return Observable.just(FakeDataAPI.getFakeVocabularies());
        }

        return Observable.just(FakeDataAPI.getFakeVocabularies())
                .map(vocabularies -> search(vocabularies, searchText));
    }

    private List<Vocabulary> search(List<Vocabulary> vocabularies, String searchText) {
        if (vocabularies == null || vocabularies.size() <= 0)
            return vocabularies;

        List<Vocabulary> searchResult = new ArrayList<>();
        for (Vocabulary vocabulary : vocabularies) {
            if (vocabulary.getWord().toLowerCase().contains(searchText.toLowerCase())
                || vocabulary.getDefine().toLowerCase().contains(searchText.toLowerCase()))
                searchResult.add(vocabulary);
        }

        return searchResult;
    }
}
