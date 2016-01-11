package me.pfdai.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

import org.apache.http.HttpStatus;

import java.util.Map;

/**
 * Created by dengpingzheng on 15/6/17.
 */
public class PostByteArrayRequest extends Request<byte[]> {

    private final Response.Listener<byte[]> mListener;
    private Map<String, String> mPostParams;

    public PostByteArrayRequest(String url, Map<String, String> params, Response.Listener<byte[]> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, errorListener);
        mListener = listener;
        mPostParams = params;
    }

    @Override
    protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {
        if (response == null) {
            return null;
        }
        if (response.statusCode != HttpStatus.SC_OK) {
            return null;
        }
        byte[] bytes = response.data;
        return Response.success(bytes, null);
    }

    @Override
    protected void deliverResponse(byte[] response) {
        if (mListener != null) {
            mListener.onResponse(response);
        }
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mPostParams;
    }
}

