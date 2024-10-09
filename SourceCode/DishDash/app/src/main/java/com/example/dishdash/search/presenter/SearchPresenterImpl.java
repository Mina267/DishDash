package com.example.dishdash.search.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.dishdash.model.Categories;
import com.example.dishdash.model.FilterMeals;
import com.example.dishdash.model.ListAllArea;
import com.example.dishdash.model.ListAllCategories;
import com.example.dishdash.model.ListAllIngredient;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepository;
import com.example.dishdash.network.NetworkConnectionStatus;
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

    NetworkConnectionStatus connectionStatus;
    private Boolean startNetworkStatus = false;



    public SearchPresenterImpl(SearchMealsView _view, MealRepository mealRepository, Context context) {
        this._view = _view;
        this.mealRepository = mealRepository;
        this.connectionStatus = NetworkConnectionStatus.getInstance(context);

    }


    @Override
    public void getMealCategories() {
        if (connectionStatus.isNetworkAvailable())
        {
            mealRepository.getAllCategories(this);
        }
    }

    @Override
    public void getMealAreas() {
        if (connectionStatus.isNetworkAvailable()) {
            mealRepository.getAllAreas(this);
        }
    }

    @Override
    public void getMealIngredients() {
        if (connectionStatus.isNetworkAvailable())
        {
            mealRepository.getAllIngredients(this);
        }
    }


    public void getMealByArea(String area) {
        if (connectionStatus.isNetworkAvailable())
        {
            if (area != null) {
                mealRepository.getMealsByArea(area, this);
            }
        }
    }

    @Override
    public void getMealByName(String mealName) {
        if (connectionStatus.isNetworkAvailable())
        {
            mealRepository.getMealsByName(mealName, this);
        }

    }

    public void startMonitoringNetwork() {
        if (!connectionStatus.isNetworkAvailable())
        {
            startNetworkStatus = true;
            _view.onStartNoNetwork();
        }

        connectionStatus.registerNetworkCallback(new NetworkConnectionStatus.NetworkChangeListener() {
            @Override
            public void onNetworkAvailable() {
                if (startNetworkStatus)
                {
                    _view.newtworkAvailable();
                    startNetworkStatus = false;

                }
            }

            @Override
            public void onNetworkLost() {
                _view.networkLost();
            }
        });
    }




    public void stopMonitoringNetwork() {
        connectionStatus.unregisterNetworkCallback();
    }
    @Override
    public void getMealByIngredient(String ingredient) {
        if (ingredient != null) {
            mealRepository.getMealsByIngredient(ingredient, this);
        }
    }

    @Override
    public void getMealByCategory(String category) {
        if (connectionStatus.isNetworkAvailable())
        {
            mealRepository.getMealsByCategory(category, this);
        }

    }

    @Override
    public void onSuccessMealId(Meal meal) {
        if (meal != null) {
            Meals.add(meal);
            fetchedCount++;
            if (fetchedCount == filterMealsList.size()) {
                if (Meals != null) {
                    _view.showSearchResult(Meals);
                }
            }
        }


    }

    @Override
    public void onSuccessMeals(List<Meal> mealsList) {
        if (mealsList != null)
        {
            _view.showSearchResult(mealsList);

        }

    }

    @Override
    public void onSuccessMealsByFirstLetter(List<Meal> mealsList) {

    }

    @Override
    public void onSuccessRandomMeals(List<Meal> mealsList) {

    }

    @Override
    public void onSuccessCategories(List<Categories> categoriesList) {
        // TO DO
        if (categoriesList != null)
        {
            _view.showAllCategories(categoriesList);
        }
    }

    @Override
    public void onSuccessCategoriesSimple(List<ListAllCategories> categoriesList) {

    }

    @Override
    public void onSuccessArea(List<ListAllArea> AreaList) {
        if (AreaList != null)
        {
            _view.showAllAreas(AreaList);
        }
    }

    @Override
    public void onSuccessIngredients(List<ListAllIngredient> ingredientsList) {

        if (ingredientsList != null)
        {
            ArrayList<ListAllIngredient> newPartialList = new ArrayList<>(ingredientsList.subList(0, 20));

            _view.showAllIngredients(newPartialList);
        }

    }

    @Override
    public void onSuccessFilteredMeals(List<FilterMeals> filterMealsList, String filterType) {
        /* Filtered meal get id from it and fetch them
         * Filtered meal list is repose from request to get id of meals according to the filtered type (Area, Ingredient, Category)
         */
        if (filterMealsList != null)
        {
            this.filterMealsList = filterMealsList;
            for (FilterMeals meal : filterMealsList) {
                mealRepository.getMealById(meal.getIdMeal(), this);
            }
        }

    }

    @Override
    public void onSuccessIngredientImage(Bitmap bitmap, String ingredientName) {

    }



    @Override
    public void onFailureResult(String errorMsg) {
        fetchedCount++;
        if (filterMealsList != null && fetchedCount == filterMealsList.size()) {
            if (Meals != null) {
                _view.showSearchResult(Meals);
            }
        } else if (filterMealsList == null) {
            Log.e(TAG, "Error: filterMealsList is null: " + errorMsg);
            _view.showSearchResult(Meals);
        }
        Log.e(TAG, "Error: onFailureResult Search: " + errorMsg);

    }
}
