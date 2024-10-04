package com.example.dishdash.favrecipes.presenter;

import com.example.dishdash.model.Meal;

public interface FavRecipesPresenter {
    void deleteMeal(Meal meal);

    void getFavMeals();

    void addToFav(Meal meal);
}
