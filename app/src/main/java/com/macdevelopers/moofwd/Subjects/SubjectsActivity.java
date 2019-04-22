package com.macdevelopers.moofwd.Subjects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.macdevelopers.moofwd.Contacts.ContactsActivity;
import com.macdevelopers.moofwd.Contacts.ContactsListAdapter;
import com.macdevelopers.moofwd.Contacts.ContactsPOJO;
import com.macdevelopers.moofwd.R;
import com.macdevelopers.moofwd.Utilities.Constants;
import com.macdevelopers.moofwd.Utilities.IOUtils;
import com.macdevelopers.moofwd.Utilities.MyLog;
import com.macdevelopers.moofwd.Utilities.Network.NetworkUtils;
import com.macdevelopers.moofwd.Utilities.Network.NetworkUtilsReceiver;
import com.macdevelopers.moofwd.databinding.ActivityContactsBinding;
import com.macdevelopers.moofwd.databinding.ActivitySubjectsBinding;

import org.json.JSONObject;

public class SubjectsActivity extends AppCompatActivity implements NetworkUtilsReceiver.NetworkResponseInt {

    private ActivitySubjectsBinding activitySubjectsBinding;
    private String TAG = this.getClass().getSimpleName();
    private NetworkUtilsReceiver networkUtilsReceiver;
    private Gson gson;
    public static TextView valueTV;
    private SubjectsPOJO subjectsPOJO;
    private LinearLayoutManager linearLayoutManager;
    private SubjectsListAdapter subjectsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

            activitySubjectsBinding = DataBindingUtil.setContentView(this, R.layout.activity_subjects);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.subjects);

            //TODO Register receiver
            networkUtilsReceiver = new NetworkUtilsReceiver(this);
            registerReceiver(networkUtilsReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

            gson = new Gson();

            linearLayoutManager = new LinearLayoutManager(SubjectsActivity.this);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            activitySubjectsBinding.recyclerView.setLayoutManager(linearLayoutManager);
            activitySubjectsBinding.recyclerView.setHasFixedSize(true);

            activitySubjectsBinding.pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {

                    getSubjectsData();
                    activitySubjectsBinding.pullToRefresh.setRefreshing(false);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO Check Network Availability
    @Override
    public void NetworkOpen() {

        try {

            getSubjectsData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void NetworkClose() {

        try {

            if (!NetworkUtils.isNetworkConnectionOn(SubjectsActivity.this)) {

                Snackbar snackbar = Snackbar
                        .make(activitySubjectsBinding.mainRelLayout, getResources().getString(R.string.error_message_no_internet), Snackbar.LENGTH_LONG)
                        .setAction(getResources().getString(R.string.settings), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                            }
                        });

                //TODO Changing action button text color
                snackbar.setActionTextColor(Color.RED);

                snackbar.show();

                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getSubjectsData() {

        try {

            activitySubjectsBinding.progressBar.setVisibility(View.VISIBLE);

            MyLog.i(TAG + "Link#######", Constants.getSubjects);

            IOUtils ioUtils = new IOUtils();

            ioUtils.getCommonStringRequest(Constants.GET, TAG, SubjectsActivity.this, Constants.getSubjects, new IOUtils.VolleyCallback() {
                @Override
                public void onSuccess(String result) {

                    try {

                        MyLog.i(TAG + "Response#######", result);
                        getSubjectsDataResponse(result);

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

    public void getSubjectsDataResponse(String response) {

        try {

            activitySubjectsBinding.progressBar.setVisibility(View.GONE);
            JSONObject jsonObject = new JSONObject(response);
            String status = jsonObject.getString("status");
            valueTV = new TextView(SubjectsActivity.this);

            if (status.equalsIgnoreCase("ok")) {

                activitySubjectsBinding.noDataHolder.setVisibility(View.GONE);
                activitySubjectsBinding.dataHolder.setVisibility(View.VISIBLE);

                subjectsPOJO = gson.fromJson(response, SubjectsPOJO.class);

                activitySubjectsBinding.nameTV.setText(subjectsPOJO.getDetail().getList().get(0).getCourseName());
                activitySubjectsBinding.courseNameHolder.setVisibility(View.VISIBLE);

                subjectsListAdapter = new SubjectsListAdapter(subjectsPOJO.getDetail().getList(), SubjectsActivity.this);
                activitySubjectsBinding.recyclerView.setAdapter(subjectsListAdapter);
                subjectsListAdapter.notifyDataSetChanged();

            } else {

                activitySubjectsBinding.noDataHolder.setVisibility(View.GONE);
                activitySubjectsBinding.dataHolder.setVisibility(View.VISIBLE);
                valueTV.setText(R.string.error_no_record);
                valueTV.setGravity(Gravity.CENTER);
                valueTV.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                ((RelativeLayout) activitySubjectsBinding.noDataHolder).addView(valueTV);
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
            SubjectsActivity.this.unregisterReceiver(networkUtilsReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
