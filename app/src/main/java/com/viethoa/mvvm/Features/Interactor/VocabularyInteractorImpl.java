package com.viethoa.mvvm.Features.Interactor;

import com.viethoa.mvvm.Components.networks.FakeDataAPI;
import com.viethoa.mvvm.Features.Models.Vocabulary;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by VietHoa on 14/05/16.
 */
public class VocabularyInteractorImpl implements VocabularyInteractor {

    @Inject
    public VocabularyInteractorImpl() {

    }

    @Override
    public List<Vocabulary> getVocabularies() {
        return FakeDataAPI.getVocabularies();
    }
}
