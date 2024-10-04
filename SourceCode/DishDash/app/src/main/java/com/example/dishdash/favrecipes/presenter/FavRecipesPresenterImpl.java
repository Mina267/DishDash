package com.example.dishdash.favrecipes.presenter;

import com.example.dishdash.favrecipes.view.FavRecipesView;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepository;

public class FavRecipesPresenterImpl implements FavRecipesPresenter {

    FavRecipesView _view;
    MealRepository mealRepository;

    public FavRecipesPresenterImpl(FavRecipesView _view, MealRepository mealRepository) {
        this._view = _view;
        this.mealRepository = mealRepository;
    }

    @Override
    public void deleteMeal(Meal meal) {mealRepository.deleteMeal(meal);}

    @Override
    public void getFavMeals() {_view.showData(mealRepository.getStoredMeals());}

    @Override
    public void addToFav(Meal meal) {mealRepository.insertMeal(meal);}
}
