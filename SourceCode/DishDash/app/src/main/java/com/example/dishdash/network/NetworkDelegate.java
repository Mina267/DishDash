package com.example.dishdash.network;

import android.graphics.Bitmap;

import com.example.dishdash.model.Categories;
import com.example.dishdash.model.FilterMeals;
import com.example.dishdash.model.ListAllArea;
import com.example.dishdash.model.ListAllCategories;
import com.example.dishdash.model.ListAllIngredient;
import com.example.dishdash.model.Meal;

import java.util.List;

public interface NetworkDelegate {
    void onSuccessMeals(List<Meal> mealsList);
    void onSuccessCategories(List<Categories> categoriesList);

    void onSuccessCategoriesSimple(List<ListAllCategories> categoriesList);
    void onSuccessArea(List<ListAllArea> AreaList);
    void onSuccessIngredients(List<ListAllIngredient> IngredientsList);
    void onSuccessFilteredMeals(List<FilterMeals> FilterMealsList);
    void onSuccessIngredientImage(Bitmap bitmap);



    void onFailureResult(String errorMsg);
}
