package com.example.dishdash.db;


import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealPlan;
import com.example.dishdash.model.MealPlanJunction;

import java.util.List;

public class MealPlanLocalDataSourceImpl implements MealPlanLocalDataSource {
    private MealPlanDAO mealPlanDAO;
    private LiveData<List<MealPlan>> storedMealPlans;
    private static MealPlanLocalDataSource repo = null;

    private MealPlanLocalDataSourceImpl(Context context) {
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        mealPlanDAO = db.getMealPlanDAO();
        storedMealPlans = (LiveData<List<MealPlan>>) mealPlanDAO.getAllMealPlans();
    }

    public static MealPlanLocalDataSource getInstance(Context context) {
        if (repo == null) {
            repo = new MealPlanLocalDataSourceImpl(context);
        }
        return repo;
    }

    @Override
    public LiveData<List<MealPlan>> getStoredMeals() {
        return storedMealPlans;
    }

    public void insertMealDayJunction(MealPlanJunction mealPlanJunction)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealPlanDAO.insertDayMealJunction(mealPlanJunction);
            }
        }).start();
    }

    @Override
    public LiveData<List<Meal>> getMealsOfTheDay(int day) {
        return mealPlanDAO.getMealsOfDay(day);
    }

    @Override
    public void deleteMeal(MealPlan mealPlan) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealPlanDAO.deleteMeal(mealPlan);
            }
        }).start();
    }

    @Override
    public void insertPlanMealForDay(MealPlan mealPlan) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealPlanDAO.insertDay(mealPlan);
            }
        }).start();
    }
}
