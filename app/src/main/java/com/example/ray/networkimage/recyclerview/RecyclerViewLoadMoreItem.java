package com.example.ray.networkimage.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.ray.networkimage.R;

/**
 * Created by raychum on 10/7/15.
 */
public class RecyclerViewLoadMoreItem extends RecyclerViewItem<RecyclerViewLoadMoreItem.ViewHolder> {

    private static final int TYPE_ID = 0x270F;
    private OnLoadingListener onLoadingListener;

    public RecyclerViewLoadMoreItem(OnLoadingListener onLoadingListener) {
        this.onLoadingListener = onLoadingListener;
    }

    @Override
    public int getTypeId() {
        return TYPE_ID;
    }

    @Override
    public int getLayoutId() {
        return R.layout.view_recyclerview_loading;
    }

    @Override
    protected ViewHolder onCreateViewHolder(View view) {
        ViewHolder viewHolder = new ViewHolder(view);
        try {
            StaggeredGridLayoutManager.LayoutParams lp =
                    (StaggeredGridLayoutManager.LayoutParams) viewHolder.itemView.getLayoutParams();
            lp.setFullSpan(true);
        } catch (Exception e) {

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        if (onLoadingListener != null) {
            onLoadingListener.loadMore();
        }
    }

    public interface OnLoadingListener {
        void loadMore();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}