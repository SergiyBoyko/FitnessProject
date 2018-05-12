package com.example.a38096.fitnessproject.model.entities;

/**
 * Created by Serhii Boiko on 08.05.2018.
 */
public class Workout {
    private String date;
    private String type;
    private String distance;
    private String duration;
    private String calories;

    public Workout(String date, String type, String distance, String duration, String calories) {
        this.date = date;
        this.type = type;
        this.distance = distance;
        this.duration = duration;
        this.calories = calories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }
}
