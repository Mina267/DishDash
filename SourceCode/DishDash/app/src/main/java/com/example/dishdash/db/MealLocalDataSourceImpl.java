package com.example.dishdash.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.dishdash.model.Meal;

import java.util.List;

public class MealLocalDataSourceImpl implements MealLocalDataSource {
    private Context context;
    private MealDAO mealDAO;
    private LiveData<List<Meal>> storedMeals;
    private static MealLocalDataSource repo = null;

    private MealLocalDataSourceImpl(Context context) {
        this.context = context;
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        mealDAO = db.getMealDAO();
        storedMeals = (LiveData<List<Meal>>) mealDAO.getAllMeals();
    }

    public static MealLocalDataSource getInstance(Context context) {
        if (repo == null) {
            repo = new MealLocalDataSourceImpl(context);
        }
        return repo;
    }



    @Override
    public LiveData<List<Meal>> getStoredMeals() {
        return storedMeals;
    }

    @Override
    public void deleteMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deleteMeal(meal);
            }
        }).start();
    }

    @Override
    public void insertMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insertMeal(meal);
            }
        }).start();
    }
}
