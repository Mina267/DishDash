package com.example.dishdash.network;

public interface MealRemoteDataSource {
    void searchMealsByName(String mealName, NetworkDelegate networkCallback);
    void searchMealsByFirstLetter(char firstLetter, NetworkDelegate networkCallback);
    void lookupMealById(String mealId, NetworkDelegate networkCallback);
    void lookupRandomMeal(NetworkDelegate networkCallback);
    void listAllCategories(NetworkDelegate networkCallback);
    void listAllCategoriesSimple(NetworkDelegate networkCallback);
    void listAllAreas(NetworkDelegate networkCallback);
    void listAllIngredients(NetworkDelegate networkCallback);
    void filterMealsByCategory(String category, NetworkDelegate networkCallback);
    void filterMealsByArea(String area, NetworkDelegate networkCallback);
    void filterMealsByIngredient(String ingredient, NetworkDelegate networkCallback);
}