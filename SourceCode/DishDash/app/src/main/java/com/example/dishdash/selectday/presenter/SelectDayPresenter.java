package com.example.dishdash.selectday.presenter;

import androidx.lifecycle.LiveData;

import com.example.dishdash.model.Meal;

public interface SelectDayPresenter {
    void addToMealPlan(Meal meal, String date);
    LiveData<Boolean> isMealPlanExists(Meal meal, String date);
}
