package com.viethoa.mvvm.Features.Views.Detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.viethoa.mvvm.BaseApplications.views.RxBaseActivity;
import com.viethoa.mvvm.Components.modules.ApplicationGraph;
import com.viethoa.mvvm.Features.Models.Vocabulary;
import com.viethoa.mvvm.Features.ViewModels.DetailViewModel.DetailViewModel;
import com.viethoa.mvvm.R;

import javax.inject.Inject;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;

public class DetailActivity extends RxBaseActivity {
    private static final String EXTRACT_VOCABULARY = "extract-data";

    @Inject
    DetailViewModel detailViewModel;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_word)
    TextView tvWord;
    @Bind(R.id.tv_vocalization)
    TextView tvVocalization;
    @Bind(R.id.tv_definition)
    TextView tvWordDefinition;
    @Bind(R.id.iv_word_meaning)
    ImageView ivWordMeaning;

    public static Intent newInstance(Context context, Vocabulary vocabulary) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRACT_VOCABULARY, vocabulary);
        return intent;
    }

    @Override
    protected void injectModule(ApplicationGraph objectGraph) {
        objectGraph.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            finish();
            return;
        }
        Vocabulary vocabulary = (Vocabulary) bundle.getSerializable(EXTRACT_VOCABULARY);
        if (vocabulary == null) {
            finish();
            return;
        }

        // Actionbar
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Word
        detailViewModel.word()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvWord::setText);
        // Vocalization
        detailViewModel.vocalization()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvVocalization::setText);
        // Word define
        detailViewModel.wordDefinition()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvWordDefinition::setText);
        // Word image meaning
        detailViewModel.wordImageMeaning()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(url -> {
                    Picasso.with(this).load(vocabulary.getUrl())
                            .placeholder(R.drawable.placeholder_image)
                            .into(ivWordMeaning);
                });

        detailViewModel.setDetailCommand().call(vocabulary);
    }
}
