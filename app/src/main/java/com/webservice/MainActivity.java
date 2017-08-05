package com.webservice;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.VolleyError;
import com.webservice.utils.ApiController;
import com.webservice.utils.ApiResponseListener;
import com.webservice.utils.Constants;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements ApiResponseListener {

    private ApiController apiController;
    private HashMap<String, String> params;
    private Context context;
    private Button btn_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        apiController = new ApiController(context, null);
        //if this used in fragment then pass like
//        apiController = new ApiController(context,MyFragment.this);

        params = new HashMap<>();

        btn_call = (Button) findViewById(R.id.btn_call);
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params.put("name", "name value");
                //make sure change Constants.BASE_URL and params your own
                apiController.actionCallWebService(Constants.BASE_URL, params);
            }
        });


    }

    @Override
    public void onSuccessResponse(String response, HashMap<String, String> hashMap) {
        //response here
    }

    @Override
    public void onErrorResponse(VolleyError error, HashMap<String, String> hashMap) {

    }
}
