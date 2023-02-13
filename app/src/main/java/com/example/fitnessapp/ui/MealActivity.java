package com.example.fitnessapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fitnessapp.R;
import com.example.fitnessapp.model.Meal;
import com.google.gson.Gson;

public class MealActivity extends AppCompatActivity {

    private Meal meal;
    private TextView txtMealName;
    private TextView txtMealCat;
    private TextView txtMealArea;
    private TextView txtInstructions;
    private TextView txtIngredients;
    private ImageView imageView;
    private ImageView btnYoutube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        Intent intent = getIntent();

        txtMealName = findViewById(R.id.text_meal_name2);
        txtMealCat = findViewById(R.id.text_meal_cat2);
        txtMealArea = findViewById(R.id.text_meal_area2);
        txtInstructions = findViewById(R.id.text_instructions2);
        txtIngredients = findViewById(R.id.text_ingrediens2);
        imageView = findViewById(R.id.meal_image2);
//        btnYoutube = findViewById(R.id.btn_youtube_link);

        Gson json = new Gson();
        String mealJsonString = intent.getStringExtra("MEAL_JSON_STRING");
        meal = json.fromJson(mealJsonString,Meal.class);

        txtMealName.setText(meal.getName());
        txtMealCat.setText(meal.getCategory());
        txtMealArea.setText(meal.getArea());
        txtInstructions.setText(meal.getInstructions());
        String ingredients = "";
        for (int i = 0 ; i < meal.getIngredients().size() ; ++i){
            ingredients+= meal.getIngredients().get(i) + ":    " + meal.getIngredientsAmounts().get(i) + "\n";
        }
        txtIngredients.setText(ingredients);
        Glide.with(this).load(meal.getImgURL()).into(imageView);


    }
}