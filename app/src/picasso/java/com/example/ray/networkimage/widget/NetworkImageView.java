package com.example.ray.networkimage.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.example.ray.networkimage.R;

/**
 * Created by Ray on 15/8/15.
 */
public class NetworkImageView extends ImageView {

    public static void init(Context context){
        Picasso.with(context).setLoggingEnabled(true);
    }

    private Drawable placeholderDrawable;
    private Drawable errorDrawable;

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

    private void init(AttributeSet attrs){
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.NetworkImageView);
        placeholderDrawable = a.getDrawable(R.styleable.NetworkImageView_placeholderImage);
        errorDrawable = a.getDrawable(R.styleable.NetworkImageView_failureImage);
        a.recycle();
    }

    public void load(String url){
        if (url!= null && url.length() > 0){
            Picasso.with(this.getContext()).load(url).placeholder(placeholderDrawable).error(errorDrawable).into(this);
        }
    }
}
