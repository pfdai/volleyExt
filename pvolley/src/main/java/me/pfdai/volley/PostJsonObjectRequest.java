package me.pfdai.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by daipengfei on 16/1/9.
 */
public class PostJsonObjectRequest extends JsonObjectRequest {

    private Map<String, String> mPostParams;

    public PostJsonObjectRequest(String url, Map<String, String> params, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, listener, errorListener);
        mPostParams = params;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mPostParams;
    }
}
