package com.macdevelopers.moofwd.Profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.macdevelopers.moofwd.R;
import com.macdevelopers.moofwd.Utilities.Constants;
import com.macdevelopers.moofwd.Utilities.IOUtils;
import com.macdevelopers.moofwd.Utilities.MyLog;
import com.macdevelopers.moofwd.Utilities.Network.NetworkUtils;
import com.macdevelopers.moofwd.Utilities.Network.NetworkUtilsReceiver;
import com.macdevelopers.moofwd.databinding.ActivityProfileBinding;

import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity implements NetworkUtilsReceiver.NetworkResponseInt {

    private ActivityProfileBinding activityProfileBinding;
    private String TAG = this.getClass().getSimpleName();
    private NetworkUtilsReceiver networkUtilsReceiver;
    public Gson gson;
    public static TextView valueTV;
    private ProfilePOJO profilePOJO;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

            activityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.profile);

            //TODO Register receiver
            networkUtilsReceiver = new NetworkUtilsReceiver(this);
            registerReceiver(networkUtilsReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

            gson = new Gson();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO Check Network Availability
    @Override
    public void NetworkOpen() {

        try {

            getProfileData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void NetworkClose() {

        try {

            if (!NetworkUtils.isNetworkConnectionOn(ProfileActivity.this)) {

                Snackbar snackbar = Snackbar
                        .make(activityProfileBinding.mainRelLayout, getResources().getString(R.string.error_message_no_internet), Snackbar.LENGTH_LONG)
                        .setAction(getResources().getString(R.string.settings), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                            }
                        });

                // Changing action button text color
                snackbar.setActionTextColor(Color.RED);

                snackbar.show();

                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getProfileData() {

        try {

            dialog = new ProgressDialog(ProfileActivity.this);
            dialog.setMessage(getResources().getString(R.string.fetching_data));
            dialog.setCancelable(false);
            dialog.show();

            MyLog.i(TAG + "Link#######", Constants.getProfile);

            IOUtils ioUtils = new IOUtils();

            ioUtils.getCommonStringRequest(Constants.GET, TAG, ProfileActivity.this, Constants.getProfile, new IOUtils.VolleyCallback() {
                @Override
                public void onSuccess(String result) {

                    try {

                        MyLog.i(TAG + "Response#######", result);
                        getProfileDataResponse(result);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new IOUtils.VolleyFailureCallback() {
                @Override
                public void onFailure(String result) {
                    MyLog.i(TAG + "onFailure", result);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getProfileDataResponse(String response) {

        try {

            JSONObject jsonObject = new JSONObject(response);
            String status = jsonObject.getString("status");
            valueTV = new TextView(ProfileActivity.this);

            if (status.equalsIgnoreCase("ok")) {

                activityProfileBinding.noDataHolder.setVisibility(View.GONE);
                activityProfileBinding.dataHolder.setVisibility(View.VISIBLE);

                profilePOJO = gson.fromJson(response, ProfilePOJO.class);

                if (getResources().getDisplayMetrics().widthPixels > getResources().getDisplayMetrics().heightPixels) {

                    Toast.makeText(this, "Screen switched to Landscape mode", Toast.LENGTH_SHORT).show();

                    displayProfileDataLandMode(profilePOJO);

                } else {

                    Toast.makeText(this, "Screen switched to Portrait mode", Toast.LENGTH_SHORT).show();

                    displayProfileDataPortMode(profilePOJO);
                }

            } else {

                activityProfileBinding.noDataHolder.setVisibility(View.GONE);
                activityProfileBinding.dataHolder.setVisibility(View.VISIBLE);
                valueTV.setText(R.string.error_no_record);
                valueTV.setGravity(Gravity.CENTER);
                valueTV.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                ((RelativeLayout) activityProfileBinding.noDataHolder).addView(valueTV);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayProfileDataPortMode(ProfilePOJO profilePOJO) {

        try {

            Detail detail = profilePOJO.getDetail();

            activityProfileBinding.nameUser.setText(detail.getName());
            activityProfileBinding.phoneNumber.setText(detail.getPhone());
            activityProfileBinding.email.setText(detail.getEmail());
            activityProfileBinding.dni.setText(String.valueOf(detail.getDni()));
            activityProfileBinding.career.setText(detail.getCareer());
            activityProfileBinding.campus.setText(detail.getCampus());
            activityProfileBinding.city.setText(detail.getCity());
            activityProfileBinding.address.setText(detail.getAddress());
            //activityProfileBinding.codeBar.setText(String.valueOf(detail.getCodebar()));
            String imageUrl = detail.getImage().toString();

            MyLog.d("IMAGEURL#$#$#$#$#$", imageUrl);

            Uri uri = Uri.parse("https://webdev.php.mooestro.com/assets/img/james.png");
            //Uri uri = Uri.parse(imageUrl);
            SimpleDraweeView draweeView = activityProfileBinding.imageView;
            draweeView.setImageURI(uri);
            dialog.dismiss();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayProfileDataLandMode(ProfilePOJO profilePOJO) {

        try {

            Detail detail = profilePOJO.getDetail();

            activityProfileBinding.nameUser.setText(detail.getName());
            activityProfileBinding.phoneNumber.setText(detail.getPhone());
            activityProfileBinding.career.setText(detail.getCareer());
            activityProfileBinding.email.setText(detail.getEmail());
            activityProfileBinding.codeBar.setText(String.valueOf(detail.getCodebar()));
            String imageUrl = detail.getImage().toString();

            MyLog.d("IMAGEURL#$#$#$#$#$", imageUrl);

            Glide.with(this).load("https://www.rottmair.de/profiles/Sebastian_Rottmair.jpg").into(activityProfileBinding.profileImage);

            Uri uri = Uri.parse("https://webdev.php.mooestro.com/assets/img/james.png");
            //Uri uri = Uri.parse(imageUrl);
            SimpleDraweeView draweeView = activityProfileBinding.imageView;
            draweeView.setImageURI(uri);
            dialog.dismiss();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            ProfileActivity.this.unregisterReceiver(networkUtilsReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
