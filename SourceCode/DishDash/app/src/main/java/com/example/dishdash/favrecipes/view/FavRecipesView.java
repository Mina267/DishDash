package com.example.dishdash.favrecipes.view;

import androidx.lifecycle.LiveData;

import com.example.dishdash.model.Meal;

import java.util.List;

public interface FavRecipesView {
    void showData(LiveData<List<Meal>> meals);;
}
