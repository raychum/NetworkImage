package com.example.ray.networkimage.widget;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Ray on 15/8/15.
 */
public class NetworkImageView extends SimpleDraweeView {

    public static void init(Context context){
        Fresco.initialize(context);
    }

    public NetworkImageView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }

    public NetworkImageView(Context context) {
        super(context);
    }

    public NetworkImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NetworkImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void load(String url){
        if (url!= null && url.length() > 0){
            setImageURI(Uri.parse(url));
        }
    }
}
