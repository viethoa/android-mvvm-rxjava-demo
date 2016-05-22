package com.viethoa.mvvm.Features.Views.Home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.TextViewEditorActionEvent;
import com.viethoa.mvvm.BaseApplications.animations.AnimationUtils;
import com.viethoa.mvvm.BaseApplications.animations.Shake;
import com.viethoa.mvvm.BaseApplications.modules.AppComponent;
import com.viethoa.mvvm.BaseApplications.views.RxBaseActivity;
import com.viethoa.mvvm.Components.modules.HomeModule.DaggerHomeComponent;
import com.viethoa.mvvm.Components.modules.HomeModule.HomeComponent;
import com.viethoa.mvvm.Components.modules.HomeModule.HomeModule;
import com.viethoa.mvvm.Features.MVVMApplication;
import com.viethoa.mvvm.Features.Models.Vocabulary;
import com.viethoa.mvvm.Features.ViewModels.MainViewModel.MainViewModel;
import com.viethoa.mvvm.Features.Views.CustomeViews.MVVMRecyclerView;
import com.viethoa.mvvm.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

import static com.jakewharton.rxbinding.widget.RxTextView.editorActionEvents;
import static com.jakewharton.rxbinding.widget.RxTextView.textChanges;

public class MainActivity extends RxBaseActivity {
    private MainAdapter mainAdapter;

    @Inject
    MainViewModel mainViewModel;

    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.search_progress)
    View searchProgress;
    @Bind(R.id.my_recycler_view)
    MVVMRecyclerView recyclerView;
    @Bind(R.id.no_data_list_view)
    View emptyView;

    @Override
    protected void injectModule(AppComponent appComponent) {
        HomeComponent homeComponent = DaggerHomeComponent.builder()
                .appComponent(MVVMApplication.newInstance().getComponent())
                .homeModule(new HomeModule())
                .build();
        homeComponent.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setEmptyView(emptyView);

        // RecycleView
        mainViewModel.vocabularies()
                .compose(bindToMainThread())
                .compose(bindToLifecycle())
                .subscribe(vocabularies -> {
                    showVocabularies(vocabularies);
                }, throwable -> {
                    showVocabularies(new ArrayList<>());
                });

        // Search UI animation effect.
        etSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        editorActionEvents(etSearch)
                .compose(bindToMainThread())
                .compose(bindToLifecycle())
                .subscribe(this::shakeEditTextViewWhenEmpty);

        // Get vocabularies in first-time and take search when text change.
        textChanges(etSearch)
                .compose(bindToMainThread())
                .compose(bindToLifecycle())
                .map(CharSequence::toString)
                .subscribe(text -> {
                    searchProgress.setVisibility(View.VISIBLE);
                    mainViewModel.getGetVocabulariesCommand().call(text);
                });
    }

    //----------------------------------------------------------------------------------------------
    // Events
    //----------------------------------------------------------------------------------------------

    private void showVocabularies(List<Vocabulary> vocabularies) {
        searchProgress.setVisibility(View.GONE);
        if (mainAdapter == null) {
            mainAdapter = new MainAdapter(this, vocabularies);
            recyclerView.setAdapter(mainAdapter);
        } else {
            mainAdapter.refreshData(vocabularies);
        }
    }

    private void shakeEditTextViewWhenEmpty(TextViewEditorActionEvent editorActionEvent) {
        if (editorActionEvent.actionId() != EditorInfo.IME_ACTION_SEARCH)
            return;
        String query = etSearch.getText().toString();
        if (!TextUtils.isEmpty(query))
            return;

        AnimationUtils.shakeAnimationEditText(etSearch);
        new Shake().start(etSearch);
    }

    //----------------------------------------------------------------------------------------------
    // User Actions Event
    //----------------------------------------------------------------------------------------------

    @OnClick(R.id.btn_clear_search)
    protected void BtnClearSearchTextClicked() {
        etSearch.setText("");
    }

    @OnClick(R.id.et_search)
    protected void OnEditTextSearchClicked() {
        etSearch.selectAll();
    }
}
