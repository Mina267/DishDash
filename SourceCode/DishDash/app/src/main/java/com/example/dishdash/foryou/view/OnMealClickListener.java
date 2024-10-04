package com.example.dishdash.foryou.view;

import com.example.dishdash.model.Meal;

public interface OnMealClickListener {
    void onAddToFavoriteClick(Meal meal);
    void onRemoveFromFavoriteClick(Meal meal);
}
