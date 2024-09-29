package com.example.dishdash.search.view;

import com.example.dishdash.model.Categories;
import com.example.dishdash.model.ListAllArea;
import com.example.dishdash.model.Meal;

import java.util.List;

public interface SearchView {
    public void showAllCategories(List<Categories> categoriesList);
    public void showAllAreas(List<ListAllArea> areassList);

    public void showSearchResult(List<Meal> meal);

}
