package com.example.dishdash.ingredients.view;

import com.example.dishdash.model.Categories;
import com.example.dishdash.model.ListAllArea;
import com.example.dishdash.model.ListAllIngredient;
import com.example.dishdash.model.Meal;

import java.util.List;

public interface IngredientsMealsView {
    public void showAllIngredients(List<ListAllIngredient> ingredientList);

    public void showSearchResult(List<Meal> meal);

}
