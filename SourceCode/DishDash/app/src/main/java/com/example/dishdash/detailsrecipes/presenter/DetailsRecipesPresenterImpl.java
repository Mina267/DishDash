package com.example.dishdash.detailsrecipes.presenter;

import android.graphics.Bitmap;

import com.example.dishdash.detailsrecipes.view.DetailsRecipesView;
import com.example.dishdash.model.Categories;
import com.example.dishdash.model.FilterMeals;
import com.example.dishdash.model.ListAllArea;
import com.example.dishdash.model.ListAllCategories;
import com.example.dishdash.model.ListAllIngredient;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepository;
import com.example.dishdash.network.NetworkDelegate;

import java.util.List;

public class DetailsRecipesPresenterImpl implements DetailsRecipesPresenter, NetworkDelegate {
    DetailsRecipesView _view;
    MealRepository mealRepository;

    public DetailsRecipesPresenterImpl(DetailsRecipesView _view, MealRepository mealRepository) {
        this._view = _view;
        this.mealRepository = mealRepository;
    }

    @Override
    public void AddMealToFav(Meal meal) {
        mealRepository.insertMeal(meal);
    }

    @Override
    public void getImgOfIngredient(String IngredientName) {
        mealRepository.getIngredientImg(IngredientName, this);
    }



    @Override
    public void onSuccessMeals(List<Meal> mealsList) {

    }

    @Override
    public void onSuccessCategories(List<Categories> categoriesList) {

    }

    @Override
    public void onSuccessCategoriesSimple(List<ListAllCategories> categoriesList) {

    }

    @Override
    public void onSuccessArea(List<ListAllArea> AreaList) {

    }

    @Override
    public void onSuccessIngredients(List<ListAllIngredient> IngredientsList) {

    }

    @Override
    public void onSuccessFilteredMeals(List<FilterMeals> FilterMealsList) {

    }

    @Override
    public void onSuccessIngredientImage(Bitmap bitmap) {
        _view.onGetImgOfIngredient(bitmap);
    }

    @Override
    public void onFailureResult(String errorMsg) {

    }
}
