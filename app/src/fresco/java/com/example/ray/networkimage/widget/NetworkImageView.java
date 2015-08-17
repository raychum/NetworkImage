package com.example.ray.networkimage.widget;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * Created by Ray on 15/8/15.
 */
public class NetworkImageView extends SimpleDraweeView {

    private int width;
    private int height;

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

    public static void init(Context context) {
        Fresco.initialize(context);
    }

    public void load(String url){
        if (url!= null && url.length() > 0){
            if (width > 0 && height > 0) {
                final Uri uri = Uri.parse(url);
                final ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setResizeOptions(new ResizeOptions(width, height))
                        .build();
                final PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                        .setOldController(getController())
                        .setImageRequest(request)
                        .build();
                setController(controller);
            } else {
                setImageURI(Uri.parse(url));
            }
        }
    }

    public NetworkImageView resize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }
}
