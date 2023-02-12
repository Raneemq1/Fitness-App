package com.example.fitnessapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;

public class PhysicalExerciseActivity extends AppCompatActivity {

    GifImageView exerciseGif;
    TextView exerciseName;
    TextView exerciseTime;
    ListView exerciseSteps;
    Button start;
    Button stop;
    Button reset;

    String name;
    double duration;
    int gif;
    String[] steps;
    int minutes;
    int seconds;

    Long startTimeInMills;
    CountDownTimer countDownTimer;
    boolean timerRunning;
    long timeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_exercise);
        setup();
        getExtras();
        setValues();
        updateTimerText();
        start.setOnClickListener(view -> {
            if (!timerRunning) {
                startTimer();
            }
        });
        stop.setOnClickListener(view -> {
            if (timerRunning) {
                pauseTimer();
            }
        });
        reset.setOnClickListener(view -> resetTimer());
    }

    private void resetTimer() {
        timeLeftInMillis = startTimeInMills;
        countDownTimer.cancel();
        timerRunning = false;
        updateTimerText();
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        timerRunning = false;
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
            }
        }.start();
        timerRunning = true;
    }

    private void updateTimerText() {
        int min = (int) (timeLeftInMillis / 1000) / 60;
        int sec = (int) (timeLeftInMillis / 1000) % 60;
        exerciseTime.setText(String.format(Locale.getDefault(), "%02d:%02d", min, sec));
    }

    private void setValues() {
        exerciseGif.setImageResource(gif);
        exerciseName.setText(name);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_view_item_txt, new ArrayList<>(Arrays.asList(steps)));
        exerciseSteps.setAdapter(arrayAdapter);
        exerciseTime.setText(String.format(Locale.getDefault(), "%02d : %02d", minutes, seconds));
    }

    private void getExtras() {
        Intent intent = getIntent();
        name = intent.getStringExtra("EXERCISE_NAME");
        duration = intent.getDoubleExtra("EXERCISE_DURATION", 1);
        gif = intent.getIntExtra("EXERCISE_GIF", 1);
        steps = intent.getStringArrayExtra("EXERCISE_STEPS");
        minutes = (int) duration;
        seconds = (int) ((duration - minutes) * 60);
        startTimeInMills = (minutes * 60 * 1000L) + (seconds * 1000L);
        timeLeftInMillis = startTimeInMills;
    }

    private void setup() {
        exerciseGif = findViewById(R.id.physical_exercise_gif);
        exerciseName = findViewById(R.id.physical_exercise_name);
        exerciseTime = findViewById(R.id.time);
        exerciseSteps = findViewById(R.id.steps_list_view);
        start = findViewById(R.id.start_btn);
        stop = findViewById(R.id.stop_btn);
        reset = findViewById(R.id.reset_btn);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer!=null)
            countDownTimer.cancel();
    }
}