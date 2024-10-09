package com.example.dishdash.foryou.view;

import androidx.lifecycle.LiveData;

import com.example.dishdash.model.Meal;

import java.util.List;

public interface ForYouView {
    void showData(Meal meal);
    void markSavedMeals(LiveData<List<Meal>> meals);
    void showResultName(List<Meal> mealsList);
    void showResultArea(Meal meal);
    void showResultCategory(List<Meal> mealsList);

    void showErrMsg(String error);

    void onStartNoNetwork();
    void newtworkAvailable();
    void networkLost();
}


