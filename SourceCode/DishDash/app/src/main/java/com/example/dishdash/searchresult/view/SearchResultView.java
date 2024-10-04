package com.example.dishdash.searchresult.view;

import androidx.lifecycle.LiveData;

import com.example.dishdash.model.Meal;

import java.util.List;

public interface SearchResultView {
    void showSearchResult(List<Meal> meals);;
    void onFailureSearch(String errorMsg);
    void markSavedMeals(LiveData<List<Meal>> meals);

}
