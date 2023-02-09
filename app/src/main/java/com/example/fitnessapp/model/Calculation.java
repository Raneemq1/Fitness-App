package com.example.fitnessapp.model;
import  com.example.fitnessapp.R;
import java.util.Random;

/**
 * vars 2numbers,result
 * 1-generate random numbers from 0-99
 * 2-generate result
 */
public class Calculation {
    private int num1;
    private int num2;
    private int ans;
    private int resStatus;
    private final int upper=20;
    /**
     * Locally choose num1,num2
     */
    public void generateRandom(){
        Random random = new Random();
        num1=random.nextInt(upper);
        num2=random.nextInt(upper);
    }
    /**
     * Locally choose res
     */
    public void getResult(){
        ans = num1 * num2;
        Random random = new Random();
        int bias;
        double rand =Math.random();
        if(rand<0.5){
            bias= random.nextInt(10);
            ans+=bias;
            resStatus=0;
        }
        else{
            resStatus=1;
        }

    }


    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public int getAns() {
        return ans;
    }

    public int getResStatus() {
        return resStatus;
    }

    public Calculation() {
    }
}