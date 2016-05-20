package com.viethoa.mvvm.Features.Views.Detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.viethoa.mvvm.BaseApplications.dialogs.MessageDialog;
import com.viethoa.mvvm.BaseApplications.views.RxBaseActivity;
import com.viethoa.mvvm.Components.modules.AppComponent;
import com.viethoa.mvvm.Components.modules.DetailModule.DaggerDetailComponent;
import com.viethoa.mvvm.Components.modules.DetailModule.DetailComponent;
import com.viethoa.mvvm.Components.modules.DetailModule.DetailModule;
import com.viethoa.mvvm.Features.MVVMApplication;
import com.viethoa.mvvm.Features.Models.Vocabulary;
import com.viethoa.mvvm.Features.ViewModels.DetailViewModel.DetailViewModel;
import com.viethoa.mvvm.R;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;

public class DetailActivity extends RxBaseActivity {
    private static final String EXTRACT_VOCABULARY = "extract-data";

    @Inject
    DetailViewModel detailViewModel;

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
    protected void injectModule(AppComponent appComponent) {
        DetailComponent component = DaggerDetailComponent.builder()
                .appComponent(MVVMApplication.newInstance().getComponent())
                .detailModule(new DetailModule())
                .build();
        component.inject(this);
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

        // Word
        detailViewModel.word()
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvWord::setText);
        // Vocalization
        detailViewModel.vocalization()
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvVocalization::setText);
        // Word define
        detailViewModel.wordDefinition()
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvWordDefinition::setText);
        // Word image meaning
        detailViewModel.wordImageMeaning()
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(url -> {
                    Picasso.with(this).load(vocabulary.getUrl())
                            .placeholder(R.drawable.placeholder_image)
                            .into(ivWordMeaning);
                });

        detailViewModel.setDetailCommand().call(vocabulary);
    }

    //----------------------------------------------------------------------------------------------
    // Message Dialog
    //----------------------------------------------------------------------------------------------

    @OnClick(R.id.btn_show_message_dialog)
    protected void BtnShowMessageDialogClicked() {
        MessageDialog message = new MessageDialog(this,
                R.string.dialog_message_title,
                R.string.dialog_message_text,
                R.string.dialog_message_positive_text,
                new SingleMessageDialogCallback()); // Set null, if you don't want to handle callback.
        message.show();

        // String instead.
//        MessageDialog message = new MessageDialog(this,
//                getString(R.string.dialog_message_title),
//                getString(R.string.dialog_message_text),
//                getString(R.string.dialog_message_negative_text),
//                getString(R.string.dialog_message_positive_text),
//                new MessageDialogCallback()); // Set null, if you don't want to handle callback.
//        message.show();
    }

    private class SingleMessageDialogCallback implements MessageDialog.MessageDialogSingleListener {
        @Override
        public void OnButtonClicked() {

        }
    }

    private class MessageDialogCallback implements MessageDialog.MessageDialogListener {
        @Override
        public void OnNegativeClicked() {

        }

        @Override
        public void OnPositiveClicked() {

        }
    }

    //----------------------------------------------------------------------------------------------
    // Loading dialog
    //----------------------------------------------------------------------------------------------

    @OnClick(R.id.btn_show_loading_dialog)
    protected void BtnShowLoadingDialogClicked() {
        showLoadingDialog();
        // auto dismiss after 5s.
        new Handler().postDelayed(this::dismissLoadingDialog, 5000);
    }
}
