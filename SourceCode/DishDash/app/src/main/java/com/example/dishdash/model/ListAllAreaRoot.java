package com.example.dishdash.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ListAllAreaRoot {

    @SerializedName("meals")
    List<ListAllArea> listAllAreaMeals;


    public void setMeals(List<ListAllArea> listAllAreaMeals) {
        this.listAllAreaMeals = listAllAreaMeals;
    }
    public List<ListAllArea> getMeals() {
        return listAllAreaMeals;
    }

}