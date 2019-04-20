package com.macdevelopers.moofwd.Dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.macdevelopers.moofwd.Profile.ProfileActivity;
import com.macdevelopers.moofwd.R;
import com.macdevelopers.moofwd.Utilities.Network.NetworkUtilsReceiver;
import com.macdevelopers.moofwd.Welcome.WelcomeActivity;
import com.macdevelopers.moofwd.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity /*implements NetworkUtilsReceiver.NetworkResponseInt*/{

    ActivityDashboardBinding activityDashboardBinding;
    private NetworkUtilsReceiver networkUtilsReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            activityDashboardBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);

            /*//TODO Register receiver
            networkUtilsReceiver = new NetworkUtilsReceiver(this);
            registerReceiver(networkUtilsReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));*/

            //TODO ClickListener for Profile Activity
            activityDashboardBinding.profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
            });

            //TODO ClickListener for Subjects Activity
            activityDashboardBinding.subjects.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DashboardActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }
            });

            //TODO ClickListener for Contacts Activity
            activityDashboardBinding.contacts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DashboardActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
