package com.example.dishdash.searchresult.presenter;

import com.example.dishdash.model.Meal;

public interface SearchResultPresenter {
    void getMealByName(String mealName);

    /* For getting Meals and store it in database */
    void addToFavourite(Meal meal);
    void getSavedMeals();
    void deleteMeal(Meal meal);
}
