package com.example.dishdash.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.dishdash.model.FavoriteMeal;

import java.util.List;

public class FavoriteMealLocalDataSourceImpl implements FavoriteMealLocalDataSource {
    private Context context;
    private FavoriteMealDAO favoriteMealDAO;
    private LiveData<List<FavoriteMeal>> storedFavorites;
    private static FavoriteMealLocalDataSource repo = null;

    private FavoriteMealLocalDataSourceImpl(Context context) {
        this.context = context;
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        favoriteMealDAO = db.getFavoriteMealDAO();
        storedFavorites = (LiveData<List<FavoriteMeal>>) favoriteMealDAO.getAllFavorites();
    }

    public static FavoriteMealLocalDataSource getInstance(Context context) {
        if (repo == null) {
            repo = new FavoriteMealLocalDataSourceImpl(context);
        }
        return repo;
    }



    @Override
    public LiveData<List<FavoriteMeal>> getStoredFavorites() {
        return storedFavorites;
    }

    @Override
    public void deleteFavorite(FavoriteMeal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                favoriteMealDAO.deleteFavorite(meal);
            }
        }).start();
    }

    @Override
    public void insertFavorite(FavoriteMeal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                favoriteMealDAO.insertFavorite(meal);
            }
        }).start();
    }
}
