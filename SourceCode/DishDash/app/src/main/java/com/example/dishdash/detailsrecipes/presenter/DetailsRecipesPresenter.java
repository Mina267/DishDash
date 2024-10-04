package com.example.dishdash.detailsrecipes.presenter;

import com.example.dishdash.model.Ingredients;
import com.example.dishdash.model.Meal;

import java.util.List;

public interface DetailsRecipesPresenter {
    void getImgOfIngredient(Meal meal);

    void AddMealToFav(Meal meal);
    void getSavedMeals();
    void deleteMeal(Meal meal);



}
