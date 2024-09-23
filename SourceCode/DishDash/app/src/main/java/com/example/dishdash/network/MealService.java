package com.example.dishdash.network;

import com.example.dishdash.model.MealsRoot;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {

    // Search meals by name
    @GET("search.php")
    Call<MealsRoot> searchMealsByName(@Query("s") String mealName);

    // Search meals by first letter
    @GET("search.php")
    Call<MealsRoot> searchMealsByFirstLetter(@Query("f") char firstLetter);

    // Lookup full meal details by ID
    @GET("lookup.php")
    Call<MealsRoot> lookupMealById(@Query("i") String mealId);
}
