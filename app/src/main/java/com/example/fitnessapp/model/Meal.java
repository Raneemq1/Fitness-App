package com.example.fitnessapp.model;

import java.util.ArrayList;

public class Meal {
    private String name;
    private String category;
    private String area;
    private String instructions;
    private String imgURL;
    private String videoLink;
    private ArrayList<String> ingredients;
    private ArrayList<String> ingredientsAmounts;


    public Meal() {
    }

    public Meal(String name, String category, String area, String instructions, String imgURL, String videoLink, ArrayList<String> ingredients, ArrayList<String> ingredientsAmounts) {
        this.name = name;
        this.category = category;
        this.area = area;
        this.instructions = instructions;
        this.imgURL = imgURL;
        this.videoLink = videoLink;
        this.ingredients = ingredients;
        this.ingredientsAmounts = ingredientsAmounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getIngredientsAmounts() {
        return ingredientsAmounts;
    }

    public void setIngredientsAmounts(ArrayList<String> ingredientsAmounts) {
        this.ingredientsAmounts = ingredientsAmounts;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", area='" + area + '\'' +
                ", instructions='" + instructions + '\'' +
                ", imgURL='" + imgURL + '\'' +
                ", videoLink='" + videoLink + '\'' +
                ", ingredients=" + ingredients +
                ", ingredientsAmounts=" + ingredientsAmounts +
                '}';
    }
}
