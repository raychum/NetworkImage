package com.example.ray.networkimage.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.ray.networkimage.R;
import com.example.ray.networkimage.widget.NetworkImageView;

import java.util.ArrayList;

/**
 * Created by raychum on 10/7/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = RecyclerViewAdapter.class.getSimpleName();

    private final ArrayList<RecyclerViewItem> fullList = new ArrayList<>();
    private final ArrayList<RecyclerViewItem> displayList = new ArrayList<>();
    private RecyclerViewLoadMoreItem loadingViewItem;
    private boolean isShowLoadMore = true;

    public RecyclerViewAdapter() {
    }

    public RecyclerViewLoadMoreItem getLoadingViewItem() {
        return loadingViewItem;
    }

    public void setLoadingViewItem(RecyclerViewLoadMoreItem loadingViewItem) {
        this.loadingViewItem = loadingViewItem;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int typeId) {
        if (loadingViewItem != null && typeId == loadingViewItem.getTypeId() && isShowLoadMore) {
            return loadingViewItem.createViewHolder(viewGroup);
        }
        for (RecyclerViewItem holder : displayList) {
            if (holder.getTypeId() == typeId) {
                return holder.createViewHolder(viewGroup);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (position == getItemCount() - 1 && loadingViewItem != null && isShowLoadMore) {
            loadingViewItem.bindViewHolder((RecyclerViewLoadMoreItem.ViewHolder) viewHolder);
        } else {
            displayList.get(position).bindViewHolder(viewHolder);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1 && loadingViewItem != null && isShowLoadMore) {
            return loadingViewItem.getTypeId();
        } else {
            return displayList.get(position).getTypeId();
        }
    }

    @Override
    public int getItemCount() {
        if (displayList.size() > 0 && loadingViewItem != null && isShowLoadMore) {
            return displayList.size() + 1;
        } else {
            return displayList.size();
        }
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        if (holder.itemView.findViewById(R.id.imageView) != null ){
            final NetworkImageView imageView = (NetworkImageView) holder.itemView.findViewById(R.id.imageView);
            imageView.setImageDrawable(null);
        }
        super.onViewRecycled(holder);
    }

    public void add(RecyclerViewItem item) {
        fullList.add(item);
        displayList.add(item);
    }

    public void clearAll() {
        fullList.clear();
        displayList.clear();
    }

    public void hide(int position) {
        if (fullList.size() > 0) {
            if (displayList.contains(fullList.get(position))) {
                displayList.remove(fullList.get(position));
            }
        }
    }

    public void show(int position) {
        if (fullList.size() > 0) {
            if (!displayList.contains(fullList.get(position))) {
                displayList.add(fullList.get(position));
            }
        }
    }


    public ArrayList<RecyclerViewItem> getFullList() {
        return fullList;
    }
    public ArrayList<RecyclerViewItem> getDisplayList() {
        return displayList;
    }

    public boolean isShowLoadMore() {
        return isShowLoadMore;
    }

    public void setShowLoadMore(boolean isShowLoading) {
        this.isShowLoadMore = isShowLoading;
    }
}