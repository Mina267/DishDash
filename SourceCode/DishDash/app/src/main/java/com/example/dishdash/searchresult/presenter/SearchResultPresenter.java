package com.example.dishdash.searchresult.presenter;

import com.example.dishdash.model.Meal;

public interface SearchResultPresenter {
    void getFavProducts();
    void addToFav(Meal meal);

}
