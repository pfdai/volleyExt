package me.pfdai.volley;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by daipengfei on 15/7/2.
 */
public abstract class VolleyHandler<T> implements Response.Listener<T>, Response.ErrorListener {

    protected abstract void onSuccess(T response);

    protected abstract void onFailure(int errorCode);

    @Override
    public void onResponse(T response) {
        onSuccess(response);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        onFailure(1);
    }
}
