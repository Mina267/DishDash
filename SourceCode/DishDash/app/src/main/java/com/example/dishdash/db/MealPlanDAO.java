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

    /* Retrieve all meal plans from the table */
    @Query("SELECT * FROM mealplan_table")
    LiveData<List<MealPlan>> getAllMealPlans();

    /* Insert a meal plan for a specific date */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertDay(MealPlan mealPlan);

    /* Delete a meal plan */
    @Delete
    void deleteMeal(MealPlan mealPlan);

    /* Retrieve meals for a specific date */
    @Query("SELECT * FROM mealplan_table WHERE Date = :date")
    LiveData<List<MealPlan>> getMealsOfDay(String date);


    @Query("SELECT EXISTS(SELECT 1 FROM mealplan_table WHERE idMeal = :idMeal AND Date = :date)")
    LiveData<Boolean> isMealExists(String idMeal, String date);
}
