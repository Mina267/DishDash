package com.example.dishdash.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class MealsRoot {

    @SerializedName("meals")
    List<Meal> meals;


    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
    public List<Meal> getMeals() {
        return meals;
    }

}