package com.example.dishdash.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mealplan_table",  primaryKeys = {"Date", "idMeal"})
public class MealPlan extends Meal {
    @NonNull
    String Date;
    public String getDate() {
        return Date;
    }


    public void setDate(String date) {
        Date = date;
    }
}
