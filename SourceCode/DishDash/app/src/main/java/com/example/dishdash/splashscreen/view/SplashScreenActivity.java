package com.example.dishdash.splashscreen.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dishdash.MainActivity;
import com.example.dishdash.R;

public class SplashScreenActivity extends AppCompatActivity {
    private Handler handler;
    private Runnable runnable;
    private static final Double SPLASH_SCREEN_TIME_IN_SEC = 5.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        /* Time out for splash screen */
        handler.postDelayed(runnable, (int)(SPLASH_SCREEN_TIME_IN_SEC * 1000));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}