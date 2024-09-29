package com.example.dishdash.detailsrecipes.view;

import android.graphics.Bitmap;

import com.example.dishdash.model.Meal;

import java.util.List;
import java.util.Map;

public interface DetailsRecipesView {
    public void onCiclkDetailsRecipesAddToFav(Meal meal);
    public void onGetImgOfIngredient(Map<String, Bitmap> ingredientBitmapMap);
}
