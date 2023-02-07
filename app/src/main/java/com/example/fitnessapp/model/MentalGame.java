package com.example.fitnessapp.model;

import com.example.fitnessapp.R;
/**
 * Mental games names with thier images
 */
public class MentalGame {

    private String name;
    private int imageID;

    public static final MentalGame[] mental_games = {
            new MentalGame ("Calculations", R.drawable.calc),
            new MentalGame ("Find the difference", R.drawable.find),
            new MentalGame ("Puzzles", R.drawable.puzzle)

    };
    private MentalGame (String name, int imageID){
        this.name = name;
        this.imageID = imageID;
    }
    public String getName(){return name;}
    public int getImageID(){return imageID;}

}
