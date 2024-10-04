package com.example.dishdash.search.presenter;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.dishdash.model.Categories;
import com.example.dishdash.model.FilterMeals;
import com.example.dishdash.model.ListAllArea;
import com.example.dishdash.model.ListAllCategories;
import com.example.dishdash.model.ListAllIngredient;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepository;
import com.example.dishdash.network.NetworkDelegate;
import com.example.dishdash.search.view.SearchMealsView;

import java.util.ArrayList;
import java.util.List;

public class SearchPresenterImpl implements NetworkDelegate, SearchPresenter {
    private static final String TAG = "SearchPresenterImpl";
    SearchMealsView _view;
    MealRepository mealRepository;
    /* Meals List that fetched using Id according to the filtered type (Area, Ingredient, Category) */
    private final List<Meal> Meals = new ArrayList<>();
    /* List acquire id from to fetch them */
    List<FilterMeals> filterMealsList;
    private int fetchedCount = 0;



    public SearchPresenterImpl(SearchMealsView _view, MealRepository mealRepository) {
        this._view = _view;
        this.mealRepository = mealRepository;
    }


    @Override
    public void getMealCategories() {
        Log.i(TAG, "getMealCategories: ");
        mealRepository.getAllCategories(this);
    }

    @Override
    public void getMealAreas() {
        mealRepository.getAllAreas(this);
    }

    @Override
    public void getMealIngredients() {
        mealRepository.getAllIngredients(this);
    }


    public void getMealByArea(String area) {
        mealRepository.getMealsByArea(area, this);
    }

    @Override
    public void getMealByName(String mealName) {
        mealRepository.getMealsByName(mealName, this);
    }

    @Override
    public void getMealByIngredient(String ingredient) {
        mealRepository.getMealsByIngredient(ingredient,this);
    }

    @Override
    public void getMealByCategory(String category) {
        mealRepository.getMealsByCategory(category, this);
    }

    @Override
    public void onSuccessMealId(Meal meal) {
        Meals.add(meal);
        fetchedCount++;
        if (fetchedCount == filterMealsList.size()) {
            _view.showSearchResult(Meals);
        }

    }

    @Override
    public void onSuccessMeals(List<Meal> mealsList) {
        _view.showSearchResult(mealsList);

    }

    @Override
    public void onSuccessRandomMeals(List<Meal> mealsList) {

    }

    @Override
    public void onSuccessCategories(List<Categories> categoriesList) {
        // TO DO
        _view.showAllCategories(categoriesList);
    }

    @Override
    public void onSuccessCategoriesSimple(List<ListAllCategories> categoriesList) {

    }

    @Override
    public void onSuccessArea(List<ListAllArea> AreaList) {
        _view.showAllAreas(AreaList);
    }

    @Override
    public void onSuccessIngredients(List<ListAllIngredient> ingredientsList) {
        ArrayList<ListAllIngredient> newPartialList = new ArrayList<>(ingredientsList.subList(0, 20));

        _view.showAllIngredients(newPartialList);
    }

    @Override
    public void onSuccessFilteredMeals(List<FilterMeals> filterMealsList, String filterType) {
        /* Filtered meal get id from it and fetch them
         * Filtered meal list is repose from request to get id of meals according to the filtered type (Area, Ingredient, Category)
         */
        this.filterMealsList = filterMealsList;
        for (FilterMeals meal : filterMealsList) {
            mealRepository.getMealById(meal.getIdMeal(), this);
        }
    }

    @Override
    public void onSuccessIngredientImage(Bitmap bitmap, String ingredientName) {

    }



    @Override
    public void onFailureResult(String errorMsg) {
        fetchedCount++;
        if (filterMealsList != null && fetchedCount == filterMealsList.size()) {
            _view.showSearchResult(Meals);
        } else if (filterMealsList == null) {
            Log.e(TAG, "Error: filterMealsList is null: " + errorMsg);
            _view.showSearchResult(Meals);
        }
    }
}
