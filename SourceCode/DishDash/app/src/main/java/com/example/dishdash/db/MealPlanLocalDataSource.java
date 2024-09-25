package com.example.dishdash.db;

import androidx.lifecycle.LiveData;

import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealPlan;
import com.example.dishdash.model.MealPlanJunction;

import java.util.List;

public interface MealPlanLocalDataSource {
    LiveData<List<MealPlan>> getStoredMeals();

    public LiveData<List<Meal>> getMealsOfTheDay(int day);

    void deleteMeal(MealPlan mealPlan);

    void insertPlanMealForDay(MealPlan mealPlan);

    public void insertMealDayJunction(MealPlanJunction mealPlanJunction);
}
