package com.example.dishdash.foryou.presenter;

import android.graphics.Bitmap;
import android.util.Log;


import com.example.dishdash.foryou.view.ForYouView;
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

public class ForYouPresenterImpl implements NetworkDelegate, ForYouPresenter {

    private static final String TAG = "ForYouPresenterImpl";
    private ForYouView view;
    private MealRepository mealRepository;
    private int randomRecipesCnt;
    private static final int MAX_RANDOM_MEAL = 10;
    private List<Meal> randomMealsList = new ArrayList<>();
    /* Meals List that fetched using Id according to the filtered type (Area, Ingredient, Category) */
    private final List<Meal> Meals = new ArrayList<>();


    public ForYouPresenterImpl(ForYouView view, MealRepository mealRepository) {
        this.view = view;
        this.mealRepository = mealRepository;
    }

    @Override
    public void getRandomProduct() {
        for (int i = 0; i  < MAX_RANDOM_MEAL ; i++)
        {
            mealRepository.getOneRandomMeal(this);
        }

    }

    @Override
    public void addToFavourite(Meal meal) {
        mealRepository.insertMeal(meal);
    }

    @Override
    public void addToMealPlan(Meal meal, String day) {
        mealRepository.insertPlanMealForDay(meal, day);
    }

    @Override
    public void getSavedMeals() {

        view.markSavedMeals(mealRepository.getStoredMeals());
    }

    @Override
    public void deleteMeal(Meal meal) {mealRepository.deleteMeal(meal);}

    @Override
    public void getMealByName(String mealName) {
        mealRepository.getMealsByName(mealName, this);
    }


    @Override
    public void onSuccessMealId(Meal meal) {

    }

    @Override
    public void onSuccessMeals(List<Meal> mealsList) {
         view.showResult(mealsList);
    }

    @Override
    public void onSuccessRandomMeals(List<Meal> mealsList) {
        Log.i(TAG, "onSuccessMeals: " + mealsList.get(0) + " randomRecipesCnt = "+ randomRecipesCnt);
        randomMealsList.add(mealsList.get(0));
        randomRecipesCnt++;
        if (randomRecipesCnt >= MAX_RANDOM_MEAL) {
            view.showData(randomMealsList);

        }
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
        Log.e(TAG, "Error: " + errorMsg);
    }
}
