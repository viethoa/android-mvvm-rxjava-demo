package com.viethoa.mvvm.Features.Views.Home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.viethoa.mvvm.BaseApplications.views.RxBaseActivity;
import com.viethoa.mvvm.Components.modules.AppComponent;
import com.viethoa.mvvm.Components.modules.HomeModule.DaggerHomeComponent;
import com.viethoa.mvvm.Components.modules.HomeModule.HomeComponent;
import com.viethoa.mvvm.Components.modules.HomeModule.HomeModule;
import com.viethoa.mvvm.Features.MVVMApplication;
import com.viethoa.mvvm.Features.ViewModels.MainViewModel.MainConstructor;
import com.viethoa.mvvm.R;

import javax.inject.Inject;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends RxBaseActivity implements MainConstructor.MainView {

    @Inject
    MainConstructor.UserActions mainViewModel;

    @Bind(R.id.my_recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void injectModule(AppComponent appComponent) {
        HomeComponent homeComponent = DaggerHomeComponent.builder()
                .appComponent(MVVMApplication.newInstance().getComponent())
                .homeModule(new HomeModule(this))
                .build();
        homeComponent.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mainViewModel.vocabularies()
                .map(vocabularies -> new MainAdapter(this, vocabularies))
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(recyclerView::setAdapter);

        mainViewModel.getGetVocabulariesCommand().call(null);
    }


}
