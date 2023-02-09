package com.example.fitnessapp.ui;

import com.example.fitnessapp.R;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;
import  com.example.fitnessapp.model.Difference;

public class DifferenceActivity extends AppCompatActivity {


    private Button submit;
    private EditText edtDiffernce;
    private ImageView differenceImg;
    private ImageButton next;
    private Difference difference;
    private int index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difference);

        extracted();
        nextDispaly();

    }

    private void extracted() {

        submit = findViewById(R.id.submit);
        edtDiffernce = findViewById(R.id.edtDifference);
        differenceImg = findViewById(R.id.differenceImg);
        next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextDispaly();
            }
        });

    }

    private void nextDispaly(){
        int diff_num,img_after,img_before,len;
        len=Difference.differences.length;
        Random random = new Random();
        edtDiffernce.setText("");
        index=random.nextInt(len);
        diff_num=Difference.differences[index].getDifferenceNumber();
        img_after=Difference.differences[index].getImgAfter();
        img_before=Difference.differences[index].getImgBefore();

        Drawable drawable = getResources().getDrawable(img_before);
        differenceImg.setImageDrawable(drawable);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtDiffernce.getText().toString().isEmpty()){
                    Toast.makeText(DifferenceActivity.this,"Make sure to fill the number",Toast.LENGTH_LONG).show();
                }
                else {
                    Drawable drawable = getResources().getDrawable(img_after);
                    if (Integer.parseInt(edtDiffernce.getText().toString()) == diff_num) {
                        Toast.makeText(DifferenceActivity.this, "True", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(DifferenceActivity.this, "False", Toast.LENGTH_LONG).show();
                    }
                    differenceImg.setImageDrawable(drawable);
                }
            }
        });


        if(index>Difference.differences.length-1){
            index=0;
        }

    }
}