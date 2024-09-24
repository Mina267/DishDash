package com.example.dishdash.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

   
public class ListAllCategoriesRoot {

   @SerializedName("meals")
   List<ListAllCategories> meals;


    public void setMeals(List<ListAllCategories> meals) {
        this.meals = meals;
    }
    public List<ListAllCategories> getMeals() {
        return meals;
    }
    
}