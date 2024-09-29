package com.example.dishdash.searchresult.presenter;

import com.example.dishdash.model.Meal;

public interface SearchResultPresenter {
    void addToFav(Meal meal);
    public void getMealByName(String mealName);

}
