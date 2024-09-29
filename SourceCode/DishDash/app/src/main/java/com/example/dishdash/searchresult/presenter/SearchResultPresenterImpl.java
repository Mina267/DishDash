package com.example.dishdash.searchresult.presenter;

import com.example.dishdash.favrecipes.view.FavRecipesView;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepository;
import com.example.dishdash.searchresult.view.SearchResultView;

public class SearchResultPresenterImpl implements SearchResultPresenter {
    SearchResultView _view;
    MealRepository mealRepository;

    public SearchResultPresenterImpl(SearchResultView _view, MealRepository mealRepository) {
        this._view = _view;
        this.mealRepository = mealRepository;
    }


    @Override
    public void getFavProducts() {_view.showData(mealRepository.getStoredMeals());}

    @Override
    public void addToFav(Meal meal) {
        mealRepository.insertMeal(meal);
    }

}
