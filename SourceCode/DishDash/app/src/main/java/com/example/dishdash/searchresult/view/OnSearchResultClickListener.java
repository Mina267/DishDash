package com.example.dishdash.searchresult.view;

import com.example.dishdash.model.Meal;

public interface OnSearchResultClickListener {
    void onAddToFavoriteClick(Meal meal);
    void onRemoveFromFavoriteClick(Meal meal);
}
