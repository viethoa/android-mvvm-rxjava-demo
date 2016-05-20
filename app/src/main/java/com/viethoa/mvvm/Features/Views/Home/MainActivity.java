package com.viethoa.mvvm.Features.Views.Home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.viethoa.mvvm.BaseApplications.animations.AnimationUtils;
import com.viethoa.mvvm.BaseApplications.animations.Shake;
import com.viethoa.mvvm.BaseApplications.views.RxBaseActivity;
import com.viethoa.mvvm.Components.modules.AppComponent;
import com.viethoa.mvvm.Components.modules.HomeModule.DaggerHomeComponent;
import com.viethoa.mvvm.Components.modules.HomeModule.HomeComponent;
import com.viethoa.mvvm.Components.modules.HomeModule.HomeModule;
import com.viethoa.mvvm.Features.MVVMApplication;
import com.viethoa.mvvm.Features.ViewModels.MainViewModel.MainViewModel;
import com.viethoa.mvvm.Features.Views.CustomeViews.MVVMRecyclerView;
import com.viethoa.mvvm.R;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends RxBaseActivity {

    @Inject
    MainViewModel mainViewModel;

    @Bind(R.id.et_search)
    EditText etSearch;
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

        initializeSearchView();
        initializeRecycleView();
    }

    //----------------------------------------------------------------------------------------------
    // RecycleView
    //----------------------------------------------------------------------------------------------

    private void initializeRecycleView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setEmptyView(emptyView);

        // Load Vocabularies
        mainViewModel.vocabularies()
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(vocabularies -> {
                    MainAdapter mainAdapter = new MainAdapter(this, vocabularies);
                    recyclerView.setAdapter(mainAdapter);
                }, throwable -> {
                    MainAdapter mainAdapter = new MainAdapter(this, null);
                    recyclerView.setAdapter(mainAdapter);
                });

        mainViewModel.getGetVocabulariesCommand().call(null);
    }

    //----------------------------------------------------------------------------------------------
    // SearchView
    //----------------------------------------------------------------------------------------------

    private void initializeSearchView() {
        etSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        etSearch.setOnEditorActionListener(new SearchEditorListener());
    }

    private class SearchEditorListener implements TextView.OnEditorActionListener {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId != EditorInfo.IME_ACTION_SEARCH)
                return false;

            String query = etSearch.getText().toString();
            if (TextUtils.isEmpty(query)) {
                AnimationUtils.shakeAnimationEditText(etSearch);
                new Shake().start(etSearch);
                return true;
            }

            // Todo search here
            return false;
        }
    }

    //----------------------------------------------------------------------------------------------
    // Event
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
