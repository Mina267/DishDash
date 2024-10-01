package com.example.dishdash.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "weekplan_table",  primaryKeys = {"Date", "idMeal"})
public class WeekPlan extends Meal {
    @NonNull
    String Date;
    public String getDate() {
        return Date;
    }


    public void setDate(String date) {
        Date = date;
    }
}
