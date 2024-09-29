package com.example.dishdash.ingredients.presenter;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.dishdash.ingredients.view.IngredientsMealsView;
import com.example.dishdash.model.Categories;
import com.example.dishdash.model.FilterMeals;
import com.example.dishdash.model.ListAllArea;
import com.example.dishdash.model.ListAllCategories;
import com.example.dishdash.model.ListAllIngredient;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepository;
import com.example.dishdash.network.NetworkDelegate;

import java.util.ArrayList;
import java.util.List;

public class IngredientsPresenterImpl implements NetworkDelegate, IngredientsPresenter {
    private static final String TAG = "ingredientsPresenterImp";
    IngredientsMealsView _view;
    MealRepository mealRepository;

    private final List<Meal> Meals = new ArrayList<>();
    List<FilterMeals> filterMealsList;
    private int fetchedCount = 0;



    public IngredientsPresenterImpl(IngredientsMealsView _view, MealRepository mealRepository) {
        this._view = _view;
        this.mealRepository = mealRepository;
    }





    @Override
    public void getMealIngredients() {
        mealRepository.getAllIngredients(this);
    }




    @Override
    public void getMealByIngredient(String ingredient) {
        mealRepository.getMealsByIngredient(ingredient,this);
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

        _view.showAllIngredients(ingredientsList);
    }

    @Override
    public void onSuccessFilteredMeals(List<FilterMeals> filterMealsList) {
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
