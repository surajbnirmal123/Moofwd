package com.macdevelopers.moofwd.Utilities;

import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.image.ImageInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import androidx.annotation.Nullable;

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

    public static boolean isServiceRunning(Class<?> serviceClass, Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    /*public static void sendEmail(Context context) {

        try {

            String name = SharedPrefUtil.getUserLogin(context).getResult().get(0).getFname();
            String mobileNo = SharedPrefUtil.getUserLogin(context).getResult().get(0).getMobile();

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto: contact@motorbuddy.in"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Query from " + name + "-" + mobileNo);
            //emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Add Message here");
            context.startActivity(Intent.createChooser(emailIntent, "Motor Buddy"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public static void callToMobileNo(Context context) {

        try {

            String mobileNo = Constants.getMobileNo;

            Intent callIntent;

            if (!mobileNo.equalsIgnoreCase("")) {

                callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + mobileNo));
                context.startActivity(callIntent);

            } else {
                Toast.makeText(context, "Mobile no. not available!", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public void finishAllPreviousActivities(Context context) {

        try {

            Intent intent = new Intent(context, SplashScreenActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public static String parseDateFormat(String time, String inputPattern, String outputPattern) {

        String str = null;

        if (!time.isEmpty()) {
            //String inputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
            outputFormat.setTimeZone(TimeZone.getDefault());

            Date date = null;
            // String str = null;

            try {
                date = inputFormat.parse(time);
                str = outputFormat.format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            str = "";
        }

        return str;
    }

    public static void openSocialMediaLink(Context context, String link) {

        try {
            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            context.startActivity(myIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "No application can handle this request."
                    + " Please install a web browser", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public static DraweeController getFrescoImageController(Context context, String url/*, final ProgressBar progressBar*/) {

        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(
                    String id,
                    @Nullable ImageInfo imageInfo,
                    @Nullable Animatable anim) {
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {

            }

            @Override
            public void onFailure(String id, Throwable throwable) {
            }
        };

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setControllerListener(controllerListener)
                .setUri(url)
                // other setters
                .build();

        return controller;
    }

    public static GenericDraweeHierarchy getFrescoImageHierarchy(Context context) {

        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(context.getResources());
        GenericDraweeHierarchy hierarchy = builder
                .setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY)
                .build();

        return hierarchy;
    }
}
