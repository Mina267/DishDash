package com.example.dishdash.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dishdash.model.FavoriteMeal;
import com.example.dishdash.model.Meal;

import java.util.List;

@Dao
public interface FavoriteMealDAO {
    @Query("SELECT * FROM FavMeals_table")
    LiveData<List<FavoriteMeal>> getAllFavorites();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFavorite(FavoriteMeal meal);

    @Delete
    void deleteFavorite(FavoriteMeal meal);
}
