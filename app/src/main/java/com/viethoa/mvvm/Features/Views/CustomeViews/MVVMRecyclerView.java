package com.viethoa.mvvm.Features.Views.CustomeViews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by VietHoa on 05/05/16.
 */
public class MVVMRecyclerView extends RecyclerView {
    private View emptyView;
    private View headerView;

    public MVVMRecyclerView(Context context) {
        super(context);
    }

    public MVVMRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MVVMRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //----------------------------------------------------------------------------------------------
    // Empty View
    //----------------------------------------------------------------------------------------------

    final private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };

    void checkIfEmpty() {
        if (getAdapter() == null)
            return;

        boolean emptyViewVisible = getAdapter().getItemCount() <= 0;
        if (emptyView != null) {
            emptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
        }
        if (headerView != null) {
            headerView.setVisibility(emptyViewVisible ? GONE : VISIBLE);
        }
        setVisibility(emptyViewVisible ? GONE : VISIBLE);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        final Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }

        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }

        checkIfEmpty();
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        checkIfEmpty();
    }

    //----------------------------------------------------------------------------------------------
    // Empty View
    //----------------------------------------------------------------------------------------------

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
        checkIfEmpty();
    }
}
