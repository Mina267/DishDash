package com.example.dishdash.detailsrecipes.view;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;

import com.example.dishdash.model.Ingredients;
import com.example.dishdash.model.Meal;

import java.util.List;
import java.util.Map;

public interface DetailsRecipesView {
    public void onGetImgOfIngredient(Map<String, Bitmap> ingredientBitmapMap, List<Ingredients> ListIngredients);
    void markSavedMeals(LiveData<List<Meal>> meals);

}
