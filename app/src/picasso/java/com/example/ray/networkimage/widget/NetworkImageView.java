package com.example.ray.networkimage.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.ray.networkimage.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * Created by Ray on 15/8/15.
 */
public class NetworkImageView extends ImageView {

    private Drawable placeholderDrawable;
    private Drawable errorDrawable;
    private int width;
    private int height;
    public NetworkImageView(Context context) {
        super(context);
    }

    public NetworkImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public NetworkImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NetworkImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public static void init(Context context) {
        Picasso.with(context).setLoggingEnabled(true);
    }

    private void init(AttributeSet attrs){
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.NetworkImageView);
        placeholderDrawable = a.getDrawable(R.styleable.NetworkImageView_placeholderImage);
        errorDrawable = a.getDrawable(R.styleable.NetworkImageView_failureImage);
        a.recycle();
    }

    public void load(String url){
        if (url!= null && url.length() > 0){
            final RequestCreator requestCreator = Picasso.with(this.getContext().getApplicationContext()).load(url);
            if (placeholderDrawable != null) {
                requestCreator.placeholder(placeholderDrawable);
            }
            if (errorDrawable != null) {
                requestCreator.error(errorDrawable);
            }
            if (width > 0 && height > 0) {
                requestCreator.resize(width, height);
            }
            requestCreator.into(this);
        }
    }

    public NetworkImageView resize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }
}
