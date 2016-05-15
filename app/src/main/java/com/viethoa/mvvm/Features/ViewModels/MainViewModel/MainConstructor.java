package com.viethoa.mvvm.Features.ViewModels.MainViewModel;

import com.jakewharton.rxrelay.PublishRelay;
import com.viethoa.mvvm.BaseApplications.views.BaseView;
import com.viethoa.mvvm.Features.Models.Vocabulary;

import java.util.List;

import rx.Observable;

/**
 * Created by VietHoa on 15/05/16.
 */
public interface MainConstructor {

    interface MainView extends BaseView {

    }

    interface UserActions {
        Observable<List<Vocabulary>> vocabularies();

        PublishRelay<Void> getGetVocabulariesCommand();
    }
}
