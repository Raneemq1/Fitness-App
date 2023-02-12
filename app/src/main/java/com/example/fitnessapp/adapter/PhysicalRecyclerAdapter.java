package com.example.fitnessapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.model.PhysicalExercise;
import com.example.fitnessapp.ui.PhysicalExerciseActivity;

import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;

public class PhysicalRecyclerAdapter extends RecyclerView.Adapter<PhysicalRecyclerAdapter.ViewHolder> {

    private final PhysicalExercise[] exercises;

    public PhysicalRecyclerAdapter(PhysicalExercise[] exercises) {
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.physical_item, parent, false);
        return new PhysicalRecyclerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PhysicalRecyclerAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        GifImageView exerciseGif = cardView.findViewById(R.id.physical_exercise_gif);
        exerciseGif.setImageResource(exercises[position].getGifID());
        TextView exerciseName = cardView.findViewById(R.id.physical_exercise_name);
        exerciseName.setText(exercises[position].getName());
        TextView exerciseDuration = cardView.findViewById(R.id.physical_exercise_duration);
        int min = (int) exercises[position].getDuration();
        int sec = (int) ((exercises[position].getDuration() - min) * 60);
        exerciseDuration.setText(String.format(Locale.getDefault(), "%02d : %02d", min, sec));
        cardView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), PhysicalExerciseActivity.class);
            intent.putExtra("EXERCISE_NAME", exercises[position].getName());
            intent.putExtra("EXERCISE_GIF", exercises[position].getGifID());
            intent.putExtra("EXERCISE_DURATION", exercises[position].getDuration());
            intent.putExtra("EXERCISE_STEPS", exercises[position].getSteps());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return exercises.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }

    }
}
