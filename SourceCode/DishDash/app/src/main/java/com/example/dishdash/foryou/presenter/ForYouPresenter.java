package com.example.dishdash.foryou.presenter;

import com.example.dishdash.model.Meal;

public interface ForYouPresenter {
    /* For getting random products */
    void getRandomProduct();
    /* For getting products by name for recommended Meals */
    void getMealByName(String mealName);

    /* For getting Meals and store it in database */
    void addToFavourite(Meal meal);
    void getSavedMeals();
    void deleteMeal(Meal meal);

    /* For monitoring network changes */
    void startMonitoringNetwork();
    void stopMonitoringNetwork();
}
