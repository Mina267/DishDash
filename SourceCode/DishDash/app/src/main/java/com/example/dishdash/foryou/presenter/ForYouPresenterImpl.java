package com.example.dishdash.foryou.presenter;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;


import com.example.dishdash.foryou.view.ForYouView;
import com.example.dishdash.model.Categories;
import com.example.dishdash.model.FilterMeals;
import com.example.dishdash.model.ListAllArea;
import com.example.dishdash.model.ListAllCategories;
import com.example.dishdash.model.ListAllIngredient;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepository;
import com.example.dishdash.network.NetworkConnectionStatus;
import com.example.dishdash.network.NetworkDelegate;

import java.util.ArrayList;
import java.util.List;

public class ForYouPresenterImpl implements NetworkDelegate, ForYouPresenter {

    private static final String TAG = "ForYouPresenterImpl";
    private ForYouView view;
    private MealRepository mealRepository;
    private static final int MAX_RANDOM_MEAL = 10;
    NetworkConnectionStatus connectionStatus;
    private Boolean startNetworkStatus = false;


    public ForYouPresenterImpl(ForYouView view, MealRepository mealRepository, NetworkConnectionStatus connectionStatus) {
        this.view = view;
        this.mealRepository = mealRepository;
        this.connectionStatus = connectionStatus;

    }


    public void startMonitoringNetwork() {
        if (!connectionStatus.isNetworkAvailable())
        {
            startNetworkStatus = true;
            view.onStartNoNetwork();
        }

        connectionStatus.registerNetworkCallback(new NetworkConnectionStatus.NetworkChangeListener() {
            @Override
            public void onNetworkAvailable() {
                if (startNetworkStatus)
                {
                    view.newtworkAvailable();
                    startNetworkStatus = false;

                }
            }

            @Override
            public void onNetworkLost() {
                view.networkLost();
            }
        });
    }




    public void stopMonitoringNetwork() {
        connectionStatus.unregisterNetworkCallback();
    }


    @Override
    public void getRandomProduct() {
        if (connectionStatus.isNetworkAvailable()) {
            for (int i = 0; i  < MAX_RANDOM_MEAL ; i++)
            {
                mealRepository.getOneRandomMeal(this);
            }
        }
    }

    @Override
    public void addToFavourite(Meal meal) {
        if (meal != null)
        {
            Log.i(TAG, "addToFavourite: " + meal.getStrMeal());
            mealRepository.insertMeal(meal);
        }
    }

    @Override
    public void getSavedMeals() {

        view.markSavedMeals(mealRepository.getStoredMeals());
    }

    @Override
    public void deleteMeal(Meal meal) { if (meal != null) {mealRepository.deleteMeal(meal);}}

    @Override
    public void getMealByName(String mealName) {
        if (connectionStatus.isNetworkAvailable()) {
            Log.i(TAG, "getMealByName: ");
            mealRepository.getMealsByFirstLetter('p', this);

        }
    }

    @Override
    public void getMealByArea(String area) {
        if (connectionStatus.isNetworkAvailable()) {
            Log.i(TAG, "getMealByArea: " + area);
            if (area != null)
            {
                mealRepository.getMealsByArea(area, this);
            }
        }
    }

    @Override
    public void getMealByCategory(String category) {
       if (connectionStatus.isNetworkAvailable()) {
            Log.i(TAG, "getMealByArea: " + category);
            if (category != null)
            {
                mealRepository.getMealsByName(category, this);
            }
        }
    }

    @Override
    public void onSuccessMealId(Meal meal) {
        if (meal != null)
        {
            view.showResultArea(meal);
        }
    }

    @Override
    public void onSuccessMeals(List<Meal> mealsList) {
        if (mealsList != null)
        {
            for (Meal meal : mealsList)
            {
                Log.i(TAG, "onSuccessMealsByFirstLetter: " + meal.getStrMeal());
            }
            view.showResultCategory(mealsList);
        }
    }

    @Override
    public void onSuccessMealsByFirstLetter(List<Meal> mealsList) {
        if (mealsList != null)
        {
            for (Meal meal : mealsList)
            {
                Log.i(TAG, "onSuccessMealsByFirstLetter: " + meal.getStrMeal());
            }
            view.showResultName(mealsList);
        }
    }

    @Override
    public void onSuccessRandomMeals(List<Meal> mealsList) {
        if (mealsList != null)
        {
            view.showData(mealsList.get(0));

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
        if (filterMealsList != null)
        {
            for (FilterMeals meal : filterMealsList)
            {
                Log.i(TAG, "onSuccessFilteredMeals: " + meal.getStrMeal());
                mealRepository.getMealById(meal.getIdMeal(), this);
            }
        }

    }
    @Override
    public void onSuccessIngredientImage(Bitmap bitmap, String ingredientName) {

    }



    @Override
    public void onFailureResult(String errorMsg) {
        Log.e(TAG, "Error: " + errorMsg);
    }
}
