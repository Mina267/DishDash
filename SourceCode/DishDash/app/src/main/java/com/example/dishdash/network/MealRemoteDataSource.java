package com.example.dishdash.network;

public interface MealRemoteDataSource {
    void searchMealsByName(String mealName, NetworkDelegate networkCallback);
    void searchMealsByFirstLetter(char firstLetter, NetworkDelegate networkCallback);
    void lookupMealById(String mealId, NetworkDelegate networkCallback);
}