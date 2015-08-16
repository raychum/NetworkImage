package com.example.ray.networkimage.activity;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.example.ray.networkimage.R;
import com.example.ray.networkimage.recyclerview.RecyclerViewItem;
import com.example.ray.networkimage.widget.NetworkImageView;

/**
 * Created by Ray on 15/8/15.
 */
public class ImageItem extends RecyclerViewItem<ImageItem.ViewHolder> {
    private static final String TAG = ImageItem.class.getSimpleName();
    private ImageModel.Result result;

    public ImageItem(ImageModel.Result result) {
        this.result = result;
    }

    @Override
    public int getLayoutId() {
        return R.layout.view_image_item;
    }

    @Override
    protected ViewHolder onCreateViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(ViewHolder viewHolder) {
        if (viewHolder != null && viewHolder.imageView != null) {
            Display display = ((WindowManager) viewHolder.itemView.getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            float height = (float)width/(float)result.width * (float)result.height;
            if (viewHolder.itemView.getLayoutParams().height != (int) height) {
                viewHolder.itemView.getLayoutParams().height = (int) height;
            }
            viewHolder.imageView.load(result.url);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        NetworkImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = (NetworkImageView) view.findViewById(R.id.imageView);
        }
    }
}
