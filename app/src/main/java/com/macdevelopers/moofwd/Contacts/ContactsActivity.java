package com.macdevelopers.moofwd.Contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.macdevelopers.moofwd.Profile.ProfileActivity;
import com.macdevelopers.moofwd.Profile.ProfilePOJO;
import com.macdevelopers.moofwd.R;
import com.macdevelopers.moofwd.Utilities.Constants;
import com.macdevelopers.moofwd.Utilities.IOUtils;
import com.macdevelopers.moofwd.Utilities.MyLog;
import com.macdevelopers.moofwd.Utilities.Network.NetworkUtils;
import com.macdevelopers.moofwd.Utilities.Network.NetworkUtilsReceiver;
import com.macdevelopers.moofwd.databinding.ActivityContactsBinding;
import com.macdevelopers.moofwd.databinding.ActivityProfileBinding;

import org.json.JSONObject;

public class ContactsActivity extends AppCompatActivity implements NetworkUtilsReceiver.NetworkResponseInt{

    private ActivityContactsBinding activityContactsBinding;
    private String TAG = this.getClass().getSimpleName();
    private NetworkUtilsReceiver networkUtilsReceiver;
    public Gson gson;
    public static TextView valueTV;
    private ContactsPOJO contactsPOJO;
    public LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

            activityContactsBinding = DataBindingUtil.setContentView(this, R.layout.activity_contacts);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.contacts);

            //TODO Register receiver
            networkUtilsReceiver = new NetworkUtilsReceiver(this);
            registerReceiver(networkUtilsReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

            activityContactsBinding.progressBar.setVisibility(View.VISIBLE);

            linearLayoutManager = new LinearLayoutManager(ContactsActivity.this);
            linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
            activityContactsBinding.recyclerView.setLayoutManager(linearLayoutManager);
            activityContactsBinding.recyclerView.setHasFixedSize(true);

            gson = new Gson();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO Check Network Availability
    @Override
    public void NetworkOpen() {

        try {

            getContactsData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void NetworkClose() {

        try {

            if (!NetworkUtils.isNetworkConnectionOn(ContactsActivity.this)) {

                Snackbar snackbar = Snackbar
                        .make(activityContactsBinding.mainRelLayout, getResources().getString(R.string.error_message_no_internet), Snackbar.LENGTH_LONG)
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

    public void getContactsData() {

        try {

            MyLog.i(TAG + "Link#######", Constants.getContacts);

            IOUtils ioUtils = new IOUtils();

            ioUtils.getCommonStringRequest(Constants.GET, TAG, ContactsActivity.this, Constants.getContacts, new IOUtils.VolleyCallback() {
                @Override
                public void onSuccess(String result) {

                    try {

                        MyLog.i(TAG + "Response#######", result);
                        getContactsDataResponse(result);

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

    public void getContactsDataResponse(String response) {

        try {

            JSONObject jsonObject = new JSONObject(response);
            String status = jsonObject.getString("status");
            valueTV = new TextView(ContactsActivity.this);

            if (status.equalsIgnoreCase("ok")) {

                activityContactsBinding.noDataHolder.setVisibility(View.GONE);
                activityContactsBinding.dataHolder.setVisibility(View.VISIBLE);

                contactsPOJO = gson.fromJson(response, ContactsPOJO.class);

            } else {

                activityContactsBinding.noDataHolder.setVisibility(View.GONE);
                activityContactsBinding.dataHolder.setVisibility(View.VISIBLE);
                valueTV.setText(R.string.error_no_record);
                valueTV.setGravity(Gravity.CENTER);
                valueTV.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                ((RelativeLayout) activityContactsBinding.noDataHolder).addView(valueTV);
            }

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
            ContactsActivity.this.unregisterReceiver(networkUtilsReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
