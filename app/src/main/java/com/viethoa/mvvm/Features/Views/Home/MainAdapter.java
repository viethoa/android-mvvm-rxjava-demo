package com.viethoa.mvvm.Features.Views.Home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viethoa.mvvm.Features.Models.Vocabulary;
import com.viethoa.mvvm.R;

import java.util.List;

/**
 * Created by VietHoa on 27/04/16.
 */
public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {
    private Context mContext;
    private List<Vocabulary> mDataArray;

    public MainAdapter(Context context, List<Vocabulary> mDataArray) {
        this.mContext = context;
        this.mDataArray = mDataArray;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_layout, parent, false);
        return new MainViewHolder(mContext, itemView);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        if (position < 0 || position >= mDataArray.size())
            return;

        Vocabulary vocabulary = mDataArray.get(position);
        if (vocabulary == null)
            return;

        holder.setDetailCommand(mContext, vocabulary);
    }

    @Override
    public int getItemCount() {
        if (mDataArray == null)
            return 0;
        return mDataArray.size();
    }

}
