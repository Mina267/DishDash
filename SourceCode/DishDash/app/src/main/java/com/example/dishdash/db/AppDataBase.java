package com.example.dishdash.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealPlan;
import com.example.dishdash.model.MealPlanJunction;

@Database(entities = {MealPlan.class, Meal.class, MealPlanJunction.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance = null;

    public abstract MealPlanDAO getMealPlanDAO();
    public abstract MealDAO getMealDAO();

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "mealsdb")
                    .build();
        }
        return instance;
    }
}
