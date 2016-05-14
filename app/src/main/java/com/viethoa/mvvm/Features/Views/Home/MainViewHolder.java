package com.viethoa.mvvm.Features.Views.Home;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.viethoa.mvvm.BaseApplications.picasso.CircleTransform;
import com.viethoa.mvvm.Components.modules.ApplicationComponent;
import com.viethoa.mvvm.Components.modules.ApplicationGraph;
import com.viethoa.mvvm.Features.Models.Vocabulary;
import com.viethoa.mvvm.Features.ViewModels.MainViewModel.ItemMainViewModel.ItemMainViewModel;
import com.viethoa.mvvm.Features.Views.Detail.DetailActivity;
import com.viethoa.mvvm.R;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

import static com.jakewharton.rxbinding.view.RxView.clicks;

/**
 * Created by VietHoa on 27/04/16.
 */
public class MainViewHolder extends RecyclerView.ViewHolder {
    private Context mContext;

    @Inject
    ItemMainViewModel itemMainViewModel;

    @Bind(R.id.card_view_content)
    View contentView;
    @Bind(R.id.tv_word)
    TextView tvWord;
    @Bind(R.id.tv_vocalization)
    TextView tvVocalization;
    @Bind(R.id.tv_definition)
    TextView tvWordDefinition;
    @Bind(R.id.iv_word_meaning)
    ImageView ivWordImageMeaning;

    @Inject
    public MainViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        ButterKnife.bind(this, itemView);

        ApplicationGraph objectGraph = ApplicationComponent.Initializer.init(mContext);
        objectGraph.inject(this);

        // Word
        itemMainViewModel.word()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvWord::setText);
        // Vocalization
        itemMainViewModel.vocalization()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvVocalization::setText);
        // Word define
        itemMainViewModel.wordDefinition()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvWordDefinition::setText);
        // Word image meaning
        itemMainViewModel.wordImageMeaning()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(url -> {
                    Picasso.with(mContext).load(url)
                            .placeholder(R.drawable.placeholder_user)
                            .transform(new CircleTransform())
                            .into(ivWordImageMeaning);
                });

        itemMainViewModel.openDetailActivity()
                .subscribe(intent -> {
                    navigationToDetailActivityWithRippleDelay(context, intent);
                });
    }

    public void setDetailCommand(Context context, Vocabulary vocabulary) {

        // Fill vocabulary data
        itemMainViewModel.setDetailCommand().call(vocabulary);

        // Navigate to detail screen
        clicks(contentView)
                .map(_void -> DetailActivity.newInstance(context, vocabulary))
                .subscribe(intent -> {
                    itemMainViewModel.navigateToDetailActivityCommand().call(intent);
                });
    }

    private void navigationToDetailActivityWithRippleDelay(Context context, Intent intent) {
        if (intent == null || context == null)
            return;

        ActivityOptions compat = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext,
                new Pair(tvWord, tvWord.getTransitionName()),
                new Pair(tvVocalization, tvVocalization.getTransitionName()),
                new Pair(tvWordDefinition, tvWordDefinition.getTransitionName()),
                new Pair(ivWordImageMeaning, ivWordImageMeaning.getTransitionName()));

        Observable.just(intent)
                .delay(200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(detailActivityIntent -> {
                    context.startActivity(detailActivityIntent, compat.toBundle());
                });
    }
}
