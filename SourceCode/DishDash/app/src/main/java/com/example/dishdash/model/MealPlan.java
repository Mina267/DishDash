package com.example.dishdash.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mealplan_table")
public class MealPlan {

    @PrimaryKey(autoGenerate = true)
    public int idDay;
    public String dayName;



    public int getIdDay() {
        return idDay;
    }

    public String getDayName() {
        return dayName;
    }

    public void setIdDay(int idDay) {
        this.idDay = idDay;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }
}


