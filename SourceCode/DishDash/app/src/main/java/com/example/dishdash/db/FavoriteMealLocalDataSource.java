package com.example.dishdash.db;

import androidx.lifecycle.LiveData;

import com.example.dishdash.model.FavoriteMeal;

import java.util.List;

public interface FavoriteMealLocalDataSource {
    LiveData<List<FavoriteMeal>> getStoredFavorites();

    void deleteFavorite(FavoriteMeal meal);

    void insertFavorite(FavoriteMeal meal);
}
