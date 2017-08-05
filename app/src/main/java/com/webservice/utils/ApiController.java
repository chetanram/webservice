package com.webservice.utils;

import android.app.Fragment;
import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.webservice.MyApplication;

import java.util.HashMap;
import java.util.Map;


public class ApiController {

    private Context context;
    public ApiResponseListener apiResponseListener;

    public ApiController(Context context, Fragment fragment) {
        this.context = context;
        if (fragment != null) {
            apiResponseListener = (ApiResponseListener) fragment;
        } else {
            apiResponseListener = (ApiResponseListener) context;
        }


    }

    public void actionCallWebService(String url, final HashMap<String, String> params) {
//        if (Common.isInternetAvailable(context)) {
//        Common.showProgressDialog(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Common.dismissProgressDialog();
                        apiResponseListener.onSuccessResponse(response, params);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Common.dismissProgressDialog();
                        apiResponseListener.onErrorResponse(error, params);
                        Log.e("Login_volley_error", "error in login " + error);

//
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                Constants.MY_API_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyApplication.getInstance().addToRequestQueue(stringRequest);
        /*} else {
            Common.showSnackBar(MainActivity.coordinatorLayout, "No Internet Connection",context);
        }*/
    }


}
