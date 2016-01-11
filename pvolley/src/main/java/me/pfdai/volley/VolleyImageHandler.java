package me.pfdai.volley;

import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by daipengfei on 16/1/9.
 */
public class VolleyImageHandler implements ImageLoader.ImageListener {

    private ImageView imageView;
    private int errorImage;
    private boolean isRound = false;

    public VolleyImageHandler(ImageView iv, int defaultImage) {
        imageView = iv;
        errorImage = defaultImage;
    }

    @Override
    public void onResponse(ImageLoader.ImageContainer imageContainer, boolean isImmediate) {
        if (imageContainer.getBitmap() != null) {
            imageView.setImageBitmap(imageContainer.getBitmap());
        } else if (errorImage != 0) {
            imageView.setImageResource(errorImage);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        imageView.setImageResource(errorImage);
    }

}
