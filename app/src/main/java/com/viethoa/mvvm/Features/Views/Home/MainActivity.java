package com.viethoa.mvvm.Features.Views.Home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.viethoa.mvvm.BaseApplications.views.RxBaseActivity;
import com.viethoa.mvvm.Components.modules.ApplicationGraph;
import com.viethoa.mvvm.Features.ViewModels.MainViewModel.MainViewModel;
import com.viethoa.mvvm.R;

import javax.inject.Inject;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends RxBaseActivity {

    @Inject
    MainViewModel mainViewModel;

    @Bind(R.id.my_recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void injectModule(ApplicationGraph objectGraph) {
        objectGraph.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mainViewModel.vocabularies()
                .observeOn(AndroidSchedulers.mainThread())
                .map(vocabularies -> new MainAdapter(this, vocabularies))
                .subscribe(recyclerView::setAdapter);

        mainViewModel.getGetVocabulariesCommand().call(null);
    }


}
