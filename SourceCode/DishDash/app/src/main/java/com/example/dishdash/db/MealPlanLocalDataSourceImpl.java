package com.example.dishdash.db;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealPlan;
import com.example.dishdash.model.MealMapper;

import java.util.List;

public class MealPlanLocalDataSourceImpl implements MealPlanLocalDataSource {
    private MealPlanDAO mealPlanDAO;
    private LiveData<List<MealPlan>> storedMealPlans;
    private static MealPlanLocalDataSource repo = null;

    private MealPlanLocalDataSourceImpl(Context context) {
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        mealPlanDAO = db.getMealPlanDAO();
        storedMealPlans = mealPlanDAO.getAllMealPlans();
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

    @Override
    public LiveData<List<MealPlan>> getMealsOfTheDay(String date) {
        return mealPlanDAO.getMealsOfDay(date);
    }

    @Override
    public void deleteMeal(MealPlan mealPlan) {
        new Thread(() -> mealPlanDAO.deleteMeal(mealPlan)).start();
    }

    @Override
    public void insertPlanMealForDay(Meal meal, String date) {
        new Thread(() -> {
            MealPlan mealPlan = MealMapper.mapMealToMealPlan(meal, date);
            mealPlanDAO.insertDay(mealPlan);
        }).start();
    }

    public LiveData<Boolean> isMealExists(String idMeal, String date) {
        return mealPlanDAO.isMealExists(idMeal, date);
    }
}
