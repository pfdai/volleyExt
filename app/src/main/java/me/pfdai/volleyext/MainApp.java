package me.pfdai.volleyext;

import android.app.Application;

import me.pfdai.volley.CacheConfig;

/**
 * Created by daipengfei on 16/1/11.
 */
public class MainApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // init the sContext
        CacheConfig.initCacheConfig(getApplicationContext());
    }
}
