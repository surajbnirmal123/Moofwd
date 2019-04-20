package com.macdevelopers.moofwd.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.macdevelopers.moofwd.R;
import com.macdevelopers.moofwd.Welcome.WelcomeActivity;
import com.macdevelopers.moofwd.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {

    ActivitySplashScreenBinding activitySplashScreenBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            activitySplashScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);

            Thread myThread = new Thread() {

                @Override
                public void run() {
                    try {
                        sleep(3000);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                initApp();
                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            myThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initApp() {

        try {

            Intent intent = new Intent(SplashScreenActivity.this, WelcomeActivity.class);
            startActivity(intent);
            finish();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
