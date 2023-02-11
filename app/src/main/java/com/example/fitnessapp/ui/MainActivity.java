package com.example.fitnessapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import pl.droidsonroids.gif.GifImageView;

/**
 * Splash Screen
 */
public class MainActivity extends AppCompatActivity {


    private GifImageView gifImageView;
    private Animation animation1;
    private Animation animation2;
    private TextView appName;
    private FirebaseAuth mAuth;
    private SharedPreferences prefs;//for read
    private String userEmail;
    private String userPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        mAuth = FirebaseAuth.getInstance();
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
                if (autoLogIn()) {
                    moveToHome();
                } else {
                    Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                    startActivity(intent);
                    finish();
                }

            }

        }, 4000);
    }

    private boolean autoLogIn() {

        userEmail = prefs.getString("email", "");
        Log.d("Ran", userEmail);
        userPass = prefs.getString("pass", "");
        if (!userEmail.isEmpty() && !userPass.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private void moveToHome() {
        mAuth.signInWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();

                }

            }
        });
    }
}