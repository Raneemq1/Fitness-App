package com.example.fitnessapp.model;

import com.example.fitnessapp.R;

/**
 * Mental games names with thier images
 */
public class MentalGame {

    public static final MentalGame[] mental_games = {
            new MentalGame("Calculations", R.drawable.calc),
            new MentalGame("Find the difference", R.drawable.find),
            new MentalGame("Map", R.drawable.map)

    };
    private String name;
    private int imageID;

    private MentalGame(String name, int imageID) {
        this.name = name;
        this.imageID = imageID;
    }

    public String getName() {
        return name;
    }

    public int getImageID() {
        return imageID;
    }

}
