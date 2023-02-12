package com.example.fitnessapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.R;
import com.example.fitnessapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private Button signup;
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText weight;
    private EditText height;
    private EditText target;
    private RadioGroup gender;
    private SharedPreferences.Editor editor;//for write
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        extracted();
    }

    private void extracted() {
        signup = findViewById(R.id.signup);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        target = findViewById(R.id.target);
        gender = findViewById(R.id.gender);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("users");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check validity of entries
                if (checkValid()) {
                    //register
                    authenticate();

                }

            }
        });
    }

    private boolean checkValid() {
        if (name.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty() ||
                weight.getText().toString().isEmpty() || height.getText().toString().isEmpty() || target.getText().toString().isEmpty() ||
                gender.getCheckedRadioButtonId() == -1 || Float.parseFloat(weight.getText().toString()) > 200.00 ||
                Float.parseFloat(target.getText().toString()) > 200.00 || Float.parseFloat(height.getText().toString()) > 200.00) {
            Toast.makeText(this, "Fill information correctly please", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method authenticate if the user email found before or not
     * Check the format of email
     * Check the length of password
     */
    private void authenticate() {
        String user_email = email.getText().toString();
        String user_pass = password.getText().toString();
        mAuth.createUserWithEmailAndPassword(user_email, user_pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    saveUserData();
                    Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void saveUserData() {
        String user_id = ref.push().getKey().toString();
        String user_name = name.getText().toString();
        String user_email = email.getText().toString();
        String user_gender = "";
        int selectedRadioButtonId = gender.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            user_gender = selectedRadioButton.getText().toString();
        }
        float user_weight = Float.parseFloat(weight.getText().toString());
        float user_target = Float.parseFloat(target.getText().toString());
        float user_height = Float.parseFloat(height.getText().toString());
        editor.putFloat("initial_weight", user_weight);
        editor.commit();
        User user = new User(user_id, user_name, user_email, user_gender, user_weight, user_target, user_height);
        ref.child(user_id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(SignUpActivity.this, "Enjoy joining us", Toast.LENGTH_SHORT).show();
            }
        });

    }
}