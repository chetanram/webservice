package com.webservice.utils;

import com.android.volley.VolleyError;

import java.util.HashMap;


public interface ApiResponseListener {

    void onSuccessResponse(String response, HashMap<String, String> hashMap);
    void onErrorResponse(VolleyError error, HashMap<String, String> hashMap);

}
