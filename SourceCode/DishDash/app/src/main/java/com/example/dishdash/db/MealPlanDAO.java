package com.example.dishdash.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealPlan;
import com.example.dishdash.model.MealPlanJunction;

import java.util.List;

@Dao
public interface MealPlanDAO {

    @Query("SELECT * FROM MealPlan_table")
    LiveData<List<MealPlan>> getAllMealPlans();

    @Delete
    void deleteMeal(MealPlan mealPlan);


    // Meal Plan table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertDay(MealPlan mealPlan);

    // Meal-Day Junction table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertDayMealJunction(MealPlanJunction mealPlanJunction);

    @Query("SELECT meal_table.* FROM meal_table " +
            "JOIN plan_junction_table ON meal_table.idMeal = plan_junction_table.idMeal " +
            "JOIN mealplan_table ON plan_junction_table.idDay = mealplan_table.idDay\n" +
            "WHERE mealplan_table.idDay = :idDay")
    LiveData<List<Meal>> getMealsOfDay(int idDay);

}
