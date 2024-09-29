package com.example.dishdash.search.presenter;

public interface SearchPresenter {
    public void getMealCategories();

    public void getMealAreas();

    public void getMealIngredients();

    public void getMealByCategory(String category);

    public void getMealByIngredient(String ingredient);

    public void getMealByArea(String area);

    public void getMealByName(String mealName);

}
