package com.example.dishdash.searchresult.presenter;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.dishdash.favrecipes.view.FavRecipesView;
import com.example.dishdash.model.Categories;
import com.example.dishdash.model.FilterMeals;
import com.example.dishdash.model.ListAllArea;
import com.example.dishdash.model.ListAllCategories;
import com.example.dishdash.model.ListAllIngredient;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepository;
import com.example.dishdash.network.NetworkDelegate;
import com.example.dishdash.searchresult.view.SearchResultView;

import java.util.List;

public class SearchResultPresenterImpl implements SearchResultPresenter, NetworkDelegate {
    SearchResultView _view;
    MealRepository mealRepository;

    public SearchResultPresenterImpl(SearchResultView _view, MealRepository mealRepository) {
        this._view = _view;
        this.mealRepository = mealRepository;
    }




    @Override
    public void addToFavourite(Meal meal) {
        if (meal != null)
        {
            /* add to favorite data base */
            mealRepository.insertMeal(meal);
        }
    }

    @Override
    public void getSavedMeals() {
        /* Get the saved meals from the local data base to use with the favorite floating button */
        _view.markSavedMeals(mealRepository.getStoredMeals());
    }

    @Override
    public void deleteMeal(Meal meal) { if (meal != null) {mealRepository.deleteMeal(meal);}}


    @Override
    public void getMealByName(String mealName) {
        if (mealName != null || !mealName.isEmpty())
        {
            /* Get the meals from the remote data source by name */
            mealRepository.getMealsByName(mealName, this);
        }
    }

    @Override
    public void onSuccessMealId(Meal meal) {

    }

    @Override
    public void onSuccessMeals(List<Meal> mealsList) {
        if (mealsList != null || !mealsList.isEmpty())
        {
            /* Show the search result in recycler view */
            _view.showSearchResult(mealsList);
        }

    }

    @Override
    public void onSuccessRandomMeals(List<Meal> mealsList) {

    }

    @Override
    public void onSuccessCategories(List<Categories> categoriesList) {

    }

    @Override
    public void onSuccessCategoriesSimple(List<ListAllCategories> categoriesList) {

    }

    @Override
    public void onSuccessArea(List<ListAllArea> AreaList) {

    }

    @Override
    public void onSuccessIngredients(List<ListAllIngredient> ingredientsList) {

    }

    @Override
    public void onSuccessFilteredMeals(List<FilterMeals> filterMealsList, String filterType) {

    }

    @Override
    public void onSuccessIngredientImage(Bitmap bitmap, String ingredientName) {

    }

    @Override
    public void onFailureResult(String errorMsg) {
        _view.onFailureSearch(errorMsg);
    }
}
