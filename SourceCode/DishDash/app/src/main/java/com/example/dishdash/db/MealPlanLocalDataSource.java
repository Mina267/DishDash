package com.example.dishdash.db;

import androidx.lifecycle.LiveData;

import com.example.dishdash.model.MealPlan;

import java.util.List;

public interface MealPlanLocalDataSource {
    LiveData<List<MealPlan>> getStoredMeals();

    LiveData<List<MealPlan>> getStoredWeekMeals(String week);

    void deleteMeal(MealPlan mealPlan);

    void insertMeal(MealPlan mealPlan);
}
