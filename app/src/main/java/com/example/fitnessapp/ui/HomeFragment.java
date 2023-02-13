package com.example.fitnessapp.ui;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fitnessapp.R;
import com.example.fitnessapp.databinding.FragmentHomeBinding;
import com.example.fitnessapp.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class HomeFragment extends Fragment {

    float initialWeight;
    float height;
    float current;
    float target;
    TextView BMIIndex;
    ProgressBar progressBar;
    TextView progressTxt;
    Button editButton;
    EditText currentWeight;
    EditText targetWeight;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    ;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ;
    DatabaseReference userRef = database.getReference().child("users");
    ;
    String email = firebaseAuth.getCurrentUser().getEmail();
    ;
    private FragmentHomeBinding binding;
    private SharedPreferences prefs;//for read
    private String userId;
    private String gender;
    private String name;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        prefs = PreferenceManager.getDefaultSharedPreferences(root.getContext());
        setup(root);
        setBMIValue(current, height);
        updateProgressBar(initialWeight, current, target);
        return root;
    }

    private void setup(View root) {
        BMIIndex = root.findViewById(R.id.BMI_index);
        progressBar = root.findViewById(R.id.progress_bar);
        progressTxt = root.findViewById(R.id.progress_bar_txt);
        editButton = root.findViewById(R.id.editWeightAndTarget);
        currentWeight = root.findViewById(R.id.editWeight);
        targetWeight = root.findViewById(R.id.editTarget);
        initialWeight = prefs.getFloat("initial_weight", 100f);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    if (Objects.equals(email, s.getValue(User.class).getEmail())) {
                        User user = s.getValue(User.class);
                        current = user.getWeight();
                        currentWeight.setText(Float.toString(current));
                        target = user.getTarget();
                        targetWeight.setText(Float.toString(target));
                        height = user.getHeight();
                        updateProgressBar(initialWeight, current, target);
                        setBMIValue(current, height);
                        userId = user.getId();
                        gender = user.getGender();
                        name = user.getName();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        editButton.setOnClickListener(view -> {
            User user_updated = new User(
                    userId,
                    name,
                    email,
                    gender,
                    Float.parseFloat(currentWeight.getText().toString()),
                    Float.parseFloat(targetWeight.getText().toString()),
                    height);
            userRef.child(userId).setValue(user_updated).addOnCompleteListener(task ->
                    Toast.makeText(root.getContext(), "Updated", Toast.LENGTH_LONG).show());
            target = user_updated.getTarget();
            current = user_updated.getWeight();
            updateProgressBar(initialWeight, current, target);
            setBMIValue(current, height);
        });
    }

    private void updateProgressBar(double initial_weight, double current_weight, double target_weight) {
        double totalDifferance = initial_weight - target_weight;
        double currentDifferance = initial_weight - current_weight;
        double ratio = (currentDifferance / totalDifferance) * 100;
        if (ratio < 0) {
            if (current_weight < initial_weight && initial_weight < target_weight) {
                ratio = Math.abs(ratio);
                progressBar.setProgress((int) ratio);
                progressTxt.setText((int) ratio + "%");
            }
            progressBar.setProgress(0);
            progressTxt.setText("0%");
        } else {
            progressBar.setProgress((int) ratio);
            progressTxt.setText((int) ratio + "%");
        }
    }

    void setBMIValue(double weight, float height) {

        double BMI = weight / ((double) ((height * height) / 10000));
        if (BMI < 18.5) {
            BMIIndex.setTextColor(getResources().getColor(R.color.blue));
            BMIIndex.setText(String.format("%.2f", BMI) + " (Underweight)");
        } else if (BMI < 25) {
            BMIIndex.setTextColor(Color.GREEN);
            BMIIndex.setText(String.format("%.2f", BMI) + " (Normal)");
        } else if (BMI < 30) {
            BMIIndex.setTextColor(getResources().getColor(R.color.app_color));
            BMIIndex.setText(String.format("%.2f", BMI) + " (Overweight)");
        } else {
            BMIIndex.setTextColor(Color.RED);
            BMIIndex.setText(String.format("%.2f", BMI) + " (Obese)");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}