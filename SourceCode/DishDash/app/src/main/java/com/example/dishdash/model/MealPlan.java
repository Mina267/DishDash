package com.example.dishdash.model;

import androidx.room.Entity;

@Entity(tableName = "MealPlan_table")
public class MealPlan extends Meal{



    private String week;

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }






}
