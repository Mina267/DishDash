package com.example.dishdash.db;

import androidx.lifecycle.LiveData;

import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealPlan;

import java.util.List;

public interface MealPlanLocalDataSource {
    LiveData<List<MealPlan>> getStoredMeals();

    LiveData<List<MealPlan>> getMealsOfTheDay(String date);

    void deleteMeal(MealPlan mealPlan);

    void insertPlanMealForDay(Meal meal, String date);
}
