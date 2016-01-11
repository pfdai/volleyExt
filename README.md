# VolleyExt
Extend the function of volley. Encapsulation the post method and cache strategy to the volley library.

### Features
Add Post request to the api of volley;
Add request to upload bytes array to server;
Add request to download bytes array from server;
Add memory cache and disk cache for get image from server;

### Usage
You should call  CacheConfig.initCacheConfig(Context) when your Application onCreate(). 

### Sample
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






