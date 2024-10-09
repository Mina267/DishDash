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
    public void getSavedMeals() {
        _view.markSavedMeals(mealRepository.getStoredMeals());

    }

    @Override
    public void deleteMeal(Meal meal) { if (meal != null) {mealRepository.deleteMeal(meal);}}

    @Override
    public void getImgOfIngredient(Meal meal) {
        Log.i(TAG, "getImgOfIngredient: the meaaaal " + meal.getStrMeal());
        ingredients = createIngredientList(meal);
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
            _view.onGetImgOfIngredient(ingredientBitmapMap, ingredients);
        }
    }

    @Override
    public void onFailureResult(String errorMsg) {
        fetchedCount++;
        Log.e(TAG, "Failed to fetch ingredient image: " + errorMsg);

        // Check if all attempts to fetch images are done
        if (fetchedCount == ingredients.size()) {
            _view.onGetImgOfIngredient(ingredientBitmapMap, ingredients);
        }
    }

    @Override
    public void onSuccessMealId(Meal meal) {}

    @Override
    public void onSuccessMeals(List<Meal> mealsList) {}

    @Override
    public void onSuccessMealsByFirstLetter(List<Meal> mealsList) {

    }

    @Override
    public void onSuccessRandomMeals(List<Meal> mealsList) {

    }

    @Override
    public void onSuccessCategories(List<Categories> categoriesList) {}

    @Override
    public void onSuccessCategoriesSimple(List<ListAllCategories> categoriesList) {}

    @Override
    public void onSuccessArea(List<ListAllArea> AreaList) {}

    @Override
    public void onSuccessIngredients(List<ListAllIngredient> ingredientsList) {}

    @Override
    public void onSuccessFilteredMeals(List<FilterMeals> filterMealsList, String filterType) {}

    private List<Ingredients> createIngredientList(Meal meal) {

        List<Ingredients> ListIngredients = new ArrayList<>();

        if (meal.getStrIngredient1() != null && !meal.getStrIngredient1().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient1(), meal.getStrMeasure1()));
        }
        if (meal.getStrIngredient2() != null && !meal.getStrIngredient2().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient2(), meal.getStrMeasure2()));
        }
        if (meal.getStrIngredient3() != null && !meal.getStrIngredient3().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient3(), meal.getStrMeasure3()));
        }
        if (meal.getStrIngredient4() != null && !meal.getStrIngredient4().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient4(), meal.getStrMeasure4()));
        }
        if (meal.getStrIngredient5() != null && !meal.getStrIngredient5().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient5(), meal.getStrMeasure5()));
        }
        if (meal.getStrIngredient6() != null && !meal.getStrIngredient6().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient6(), meal.getStrMeasure6()));
        }
        if (meal.getStrIngredient7() != null && !meal.getStrIngredient7().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient7(), meal.getStrMeasure7()));
        }
        if (meal.getStrIngredient8() != null && !meal.getStrIngredient8().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient8(), meal.getStrMeasure8()));
        }
        if (meal.getStrIngredient9() != null && !meal.getStrIngredient9().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient9(), meal.getStrMeasure9()));
        }
        if (meal.getStrIngredient10() != null && !meal.getStrIngredient10().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient10(), meal.getStrMeasure10()));
        }
        if (meal.getStrIngredient11() != null && !meal.getStrIngredient11().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient11(), meal.getStrMeasure11()));
        }
        if (meal.getStrIngredient12() != null && !meal.getStrIngredient12().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient12(), meal.getStrMeasure12()));
        }
        if (meal.getStrIngredient13() != null && !meal.getStrIngredient13().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient13(), meal.getStrMeasure13()));
        }
        if (meal.getStrIngredient14() != null && !meal.getStrIngredient14().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient14(), meal.getStrMeasure14()));
        }
        if (meal.getStrIngredient15() != null && !meal.getStrIngredient15().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient15(), meal.getStrMeasure15()));
        }
        if (meal.getStrIngredient16() != null && !meal.getStrIngredient16().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient16(), meal.getStrMeasure16()));
        }
        if (meal.getStrIngredient17() != null && !meal.getStrIngredient17().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient17(), meal.getStrMeasure17()));
        }
        if (meal.getStrIngredient18() != null && !meal.getStrIngredient18().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient18(), meal.getStrMeasure18()));
        }
        if (meal.getStrIngredient19() != null && !meal.getStrIngredient19().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient19(), meal.getStrMeasure19()));
        }
        if (meal.getStrIngredient20() != null && !meal.getStrIngredient20().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient20(), meal.getStrMeasure20()));
        }

        Log.i(TAG, "createIngredientList: " + ListIngredients);
        return ListIngredients;
    }
}
