package com.viethoa.mvvm.ViewModels;

import com.viethoa.mvvm.BuildConfig;
import com.viethoa.mvvm.Features.Interactor.VocabularyInteractor;
import com.viethoa.mvvm.Features.Models.Vocabulary;
import com.viethoa.mvvm.Features.ViewModels.MainViewModel.MainConstructor;
import com.viethoa.mvvm.Features.ViewModels.MainViewModel.MainViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by VietHoa on 14/05/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainViewModelTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    MainConstructor.MainView mainView;
    @Mock
    VocabularyInteractor vocabularyInteractor;

    @InjectMocks
    MainViewModel mainViewModel;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void shouldGetCorrectVocabularies() {
        // Setup Fake Data
        List<Vocabulary> vocabularies = new ArrayList<>();
        vocabularies.add(new Vocabulary());
        vocabularies.add(new Vocabulary());
        Observable<List<Vocabulary>> observable = Observable.create(subscriber -> {
            subscriber.onNext(vocabularies);
            subscriber.onCompleted();
        });
        when(vocabularyInteractor.getVocabularies()).thenReturn(observable);

        // Subscriber
        TestSubscriber<List<Vocabulary>> testSubscriber = new TestSubscriber<>();
        mainViewModel.vocabularies().subscribe(testSubscriber);
        testSubscriber.assertNoErrors();

        // Trigger asynchronous
        mainViewModel.getGetVocabulariesCommand().call(null);

        // Checkout response
        List<Vocabulary> response = testSubscriber.getOnNextEvents().get(0);
        assertNotNull(response);
        assertEquals(2, response.size());
    }

    @Test
    public void errorGetVocabularies() {

    }
}
