package com.example.dishdash.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dishdash.model.MealPlan;

import java.util.List;

@Dao
public interface MealPlanDAO {

    @Query("SELECT * FROM MealPlan_table")
    LiveData<List<MealPlan>> getAllMealPlans();

    @Query("SELECT * FROM MealPlan_table WHERE week = :week")
    LiveData<List<MealPlan>> getMealsForWeek(String week);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeal(MealPlan meal);

    @Delete
    void deleteMeal(MealPlan meal);
}
