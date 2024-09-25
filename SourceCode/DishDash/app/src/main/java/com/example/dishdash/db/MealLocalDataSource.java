package com.example.dishdash.db;

import androidx.lifecycle.LiveData;

import com.example.dishdash.model.FavoriteMeal;
import com.example.dishdash.model.Meal;

import java.util.List;

public interface MealLocalDataSource {
    LiveData<List<Meal>> getStoredMeals();

    void deleteMeal(Meal meal);

    void insertMeal(Meal meal);
}
