package me.pfdai.volley;

import android.support.v4.util.ArrayMap;

import java.util.Map;

/**
 * Created by daipengfei on 15/6/23.
 */
public class VolleyMultipartParams {

    private Map<String, String> paramsStr;
    private Map<String, byte[]> paramsBytes;

    public VolleyMultipartParams() {
        paramsStr = new ArrayMap();
        paramsBytes = new ArrayMap();
    }

    public Map<String, String> getParamsStr() {
        return paramsStr;
    }

    public Map<String, byte[]> getParamsBytes() {
        return paramsBytes;
    }

    public void put(String key, String value) {
        paramsStr.put(key, value);
    }

    public void put(String key, byte[] value) {
        paramsBytes.put(key, value);
    }

}
