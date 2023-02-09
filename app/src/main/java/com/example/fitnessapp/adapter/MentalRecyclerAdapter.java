package com.example.fitnessapp.adapter;
import com.example.fitnessapp.R;
import com.example.fitnessapp.ui.CalculationActivity;
import com.example.fitnessapp.ui.DifferenceActivity;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MentalRecyclerAdapter extends RecyclerView.Adapter<MentalRecyclerAdapter.ViewHolder> {

    private String[] captions;
    private int[] imageIds;

    public MentalRecyclerAdapter(String[] captions, int[] imageIds){
        this.captions = captions;
        this.imageIds = imageIds;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.mental_item,parent, false);
        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.image);
        Drawable dr = ContextCompat.getDrawable(cardView.getContext(), imageIds[position]);
        imageView.setImageDrawable(dr);
        TextView txt = (TextView)cardView.findViewById(R.id.txtName);
        txt.setText(captions[position]);

        cardView.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                cardView.setCardBackgroundColor(Color.parseColor("#EAEAEA"));
                // context in the adapter >> holder.itemView.getContext()
                switch (position){
                    case 0:{
                        Intent intent=new Intent(holder.itemView.getContext(), CalculationActivity.class);
                        holder.itemView.getContext().startActivity(intent);
                        break;


                    }
                    case 1:{
                        Intent intent=new Intent(holder.itemView.getContext(), DifferenceActivity.class);
                        holder.itemView.getContext().startActivity(intent);
                        break;

                    }
                    case 2:{
                        //Intent intent=new Intent(holder.itemView.getContext(), PuzzleActivity.class);
                        //holder.itemView.getContext().startActivity(intent);
                        break;
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return captions.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        public ViewHolder(CardView cardView){
            super(cardView);
            this.cardView = cardView;
        }

    }
}
