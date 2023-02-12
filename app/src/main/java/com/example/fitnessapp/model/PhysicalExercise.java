package com.example.fitnessapp.model;

import com.example.fitnessapp.R;

public class PhysicalExercise {
    public static final PhysicalExercise[] physicalExercises = {
            new PhysicalExercise("Push Up", R.drawable.physicalexercise_pushup, 1.5,
                    new String[]{
                            "1) Start down on all fours with your hands just wider than your shoulders.",
                            "2) Extend your legs so they form a straight line with your back.",
                            "3) Lower yourself to the floor until your elbows are at 90 degrees.",
                            "4) Push yourself back up until your arms are straight.",
                            "5) Try to do as many push-ups as you can with good form."
                    }),
            new PhysicalExercise("Jumping Jack", R.drawable.physicalexercise_jumpingjack, 2,
                    new String[]{
                            "1) Stand up straight, hold your arms at your sides, and stand with your feet shoulder-width apart.",
                            "2) Jump and extend your arms overhead.",
                            "3) Extend your legs.",
                            "4) Land in the starting position.",
                            "5) Repeat as needed."
                    }),
            new PhysicalExercise("Sit Up", R.drawable.physicalexercise_situp, 3,
                    new String[]{
                            "1) Have your knees bent and the balls of your feet and heels placed flat on the ground.",
                            "2) Place your hands on opposing shoulders, so that your arms are crossed over your chest, or behind your head.",
                            "3) Tighten your abdominal muscles gently by drawing in your belly button to your spine. ",
                            "4) Keeping your heels on the ground and your toes flat to the ground, slowly and gently lift your head first, followed by your shoulder blades.",
                            "5) Hold the position for a second.",
                            "6) Repeat steps 3-5 for the remainder of the exercise."
                    })
    };
    private final String name;
    private final int gifID;
    private final double duration;
    private final String[] steps;

    private PhysicalExercise(String name, int gifID, double duration, String[] steps) {
        this.name = name;
        this.gifID = gifID;
        this.duration = duration;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public int getGifID() {
        return gifID;
    }

    public double getDuration() {
        return duration;
    }

    public String[] getSteps() {
        return steps;
    }
}
