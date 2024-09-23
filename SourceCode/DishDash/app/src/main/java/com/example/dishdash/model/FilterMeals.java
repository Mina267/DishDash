package com.example.dishdash.model;

import com.google.gson.annotations.SerializedName;

public class FilterMeals {
    @SerializedName("strMeal")
    String strMeal;

    @SerializedName("strMealThumb")
    String strMealThumb;

    @SerializedName("idMeal")
    String idMeal;


    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }
    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }
    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }
    public String getIdMeal() {
        return idMeal;
    }
}
