package com.example.fitnessapp.model;

public class User {
    private String id;
    private String name;
    private String email;
    private String gender;
    private float weight;
    private float target;
    private float height;

    public User() {
    }

    public User(String id, String name, String email, String gender, float weight, float target, float height) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.weight = weight;
        this.target = target;
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getTarget() {
        return target;
    }

    public void setTarget(float target) {
        this.target = target;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
