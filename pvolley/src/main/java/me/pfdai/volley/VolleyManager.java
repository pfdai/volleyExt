package me.pfdai.volley;

import android.util.Patterns;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.Map;

public class VolleyManager {

    private static VolleyManager sInstance;

    public static synchronized VolleyManager getInstance() {
        if (sInstance == null) {
            sInstance = new VolleyManager();
        }
        return sInstance;
    }

    public static synchronized void clearResource() {
        if (sInstance != null) {
            sInstance.clearVolleyResource();
            sInstance = null;
        }
    }

    public static boolean isValidUrl(String url) {
        try {
            return Patterns.WEB_URL.matcher(url).matches();
        } catch (Exception e) {
            // 解析错误，非正常Url
        }
        return false;
    }

    private RequestQueue mRequestQueue;

    private ImageLoader mNetworkImageLoader;

    private VolleyManager() {
        mRequestQueue = new RequestQueue(CacheConfig.getDiskCache(), new BasicNetwork(new HurlStack()));
        // 默认开启
        mRequestQueue.start();
    }

    public void clearVolleyResource() {
        // 关闭队列
        if (mRequestQueue != null)
            mRequestQueue.stop();

        // 关闭图片加载
        if (mNetworkImageLoader != null)
            mNetworkImageLoader = null;
    }

    private ImageLoader getNetworkImageLoader() {
        if (mNetworkImageLoader == null) {
            // 开启内存和本地两级缓存
            mNetworkImageLoader = new ImageLoader(mRequestQueue, CacheConfig.getBitmapCache());
        }
        return mNetworkImageLoader;
    }

    private <T> void add(Request<T> req, VolleyHandler vh) {
        add(req, null, vh);
    }

    private <T> void add(Request<T> req, Object tag, VolleyHandler vh) {
        if (tag != null) {
            req.setTag(tag);
        }

        if (mRequestQueue != null)
            mRequestQueue.add(req);
    }

    public void cancelAll(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    // 字节数组的POST下载方法
    public void requestBytePost(String url, final Map<String, String> para, final VolleyHandler<byte[]> vh) {
        // url判断
        if (!isValidUrl(url)) {
            vh.onFailure(1);
            return;
        }
        // 构造request
        PostByteArrayRequest byteRequest = new PostByteArrayRequest(url, para, vh, vh);
        add(byteRequest, vh);
    }

    //字节数据的上传方法
    public void requestMultipartPost(String url, final VolleyMultipartParams params, final VolleyHandler vh) {
        // url判断
        if (!isValidUrl(url)) {
            vh.onFailure(1);
            return;
        }
        // 构造request
        MultipartRequest mr = new MultipartRequest(url, vh);

        MultipartEntity me = mr.getMultiPartEntity();
        // String String
        for (Map.Entry<String, String> entry : params.getParamsStr().entrySet()) {
            me.addStringPart(entry.getKey(), entry.getValue());
        }
        // String byte[]
        for (Map.Entry<String, byte[]> entry : params.getParamsBytes().entrySet()) {
            me.addBinaryPart(entry.getKey(), entry.getValue());
        }

        add(mr, vh);
    }

    //获取JsonObject数据
    public void requestJsonObjectGet(final String url, final VolleyHandler<JSONObject> vh) {
        // url判断
        if (!isValidUrl(url)) {
            vh.onFailure(1);
            return;
        }
        // 构造request
        JsonObjectRequest request = new JsonObjectRequest(url, vh, vh);
        add(request, vh);
    }

    public void requestJsonObjectPost(final String url, final Map<String, String> params, final VolleyHandler<JSONObject> vh) {
        // url判断
        if (!isValidUrl(url)) {
            vh.onFailure(1);
            return;
        }
        // 构造request
        PostJsonObjectRequest request = new PostJsonObjectRequest(url, params, vh, vh);
        add(request, vh);
    }

    //获取String数据
    public void requestStringGet(String url, final VolleyHandler<String> vh) {
        // url判断
        if (!isValidUrl(url)) {
            vh.onFailure(1);
            return;
        }
        // 构造request
        StringRequest stringRequest = new StringRequest(url, vh, vh);
        add(stringRequest, vh);
    }

    public void requestStringPost(String url, final Map<String, String> para, final VolleyHandler<String> vh) {
        // url判断
        if (!isValidUrl(url)) {
            vh.onFailure(1);
            return;
        }
        // 构造request
        PostStringRequest request = new PostStringRequest(url, para, vh, vh);
        add(request, vh);
    }

    private String formatUrl(Map<String, String> map, String url) {
        String formatUrl = url;
        if (map.isEmpty()) {
            return url;
        } else {
            formatUrl += "?";
        }
        for (String key : map.keySet()) {
            formatUrl += (key + "=" + map.get(key) + "&");
        }
        formatUrl = formatUrl.substring(0, formatUrl.length() - 1);
        return formatUrl;
    }

    // 获取图片
    public void requestImage(final ImageView imageView, final String url, final int errorImage) {
        if (!isValidUrl(url)) {
            imageView.setImageResource(errorImage);
            return;
        }
        getNetworkImageLoader().get(url, new VolleyImageHandler(imageView, errorImage));
    }

    public boolean getCachedImages(NetworkImageView imageView, String url) {
        if (!isValidUrl(url)) {
            return false;
        }

        imageView.setImageUrl(url, getNetworkImageLoader());
        return true;
    }

    public boolean containImageCache(String url) {
        return getNetworkImageLoader().isCached(url, 0, 0);
    }


}
