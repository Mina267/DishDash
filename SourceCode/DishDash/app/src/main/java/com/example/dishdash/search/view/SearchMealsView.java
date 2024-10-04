package com.example.dishdash.search.view;

import com.example.dishdash.model.Categories;
import com.example.dishdash.model.ListAllArea;
import com.example.dishdash.model.ListAllIngredient;
import com.example.dishdash.model.Meal;

import java.util.List;

public interface SearchMealsView {
    public void showAllCategories(List<Categories> categoriesList);
    public void showAllAreas(List<ListAllArea> areasList);
    public void showAllIngredients(List<ListAllIngredient> ingredientList);

    public void showSearchResult(List<Meal> meal);

    void onStartNoNetwork();
    void newtworkAvailable();
    void networkLost();

}
