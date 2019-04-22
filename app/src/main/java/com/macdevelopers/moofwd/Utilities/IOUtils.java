package com.macdevelopers.moofwd.Utilities;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Suraj Nirmal
 */

public class IOUtils {

    //Volley JSON Object Request
    public void sendCommonJSONObjectRequest(int RequestType, String API_Tag, final Context context, String url, JSONObject jsonObject, final VolleyCallback callback, final VolleyFailureCallback failureCallback) {

        final Map<String, String> paramsHeaders = new HashMap<String, String>();
        paramsHeaders.put("X-App-Key", "95d7e28234ce4318ac6a732a38bf659f1f431e865ed7c789d35854b9b2468321");

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(RequestType,
                url, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response.toString());
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                try {
                    /*String json = null;
                    NetworkResponse response = error.networkResponse;
                    json = new String(response.data);
                    MyLog.d("Error", json);

                    failureCallback.onFailure(json);*/

                    error.printStackTrace();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                return paramsHeaders;
            }
        };

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        //Adding request to request queue
        MyApplication.getInstance().addRequestToQueue(jsonObjReq, API_Tag);

    }

    //Volley JSON Array Request
    public void sendCommonJSONArrayRequest(int RequestType, String API_Tag, final Context context, String url, JSONArray jsonArray, final VolleyCallback callback, final VolleyFailureCallback failureCallback) {

        final Map<String, String> paramsHeaders = new HashMap<String, String>();
        paramsHeaders.put("X-App-Key", "95d7e28234ce4318ac6a732a38bf659f1f431e865ed7c789d35854b9b2468321");

        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                RequestType,
                url,
                jsonArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        callback.onSuccess(String.valueOf(response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when error occurred
                        failureCallback.onFailure("");
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                return paramsHeaders;
            }
        };

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        //Adding request to request queue
        MyApplication.getInstance().addRequestToQueue(jsonArrayRequest, API_Tag);
    }

    // Volley String Get Request
    public void getCommonStringRequest(int RequestType, String API_Tag, final Context context, String url, final VolleyCallback callback, final VolleyFailureCallback failureCallback) {

        StringRequest stringRequest = new StringRequest(RequestType, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //MyLog.d("Response", response.toString());
                callback.onSuccess(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MyLog.d("Error", error.toString());
                failureCallback.onFailure("");
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyApplication.getInstance().addRequestToQueue(stringRequest, API_Tag);
    }


    public interface VolleyFailureCallback {
        void onFailure(String result);
    }

    public interface VolleyCallback {
        void onSuccess(String result);
    }
}
