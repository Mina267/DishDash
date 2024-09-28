package com.example.dishdash.network;

import com.example.dishdash.model.CategoriesRoot;
import com.example.dishdash.model.FilterMealsRoot;
import com.example.dishdash.model.ListAllAreaRoot;
import com.example.dishdash.model.ListAllCategoriesRoot;
import com.example.dishdash.model.ListAllIngredientRoot;
import com.example.dishdash.model.MealsRoot;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface MealService {

    /* Search meals by name */
    @GET("search.php")
    Call<MealsRoot> searchMealsByName(@Query("s") String mealName);

    /* Search meals by first letter */
    @GET("search.php")
    Call<MealsRoot> searchMealsByFirstLetter(@Query("f") char firstLetter);

    /* Lookup full meal details by ID */
    @GET("lookup.php")
    Call<MealsRoot> lookupMealById(@Query("i") String mealId);

    /* Lookup a random meal */
    @GET("random.php")
    Call<MealsRoot> lookupRandomMeal();

    /* List all meal categories */
    @GET("categories.php")
    Call<CategoriesRoot> listAllCategories();
    
    /* List all meal categories */
    @GET("list.php?c=list")
    Call<ListAllCategoriesRoot> listAllCategoriesSimple();

    /* List all areas */
    @GET("list.php?a=list")
    Call<ListAllAreaRoot> listAllAreas();

    /* List all ingredients */
    @GET("list.php?i=list")
    Call<ListAllIngredientRoot> listAllIngredients();

    /* Filter meals by category */
    @GET("filter.php")
    Call<FilterMealsRoot> filterMealsByCategory(@Query("c") String category);

    /* Filter meals by area */
    @GET("filter.php")
    Call<FilterMealsRoot> filterMealsByArea(@Query("a") String area);

    /* Filter meals by main ingredient */
    @GET("filter.php")
    Call<FilterMealsRoot> filterMealsByIngredient(@Query("i") String ingredient);


    @GET
    Call<ResponseBody> getIngredientImage(@Url String imageUrl);
}
