package com.example.dishdash.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilterMealsRoot {

    @SerializedName("meals")
    List<FilterMeals> meals;


    public void setMeals(List<FilterMeals> meals) {
        this.meals = meals;
    }
    public List<FilterMeals> getMeals() {
        return meals;
    }

}
