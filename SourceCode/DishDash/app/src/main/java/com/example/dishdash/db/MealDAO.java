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
public interface MealDAO {
    @Query("SELECT * FROM meal_table")
    LiveData<List<Meal>> getAllMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(Meal meal);

    @Delete
    void deleteMeal(Meal meal);

    @Query("SELECT EXISTS(SELECT 1 FROM meal_table WHERE idMeal = :idMeal)")
    boolean isMealExists(String idMeal);



}



