# VolleyExt
Extend the function of volley. Encapsulation the post method and cache strategy to the volley library.

### Features
Add post request to the api of volley;

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


### License

    Copyright 2015 Ledong, Inc.
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

