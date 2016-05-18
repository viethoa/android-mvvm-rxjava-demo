package com.viethoa.mvvm.ViewModels.MainViewModel;

import android.content.Context;

import com.viethoa.mvvm.Features.Interactor.VocabularyInteractor;
import com.viethoa.mvvm.Features.Models.Vocabulary;
import com.viethoa.mvvm.Features.ViewModels.MainViewModel.MainConstructor;
import com.viethoa.mvvm.Features.ViewModels.MainViewModel.MainViewModel;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

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
@RunWith(PowerMockRunner.class)
@PrepareForTest({Context.class})
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

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldGetCorrectVocabularies() {
        // Setup Fake Data
        List<Vocabulary> vocabularies = new ArrayList<>();
        vocabularies.add(new Vocabulary());
        vocabularies.add(new Vocabulary());
        Observable<List<Vocabulary>> observable = Observable.just(vocabularies);
        when(vocabularyInteractor.getVocabularies()).thenReturn(observable);

        // Subscriber
        TestSubscriber<List<Vocabulary>> testSubscriber = new TestSubscriber<>();
        mainViewModel.vocabularies().subscribe(testSubscriber);

        // Trigger asynchronous
        mainViewModel.getGetVocabulariesCommand().call(null);

        // Checkout response
        List<Vocabulary> response = testSubscriber.getOnNextEvents().get(0);
        assertNotNull(response);
        assertEquals(2, response.size());
    }

    @Test
    public void errorGetVocabularies() {
        // Fake data
        String errorMessage = "not network connection";
        Observable<List<Vocabulary>> observable = Observable.error(new Exception(errorMessage));
        when(vocabularyInteractor.getVocabularies()).thenReturn(observable);

        // Subscriber
        TestSubscriber<Object> testSubscriber = new TestSubscriber<>();
        mainViewModel.vocabularies().subscribe(testSubscriber);

        // Trigger asynchronous
        mainViewModel.getGetVocabulariesCommand().call(null);

        Exception exception = (Exception) testSubscriber.getOnErrorEvents().get(0);
        Assert.assertEquals(exception.getMessage(), errorMessage);
    }
}
