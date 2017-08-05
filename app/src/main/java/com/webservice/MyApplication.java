package com.webservice;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

/**
 * Created by chetan on 5/8/17.
 */

public class MyApplication extends MultiDexApplication {

    public static final String TAG = MyApplication.class
            .getSimpleName();
    private RequestQueue queue;
    private static MyApplication mInstance;
    private Context context;
    private HashMap<String, String> params;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        mInstance = this;


    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {


        if (queue == null) {
            /*if(Common.httpclient==null)
            {
                Common.httpclient=new DefaultHttpClient();
                ClientConnectionManager mgr = Common.httpclient.getConnectionManager();

                HttpParams params = Common.httpclient.getParams();

                Common.httpclient = new DefaultHttpClient(new ThreadSafeClientConnManager(params,

                        mgr.getSchemeRegistry()), params);
                CookieStore cookieStore = new BasicCookieStore();
                Common.httpclient.setCookieStore( cookieStore );
            }
            HttpStack httpStack = new HttpClientStack( Common.httpclient );*/
            queue = Volley.newRequestQueue(getApplicationContext());
        }

        return queue;
    }

    public <T> void addToRequestQueue(com.android.volley.Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(com.android.volley.Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (queue != null) {
            queue.cancelAll(tag);
        }
    }

    public void stopQueue() {
        if (queue != null) {
            queue.stop();
        }
    }
}