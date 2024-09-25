package com.example.dishdash.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "plan_junction_table", primaryKeys = {"idDay", "idMeal"})
public class MealPlanJunction {
    public int idDay;
    @NonNull
    public String idMeal;

    public MealPlanJunction(int idDay, String idMeal) {
        this.idDay = idDay;
        this.idMeal = idMeal;
    }

    public int getIdDay() {
        return idDay;
    }

    @NonNull
    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(@NonNull String idMeal) {
        this.idMeal = idMeal;
    }

    public void setIdDay(int idDay) {
        this.idDay = idDay;
    }
}
