package com.example.fitnessapp.model;
 import  com.example.fitnessapp.R;
/**
 * Two images and difference number
 */
public class Difference {
    private int differenceNumber;
    private int imgBefore;
    private int imgAfter;


    public final static Difference[] differences={
            new Difference(8, R.drawable.img1_a,R.drawable.img2_a),
            new Difference(7, R.drawable.img1_b,R.drawable.img2_b),
            new Difference(8, R.drawable.img1_c,R.drawable.img2_c),
            new Difference(8, R.drawable.img1_d,R.drawable.img2_d),
            new Difference(8, R.drawable.img1_e,R.drawable.img2_e)
    };


    public Difference(int differenceNumber, int imgBefore, int imgAfter) {
        this.differenceNumber = differenceNumber;
        this.imgBefore = imgBefore;
        this.imgAfter = imgAfter;
    }

    public int getDifferenceNumber() {
        return differenceNumber;
    }



    public int getImgBefore() {
        return imgBefore;
    }



    public int getImgAfter() {
        return imgAfter;
    }


}
