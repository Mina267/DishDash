package com.example.dishdash.model;

import androidx.lifecycle.LiveData;

import com.example.dishdash.network.NetworkDelegate;

import java.util.List;

public interface MealRepository {
    void getMealsByName(String mealName, NetworkDelegate networkCallback);

    void getMealsByFirstLetter(char firstLetter, NetworkDelegate networkCallback);

    void getMealById(String mealId, NetworkDelegate networkCallback);

    void getOneRandomMeal(NetworkDelegate networkCallback);

    void getAllCategories(NetworkDelegate networkCallback);

    void getAllCategoriesSimple(NetworkDelegate networkCallback);

    void getAllAreas(NetworkDelegate networkCallback);

    void getAllIngredients(NetworkDelegate networkCallback);

    void getMealsByCategory(String category, NetworkDelegate networkCallback);

    void getMealsByArea(String area, NetworkDelegate networkCallback);

    void getMealsByIngredient(String ingredient, NetworkDelegate networkCallback);

    LiveData<List<Meal>> getStoredMeals();

    void deleteMeal(Meal meal);

    void insertMeal(Meal meal);

    LiveData<List<MealPlan>> getMealsOfTheDay(String day);

    LiveData<List<MealPlan>> getStoredMealsPlan();
    void deleteMealPlan(MealPlan mealPlan);
    void insertPlanMealForDay(Meal meal, String date);

    LiveData<Boolean> isMealPlanExists(String idMeal, String date);

    void getIngredientImg(String ingredientName, NetworkDelegate networkCallback);
}