package com.example.fitnessapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fitnessapp.R;
import com.example.fitnessapp.model.Meal;
import com.example.fitnessapp.ui.MealActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MealRecyclerAdapter extends RecyclerView.Adapter<MealRecyclerAdapter.ViewHolder>{

    ArrayList<Meal> meals;
    Context context;

    public MealRecyclerAdapter(ArrayList<Meal> meals, Context context) {
        this.meals = meals;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_item, parent, false);
        return new MealRecyclerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        Meal meal = meals.get(position);
        TextView txtName = (TextView)cardView.findViewById(R.id.text_meal_name);
        TextView txtCategory = (TextView)cardView.findViewById(R.id.text_meal_category);
        TextView txtArea = (TextView)cardView.findViewById(R.id.text_meal_area);
        ImageView image = (ImageView) cardView.findViewById(R.id.meal_image);
        txtName.setText(meal.getName());
        txtCategory.setText(meal.getCategory());
        txtArea.setText(meal.getArea());
        Glide.with(context).load(meal.getImgURL()).into(image);
        Gson json = new Gson();
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mealJsonString = json.toJson(meal);
                Intent intent = new Intent(holder.itemView.getContext(), MealActivity.class);
                intent.putExtra("MEAL_JSON_STRING", mealJsonString);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }

    }
}
