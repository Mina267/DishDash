package com.example.dishdash.network;

import android.graphics.Bitmap;

import com.example.dishdash.model.Categories;
import com.example.dishdash.model.FilterMeals;
import com.example.dishdash.model.ListAllArea;
import com.example.dishdash.model.ListAllCategories;
import com.example.dishdash.model.ListAllIngredient;
import com.example.dishdash.model.Meal;

import java.util.List;

/* CallBack for network request */
public interface NetworkDelegate {
    void onSuccessMealId(Meal meal);
    void onSuccessMeals(List<Meal> mealsList);
    void onSuccessMealsByFirstLetter(List<Meal> mealsList);
    void onSuccessRandomMeals(List<Meal> mealsList);
    void onSuccessCategories(List<Categories> categoriesList);

    void onSuccessCategoriesSimple(List<ListAllCategories> categoriesList);
    void onSuccessArea(List<ListAllArea> AreaList);
    void onSuccessIngredients(List<ListAllIngredient> ingredientsList);
    void onSuccessFilteredMeals(List<FilterMeals> filterMealsList, String filterType);
    void onSuccessIngredientImage(Bitmap bitmap, String ingredientName);



    void onFailureResult(String errorMsg);
}
