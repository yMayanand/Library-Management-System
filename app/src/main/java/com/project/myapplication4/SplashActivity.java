package com.project.myapplication4;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT=2000;
    // After completion of 2000 ms, the next activity will get started.

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,
                        StartActivity.class);
                //Intent is used to switch from one activity to another.

                startActivity(intent);
                //invoke the SecondActivity.

                finish();
                //the current activity will get finished.
            }
        }, SPLASH_SCREEN_TIME_OUT);

    }
}
