package com.example.dishdash.detailsrecipes.presenter;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.dishdash.detailsrecipes.view.DetailsRecipesView;
import com.example.dishdash.model.Categories;
import com.example.dishdash.model.FilterMeals;
import com.example.dishdash.model.Ingredients;
import com.example.dishdash.model.ListAllArea;
import com.example.dishdash.model.ListAllCategories;
import com.example.dishdash.model.ListAllIngredient;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepository;
import com.example.dishdash.network.NetworkDelegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailsRecipesPresenterImpl implements DetailsRecipesPresenter, NetworkDelegate {
    private static final String TAG = "DetailsRecipesPresenter";
    DetailsRecipesView _view;
    MealRepository mealRepository;
    private List<Ingredients> ingredients;
    private final Map<String, Bitmap> ingredientBitmapMap = new HashMap<>();
    private int fetchedCount = 0;

    public DetailsRecipesPresenterImpl(DetailsRecipesView _view, MealRepository mealRepository) {
        this._view = _view;
        this.mealRepository = mealRepository;
    }

    @Override
    public void AddMealToFav(Meal meal) {
        mealRepository.insertMeal(meal);
    }

    @Override
    public void getImgOfIngredient(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
        fetchedCount = 0;
        ingredientBitmapMap.clear(); // Clear any previous data

        for (Ingredients ingredient : ingredients) {
            mealRepository.getIngredientImg(ingredient.getIngredientName(), this);
            Log.i(TAG, "Fetching image for ingredient: " + ingredient.getIngredientName());
        }
    }

    @Override
    public void onSuccessIngredientImage(Bitmap bitmap, String ingredientName) {
        // Store the bitmap with the corresponding ingredient name
        ingredientBitmapMap.put(ingredientName, bitmap);
        fetchedCount++;

        Log.i(TAG, "Fetched bitmap for ingredient: " + ingredientName);

        // If all images are fetched, update the view
        if (fetchedCount == ingredients.size()) {
            _view.onGetImgOfIngredient(ingredientBitmapMap);
        }
    }

    @Override
    public void onFailureResult(String errorMsg) {
        fetchedCount++;
        Log.e(TAG, "Failed to fetch ingredient image: " + errorMsg);

        // Check if all attempts to fetch images are done
        if (fetchedCount == ingredients.size()) {
            _view.onGetImgOfIngredient(ingredientBitmapMap);
        }
    }

    @Override
    public void onSuccessMealId(Meal meal) {}

    @Override
    public void onSuccessMeals(List<Meal> mealsList) {}

    @Override
    public void onSuccessCategories(List<Categories> categoriesList) {}

    @Override
    public void onSuccessCategoriesSimple(List<ListAllCategories> categoriesList) {}

    @Override
    public void onSuccessArea(List<ListAllArea> AreaList) {}

    @Override
    public void onSuccessIngredients(List<ListAllIngredient> ingredientsList) {}

    @Override
    public void onSuccessFilteredMeals(List<FilterMeals> filterMealsList) {}
}
