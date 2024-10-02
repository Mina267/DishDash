package com.example.dishdash.mealplan.presenter;

import androidx.lifecycle.LiveData;

import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealPlan;

import java.util.List;

public interface MealPlanPresenter {
    void deleteMealPlan(MealPlan mealPlan);
    void addToMealPlan(MealPlan mealPlan);

    LiveData<List<MealPlan>> getMealPlanByDate(String date);
}
