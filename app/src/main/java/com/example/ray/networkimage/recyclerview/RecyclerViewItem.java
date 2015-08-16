package com.example.ray.networkimage.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by raychum on 10/7/15.
 */
public abstract class RecyclerViewItem<T extends RecyclerView.ViewHolder> {

    public int getTypeId() {
        return this.getClass().hashCode();
    }

    public T createViewHolder(ViewGroup parent) {
        final View v = LayoutInflater.from(parent.getContext())
                .inflate(getLayoutId(), parent, false);
        return onCreateViewHolder(v);
    }

    public abstract int getLayoutId();

    public void bindViewHolder(T viewHolder) {
        onBindViewHolder(viewHolder);
    }

    protected abstract T onCreateViewHolder(View view);

    protected abstract void onBindViewHolder(T viewHolder);
}