package com.example.dishdash.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


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