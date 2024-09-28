package com.example.dishdash.detailsrecipes.view;

import android.graphics.Bitmap;

import com.example.dishdash.model.Meal;

public interface DetailsRecipesView {
    public void onCiclkDetailsRecipesAddToFav(Meal meal);
    public void onGetImgOfIngredient(Bitmap bitmap);
}
