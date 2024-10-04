package com.example.dishdash.selectday.presenter;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.dishdash.foryou.presenter.ForYouPresenter;
import com.example.dishdash.foryou.view.ForYouView;
import com.example.dishdash.model.Categories;
import com.example.dishdash.model.FilterMeals;
import com.example.dishdash.model.ListAllArea;
import com.example.dishdash.model.ListAllCategories;
import com.example.dishdash.model.ListAllIngredient;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepository;
import com.example.dishdash.network.NetworkDelegate;
import com.example.dishdash.selectday.view.SelectDayView;

import java.util.ArrayList;
import java.util.List;

public class SelectDayPresenterImpl implements NetworkDelegate, SelectDayPresenter {

    private static final String TAG = "ForYouPresenterImpl";
    SelectDayView view;
    MealRepository mealRepository;

    public SelectDayPresenterImpl(SelectDayView view, MealRepository mealRepository) {
        this.view = view;
        this.mealRepository = mealRepository;
    }





    @Override
    public void addToMealPlan(Meal meal, String date) {
        mealRepository.insertPlanMealForDay(meal, date);
    }


    @Override
    public void onSuccessMealId(Meal meal) {

    }

    @Override
    public void onSuccessMeals(List<Meal> mealsList) {

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
        view.showErrMsg(errorMsg);
    }
}
