package com.example.dishdash.model;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListAllIngredientRoot {

    @SerializedName("meals")
    List<ListAllIngredient> meals;


    public void setMeals(List<ListAllIngredient> meals) {
        this.meals = meals;
    }
    public List<ListAllIngredient> getMeals() {
        return meals;
    }
}
