package com.example.dishdash.foryou.presenter;

import com.example.dishdash.model.Meal;

public interface ForYouPresenter {
    void getRandomProduct();
    void addToFavourite(Meal meal);
}
