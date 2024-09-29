package com.example.dishdash.model;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.dishdash.db.MealLocalDataSource;
import com.example.dishdash.db.MealPlanLocalDataSource;
import com.example.dishdash.network.MealRemoteDataSource;
import com.example.dishdash.network.NetworkDelegate;

import java.util.List;

public class MealRepositoryImpl implements MealRepository {
    MealRemoteDataSource mealRemoteDataSource;
    MealLocalDataSource mealLocalDataSource;
    MealPlanLocalDataSource mealPlanLocalDataSource;
    private static MealRepository repo;

    private static final String TAG = "MealRepositoryImpl";
    public static MealRepository getInstance(MealRemoteDataSource mealRemoteDataSource, MealLocalDataSource mealLocalDataSourceImpl, MealPlanLocalDataSource mealPlanLocalDataSourceImpl) {
        if (repo == null) {
            repo = new MealRepositoryImpl(mealRemoteDataSource, mealLocalDataSourceImpl, mealPlanLocalDataSourceImpl);
        }
        return repo;
    }


    public MealRepositoryImpl(MealRemoteDataSource mealRemoteDataSource, MealLocalDataSource mealLocalDataSource, MealPlanLocalDataSource mealPlanLocalDataSource) {
        this.mealRemoteDataSource = mealRemoteDataSource;
        this.mealLocalDataSource = mealLocalDataSource;
        this.mealPlanLocalDataSource = mealPlanLocalDataSource;
    }



    @Override
    public void getMealsByName(String mealName, NetworkDelegate networkCallback) {
        mealRemoteDataSource.searchMealsByName(mealName, networkCallback);
    }

    @Override
    public void getMealsByFirstLetter(char firstLetter, NetworkDelegate networkCallback) {
        mealRemoteDataSource.searchMealsByFirstLetter(firstLetter, networkCallback);
    }


    @Override
    public void getMealById(String mealId, NetworkDelegate networkCallback) {
        mealRemoteDataSource.lookupMealById(mealId, networkCallback);

    }
    @Override
    public void getOneRandomMeal(NetworkDelegate networkCallback) {
        mealRemoteDataSource.lookupRandomMeal(networkCallback);

    }
    @Override
    public void getAllCategories(NetworkDelegate networkCallback) {
        Log.i(TAG, "getAllCategories: ");
        mealRemoteDataSource.listAllCategories(networkCallback);

    }
    @Override
    public void getAllCategoriesSimple(NetworkDelegate networkCallback) {
        mealRemoteDataSource.listAllCategoriesSimple(networkCallback);

    }
    @Override
    public void getAllAreas(NetworkDelegate networkCallback) {
        mealRemoteDataSource.listAllAreas(networkCallback);

    }
    @Override
    public void getAllIngredients(NetworkDelegate networkCallback) {
        mealRemoteDataSource.listAllIngredients(networkCallback);

    }
    @Override
    public void getMealsByCategory(String category, NetworkDelegate networkCallback) {
        mealRemoteDataSource.filterMealsByCategory(category, networkCallback);

    }
    @Override
    public void getMealsByArea(String area, NetworkDelegate networkCallback) {
        mealRemoteDataSource.filterMealsByArea(area, networkCallback);

    }
    @Override
    public void getMealsByIngredient(String ingredient, NetworkDelegate networkCallback) {
        mealRemoteDataSource.filterMealsByIngredient(ingredient, networkCallback);

    }

    @Override
    public LiveData<List<Meal>> getStoredMeals() {
        return mealLocalDataSource.getStoredMeals();
    }

    @Override
    public void deleteMeal(Meal meal) {
        mealLocalDataSource.deleteMeal(meal);
    }

    @Override
    public void insertMeal(Meal meal) {
        mealLocalDataSource.insertMeal(meal);
    }


    @Override
    public LiveData<List<MealPlan>> getStoredMealsPlan() {
        return mealPlanLocalDataSource.getStoredMeals();
    }

    @Override
    public LiveData<List<Meal>> getMealsOfTheDay(int day) {
        return mealPlanLocalDataSource.getMealsOfTheDay(day);
    }

    @Override
    public void deleteMealPlan(MealPlan mealPlan) {
        mealPlanLocalDataSource.deleteMeal(mealPlan);
    }

    @Override
    public void insertPlanMealForDay(MealPlan mealPlan) {
        mealPlanLocalDataSource.insertPlanMealForDay(mealPlan);
    }

    @Override
    public void getIngredientImg(String ingredientName, NetworkDelegate networkCallback) {
        mealRemoteDataSource.fetchIngredientImage(ingredientName, networkCallback);
    }








}
