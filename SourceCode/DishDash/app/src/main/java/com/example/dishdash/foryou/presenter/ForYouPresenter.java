package com.example.dishdash.foryou.presenter;

import com.example.dishdash.model.Meal;

public interface ForYouPresenter {
    void getRandomProduct();
    void addToFavourite(Meal meal);
    void addToMealPlan(Meal meal, String day);
    void getSavedMeals();
    void deleteMeal(Meal meal);
    public void getMealByName(String mealName);


}
