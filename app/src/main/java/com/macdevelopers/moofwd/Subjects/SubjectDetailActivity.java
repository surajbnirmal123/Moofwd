package com.macdevelopers.moofwd.Subjects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.macdevelopers.moofwd.R;
import com.macdevelopers.moofwd.Utilities.Network.NetworkUtils;
import com.macdevelopers.moofwd.Utilities.Network.NetworkUtilsReceiver;
import com.macdevelopers.moofwd.databinding.ActivitySubjectDetailBinding;
import com.macdevelopers.moofwd.databinding.ActivitySubjectsBinding;

public class SubjectDetailActivity extends AppCompatActivity implements NetworkUtilsReceiver.NetworkResponseInt {

    private ActivitySubjectDetailBinding activitySubjectDetailBinding;
    private String TAG = this.getClass().getSimpleName();
    private NetworkUtilsReceiver networkUtilsReceiver;
    private String subjectName = "", subjectCode = "", period = "", lectureName = "", lectureEmail = "", subjectOverview = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

            activitySubjectDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_subject_detail);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.subject_detail);

            //TODO Register receiver
            networkUtilsReceiver = new NetworkUtilsReceiver(this);
            registerReceiver(networkUtilsReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

            Intent intent = getIntent();
            subjectName = intent.getStringExtra("subjectName");
            subjectCode = intent.getStringExtra("subjectCode");
            period = intent.getStringExtra("period");
            lectureName = intent.getStringExtra("lectureName");
            lectureEmail = intent.getStringExtra("lectureEmail");
            subjectOverview = intent.getStringExtra("subjectOverview");

            activitySubjectDetailBinding.subjectNameTV.setText(subjectName);
            activitySubjectDetailBinding.subjectCodeTV.setText(subjectCode);
            activitySubjectDetailBinding.periodTV.setText(period);
            activitySubjectDetailBinding.lectureNameTV.setText(lectureName);
            activitySubjectDetailBinding.emailTV.setText(lectureEmail);
            activitySubjectDetailBinding.subjectOverviewTV.setText(subjectOverview);

            activitySubjectDetailBinding.emailTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {

                        if (!lectureEmail.isEmpty()) {

                            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                            emailIntent.setData(Uri.parse("mailto:" + lectureEmail));
                            //emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Query from ");
                            //emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Add Message here");
                            SubjectDetailActivity.this.startActivity(Intent.createChooser(emailIntent, "Moofwd"));

                        } else {

                            Toast.makeText(SubjectDetailActivity.this, SubjectDetailActivity.this.getString(R.string.email_not_available), Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO Check Network Availability
    @Override
    public void NetworkOpen() {
    }

    @Override
    public void NetworkClose() {

        try {

            if (!NetworkUtils.isNetworkConnectionOn(SubjectDetailActivity.this)) {

                Snackbar snackbar = Snackbar
                        .make(activitySubjectDetailBinding.mainRelLayout, getResources().getString(R.string.error_message_no_internet), Snackbar.LENGTH_LONG)
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
            SubjectDetailActivity.this.unregisterReceiver(networkUtilsReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
