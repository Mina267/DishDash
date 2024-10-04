package com.example.dishdash.foryou.view;

import androidx.lifecycle.LiveData;

import com.example.dishdash.model.Meal;

import java.util.List;

public interface ForYouView {
    void showData(Meal meal);
    void markSavedMeals(LiveData<List<Meal>> meals);
    void showResult(List<Meal> mealsList);
    void showErrMsg(String error);
}


