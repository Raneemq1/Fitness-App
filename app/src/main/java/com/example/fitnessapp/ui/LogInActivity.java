package com.example.fitnessapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pl.droidsonroids.gif.GifImageView;

public class LogInActivity extends AppCompatActivity {
    private Button login;
    private Button signup;
    private EditText login_email;
    private EditText login_pass;
    private  FirebaseAuth mAuth;
    private SharedPreferences prefs;//for read
    private  SharedPreferences.Editor editor;//for write
    private  String email;
    private  String pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        extracted();
        setUpPrefs();


    }

    private void setUpPrefs(){
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor= prefs.edit();
    }
    private void extracted() {
        login=findViewById(R.id.login);
        login_email=findViewById(R.id.login_email);
        login_pass=findViewById(R.id.login_pass);
        signup=findViewById(R.id.login_signup);
        mAuth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             checkValid();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LogInActivity.this,SignUpActivity.class);
                startActivity(intent);

            }
        });
    }


private void checkValid(){
   email=login_email.getText().toString();
    pass=login_pass.getText().toString();
        if(email.isEmpty()||pass.isEmpty()){
            Toast.makeText(this, "Fill information", Toast.LENGTH_SHORT).show();
        }
        else{
            login(email,pass);
        }
}

private void login(String email,String pass){

        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    saveEmail();
                    Intent intent=new Intent(LogInActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
                else{
                    Toast.makeText(LogInActivity.this, "Incorrect email or pass", Toast.LENGTH_SHORT).show();
                }
            }
        });
}

private void login_auto(String email,String pass){

        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    saveEmail();
                    Intent intent=new Intent(LogInActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();

                }

            }
        });

}

private void saveEmail(){
    editor.putString("email",email);
    editor.putString("pass",pass);
    editor.commit();
}

}