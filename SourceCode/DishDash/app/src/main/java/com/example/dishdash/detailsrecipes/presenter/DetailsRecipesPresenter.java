package com.example.dishdash.detailsrecipes.presenter;

import com.example.dishdash.model.Meal;

public interface DetailsRecipesPresenter {
    void AddMealToFav(Meal meal);
    void getImgOfIngredient(String IngredientName);
}
