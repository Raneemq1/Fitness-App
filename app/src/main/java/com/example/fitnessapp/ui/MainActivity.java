package com.example.fitnessapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.fitnessapp.R;

import pl.droidsonroids.gif.GifImageView;

/**
 * Splash Screen
 */
public class MainActivity extends AppCompatActivity {


    private GifImageView gifImageView;
    private Animation animation1;
    private Animation animation2;
    private TextView appName;
    private SharedPreferences prefs;//for read
    private String userEmail;
    private String userPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Handler handler = new Handler();
        animation1 = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        animation2 = AnimationUtils.loadAnimation(this, R.anim.slide_down);

        appName = findViewById(R.id.txt_splash);
        gifImageView = findViewById(R.id.img_splash);


        appName.startAnimation(animation1);
        gifImageView.startAnimation(animation2);


        /**
         * Post delayed
         * first param object from Runnable
         * second time
         */
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                    Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                    startActivity(intent);
                    finish();


            }

        }, 4000);
    }


}