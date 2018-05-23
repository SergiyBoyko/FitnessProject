package com.example.a38096.fitnessproject.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Serhii Boiko on 08.05.2018.
 */
public class Workout {
    @SerializedName("workoutId")
    @Expose
    private Long workoutId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("calories")
    @Expose
    private Integer calories;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("workoutDate")
    @Expose
    private Long workoutDate;

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Long getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(Long workoutDate) {
        this.workoutDate = workoutDate;
    }
}
