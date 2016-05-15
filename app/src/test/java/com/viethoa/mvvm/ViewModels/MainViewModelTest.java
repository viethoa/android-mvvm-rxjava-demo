package com.viethoa.mvvm.ViewModels;

import com.viethoa.mvvm.BuildConfig;
import com.viethoa.mvvm.Features.Models.Vocabulary;
import com.viethoa.mvvm.Features.ViewModels.MainViewModel.MainViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by VietHoa on 14/05/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainViewModelTest {

    @Inject
    MainViewModel mainViewModel;

    @Inject
    public MainViewModelTest() {

    }

    @Before
    public void setUp() throws Exception {
//        ApplicationComponentTest component = ApplicationComponentTest
//                .Initializer.init(RuntimeEnvironment.application);
//        component.inject(this);
    }

    @Test
    public void shouldCorrectVocabularies() {
        List<Vocabulary> vocabularies = new ArrayList<>();
        vocabularies.add(new Vocabulary(0, "hello", "[hello]", "", ""));
        vocabularies.add(new Vocabulary(1, "hello", "[hello]", "", ""));

        TestSubscriber<List<Vocabulary>> testSubscriber = new TestSubscriber<>();
        mainViewModel.vocabularies().subscribe(testSubscriber);
        testSubscriber.assertNoErrors();

        List<Vocabulary> response = testSubscriber.getOnNextEvents().get(0);
        assertNotNull(response);
        assertEquals(2, response.size());

        mainViewModel.getGetVocabulariesCommand().call(null);


    }
}
