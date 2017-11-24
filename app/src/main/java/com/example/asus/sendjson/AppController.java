package com.example.asus.sendjson;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Asus on 11/23/2017.
 */

public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
    public static synchronized AppController getmInstance (){
        return mInstance;
    }
    public RequestQueue getRequestQueue (){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }
    public ImageLoader getImageLoader (){
        getRequestQueue();
        if(imageLoader == null){
            imageLoader = new ImageLoader(this.requestQueue, new BitmapCache());
        }
        return this.imageLoader;
    }
    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }
    public void cancelPendingRequest(Object tag){
        if(requestQueue != null){
            requestQueue.cancelAll(tag);
        }
    }
}
