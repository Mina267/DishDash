package com.example.dishdash.network;

import android.util.Log;

import com.example.dishdash.model.CategoriesRoot;
import com.example.dishdash.model.FilterMealsRoot;
import com.example.dishdash.model.ListAllAreaRoot;
import com.example.dishdash.model.ListAllCategoriesRoot;
import com.example.dishdash.model.ListAllIngredientRoot;
import com.example.dishdash.model.MealsRoot;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSourceImpl implements MealRemoteDataSource {

    private static final String TAG = "MealClient";
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static MealRemoteDataSource client = null;
    private MealService mealService;

    private MealRemoteDataSourceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mealService = retrofit.create(MealService.class);
    }

    public static MealRemoteDataSource getInstance() {
        if (client == null) {
            client = new MealRemoteDataSourceImpl();
        }
        return client;
    }

    @Override
    public void searchMealsByName(String mealName, NetworkDelegate networkCallback) {
        mealService.searchMealsByName(mealName).enqueue(new Callback<MealsRoot>() {
            @Override
            public void onResponse(Call<MealsRoot> call, Response<MealsRoot> response) {
                if (response.body() != null && response.body().getMeals() != null) {
                    Log.i(TAG, "Meals found: " + response.body().getMeals().size());
                    networkCallback.onSuccessMeals(response.body().getMeals());
                } else {
                    networkCallback.onFailureResult("No meals found.");
                }
            }

            @Override
            public void onFailure(Call<MealsRoot> call, Throwable throwable) {
                networkCallback.onFailureResult(throwable.getMessage());
            }
        });
    }

    @Override
    public void searchMealsByFirstLetter(char firstLetter, NetworkDelegate networkCallback) {
        mealService.searchMealsByFirstLetter(firstLetter).enqueue(new Callback<MealsRoot>() {
            @Override
            public void onResponse(Call<MealsRoot> call, Response<MealsRoot> response) {
                if (response.body() != null && response.body().getMeals() != null) {
                    Log.i(TAG, "Meals found: " + response.body().getMeals().size());
                    networkCallback.onSuccessMeals(response.body().getMeals());
                } else {
                    networkCallback.onFailureResult("No meals found.");
                }
            }

            @Override
            public void onFailure(Call<MealsRoot> call, Throwable throwable) {
                networkCallback.onFailureResult(throwable.getMessage());
            }
        });
    }

    @Override
    public void lookupMealById(String mealId, NetworkDelegate networkCallback) {
        mealService.lookupMealById(mealId).enqueue(new Callback<MealsRoot>() {
            @Override
            public void onResponse(Call<MealsRoot> call, Response<MealsRoot> response) {
                if (response.body() != null && response.body().getMeals() != null) {
                    Log.i(TAG, "Meal found with ID: " + mealId);
                    networkCallback.onSuccessMeals(response.body().getMeals());
                } else {
                    networkCallback.onFailureResult("No meal found.");
                }
            }

            @Override
            public void onFailure(Call<MealsRoot> call, Throwable throwable) {
                networkCallback.onFailureResult(throwable.getMessage());
            }
        });
    }

    // method to lookup a random meal
    @Override
    public void lookupRandomMeal(NetworkDelegate networkCallback) {
        mealService.lookupRandomMeal().enqueue(new Callback<MealsRoot>() {
            @Override
            public void onResponse(Call<MealsRoot> call, Response<MealsRoot> response) {
                if (response.body() != null && response.body().getMeals() != null) {
                    Log.i(TAG, "Random meal found");
                    networkCallback.onSuccessMeals(response.body().getMeals());
                } else {
                    networkCallback.onFailureResult("No random meal found.");
                }
            }

            @Override
            public void onFailure(Call<MealsRoot> call, Throwable throwable) {
                networkCallback.onFailureResult(throwable.getMessage());
            }
        });
    }

    // method to list all meal categories
    @Override
    public void listAllCategories(NetworkDelegate networkCallback) {
        mealService.listAllCategories().enqueue(new Callback<CategoriesRoot>() {
            @Override
            public void onResponse(Call<CategoriesRoot> call, Response<CategoriesRoot> response) {
                if (response.body() != null && response.body().getCategories() != null) {
                    Log.i(TAG, "Categories found: " + response.body().getCategories().size());
                    networkCallback.onSuccessCategories(response.body().getCategories());
                } else {
                    networkCallback.onFailureResult("No categories found.");
                }
            }

            @Override
            public void onFailure(Call<CategoriesRoot> call, Throwable throwable) {
                networkCallback.onFailureResult(throwable.getMessage());
            }
        });
    }

    @Override
    public void listAllCategoriesSimple(NetworkDelegate networkCallback) {
        mealService.listAllCategoriesSimple().enqueue(new Callback<ListAllCategoriesRoot>() {
            @Override
            public void onResponse(Call<ListAllCategoriesRoot> call, Response<ListAllCategoriesRoot> response) {
                if (response.body() != null && response.body().getMeals() != null) {
                    Log.i(TAG, "Simple Categories found: " + response.body().getMeals().size());
                    networkCallback.onSuccessCategoriesSimple(response.body().getMeals());
                } else {
                    networkCallback.onFailureResult("No categories found.");
                }
            }

            @Override
            public void onFailure(Call<ListAllCategoriesRoot> call, Throwable throwable) {
                networkCallback.onFailureResult(throwable.getMessage());
            }
        });
    }



    @Override
    public void listAllAreas(NetworkDelegate networkCallback) {
        mealService.listAllAreas().enqueue(new Callback<ListAllAreaRoot>() {
            @Override
            public void onResponse(Call<ListAllAreaRoot> call, Response<ListAllAreaRoot> response) {
                if (response.body() != null && response.body().getMeals() != null) {
                    Log.i(TAG, "listAllAreas found: " + response.body().getMeals().size());
                    networkCallback.onSuccessArea(response.body().getMeals());
                } else {
                    networkCallback.onFailureResult("No categories found.");
                }
            }

            @Override
            public void onFailure(Call<ListAllAreaRoot> call, Throwable throwable) {
                networkCallback.onFailureResult(throwable.getMessage());
            }
        });
    }

    @Override
    public void listAllIngredients(NetworkDelegate networkCallback) {
        mealService.listAllIngredients().enqueue(new Callback<ListAllIngredientRoot>() {
            @Override
            public void onResponse(Call<ListAllIngredientRoot> call, Response<ListAllIngredientRoot> response) {
                if (response.body() != null && response.body().getMeals() != null) {
                    Log.i(TAG, "listAllIngredients found: " + response.body().getMeals().size());
                    networkCallback.onSuccessIngredients(response.body().getMeals());
                } else {
                    networkCallback.onFailureResult("No categories found.");
                }
            }

            @Override
            public void onFailure(Call<ListAllIngredientRoot> call, Throwable throwable) {
                networkCallback.onFailureResult(throwable.getMessage());
            }
        });
    }

    @Override
    public void filterMealsByCategory(String category, NetworkDelegate networkCallback) {
        mealService.filterMealsByCategory(category).enqueue(new Callback<FilterMealsRoot>() {
            @Override
            public void onResponse(Call<FilterMealsRoot> call, Response<FilterMealsRoot> response) {
                if (response.body() != null && response.body().getMeals() != null) {
                    Log.i(TAG, "filterMealsByCategory found: " + response.body().getMeals().size());
                    networkCallback.onSuccessFilteredMeals(response.body().getMeals());
                } else {
                    networkCallback.onFailureResult("No categories found.");
                }
            }

            @Override
            public void onFailure(Call<FilterMealsRoot> call, Throwable throwable) {
                networkCallback.onFailureResult(throwable.getMessage());

            }
        });
    }

    @Override
    public void filterMealsByArea(String area, NetworkDelegate networkCallback) {
        mealService.filterMealsByArea(area).enqueue(new Callback<FilterMealsRoot>() {
            @Override
            public void onResponse(Call<FilterMealsRoot> call, Response<FilterMealsRoot> response) {
                if (response.body() != null && response.body().getMeals() != null) {
                    Log.i(TAG, "filterMealsByArea found: " + response.body().getMeals().size());
                    networkCallback.onSuccessFilteredMeals(response.body().getMeals());
                } else {
                    networkCallback.onFailureResult("No categories found.");
                }
            }

            @Override
            public void onFailure(Call<FilterMealsRoot> call, Throwable throwable) {

            }
        });
    }

    @Override
    public void filterMealsByIngredient(String ingredient, NetworkDelegate networkCallback) {
        mealService.filterMealsByIngredient(ingredient).enqueue(new Callback<FilterMealsRoot>() {
            @Override
            public void onResponse(Call<FilterMealsRoot> call, Response<FilterMealsRoot> response) {
                if (response.body() != null && response.body().getMeals() != null) {
                    Log.i(TAG, "filterMealsByIngredient found: " + response.body().getMeals().size());
                    networkCallback.onSuccessFilteredMeals(response.body().getMeals());
                } else {
                    networkCallback.onFailureResult("No categories found.");
                }
            }

            @Override
            public void onFailure(Call<FilterMealsRoot> call, Throwable throwable) {
                networkCallback.onFailureResult(throwable.getMessage());

            }
        });
    }
}
