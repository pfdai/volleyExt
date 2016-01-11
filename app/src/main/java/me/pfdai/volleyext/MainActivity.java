package me.pfdai.volleyext;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;

import java.util.Map;

import me.pfdai.volley.VolleyManager;
import me.pfdai.volley.VolleyHandler;
import me.pfdai.volley.VolleyMultipartParams;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // request string data from server by GET request
        VolleyManager.getInstance().requestStringGet("YOUR_URL", new VolleyHandler<String>() {
            @Override
            protected void onSuccess(String response) {
                // process response here
            }

            @Override
            protected void onFailure(int errorCode) {
                // process failure situation
            }
        });

        // request string data from server by POST request
        Map<String, String> params = new ArrayMap<>();
        params.put("Test", "Value");

        VolleyManager.getInstance().requestStringPost("YOUR_URL", params, new VolleyHandler<String>() {
            @Override
            protected void onSuccess(String response) {
                // process response here
            }

            @Override
            protected void onFailure(int errorCode) {
                // process failure situation
            }
        });


        // request bytes array from server
        VolleyManager.getInstance().requestBytePost("YOUR_URL", params, new VolleyHandler<byte[]>() {
            @Override
            protected void onSuccess(byte[] response) {
                // process bytes response here
            }

            @Override
            protected void onFailure(int errorCode) {
                // process failure situation
            }
        });


        // upload bytes array to server
        VolleyMultipartParams multipartParams = new VolleyMultipartParams();
        byte[] testBytes = {'1', '2', '3'};
        multipartParams.put("TEST_BYTES", testBytes);
        multipartParams.put("TEST_STRING", "");

        VolleyManager.getInstance().requestMultipartPost("YOUR_URL", multipartParams, new VolleyHandler() {
            @Override
            protected void onSuccess(Object response) {
                // process response here
            }

            @Override
            protected void onFailure(int errorCode) {
                // process failure situation
            }
        });

    }

}
