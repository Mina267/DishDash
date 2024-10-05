package com.example.dishdash.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.dishdash.model.CategoriesRoot;
import com.example.dishdash.model.FilterMealsRoot;
import com.example.dishdash.model.ListAllAreaRoot;
import com.example.dishdash.model.ListAllCategoriesRoot;
import com.example.dishdash.model.ListAllIngredientRoot;
import com.example.dishdash.model.MealsRoot;

import okhttp3.ResponseBody;
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
    public static final String CATOGERY_TYPE = "CATOGERY";
    public static final String AREA_TYPE = "AREA";
    public static final String INGREDIENT_TYPE = "INGREDIENT";

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
                    networkCallback.onSuccessMealId(response.body().getMeals().get(0));
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

    /* lookup a random meal */
    @Override
    public void lookupRandomMeal(NetworkDelegate networkCallback) {
        mealService.lookupRandomMeal().enqueue(new Callback<MealsRoot>() {
            @Override
            public void onResponse(Call<MealsRoot> call, Response<MealsRoot> response) {
                if (response.body() != null && response.body().getMeals() != null) {
                    Log.i(TAG, "Random meal found");
                    networkCallback.onSuccessRandomMeals(response.body().getMeals());
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

    /*  list all meal categories */
    @Override
    public void listAllCategories(NetworkDelegate networkCallback) {
        Log.i(TAG, "listAllCategories: ");
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
   /*  list all meal categories simple */
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


    /*  list all areas */
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

    /*  list all ingredients */
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

    /*  filter meals by category */
    @Override
    public void filterMealsByCategory(String category, NetworkDelegate networkCallback) {
        mealService.filterMealsByCategory(category).enqueue(new Callback<FilterMealsRoot>() {
            @Override
            public void onResponse(Call<FilterMealsRoot> call, Response<FilterMealsRoot> response) {
                /* check if the response is successful and the body is not null and the meals list is not null */
                if (response.body() != null && response.body().getMeals() != null) {
                    Log.i(TAG, "filterMealsByCategory found: " + response.body().getMeals().size());
                    networkCallback.onSuccessFilteredMeals(response.body().getMeals(), CATOGERY_TYPE);
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

    /*  filter meals by area */
    @Override
    public void filterMealsByArea(String area, NetworkDelegate networkCallback) {
        mealService.filterMealsByArea(area).enqueue(new Callback<FilterMealsRoot>() {
            @Override
            public void onResponse(Call<FilterMealsRoot> call, Response<FilterMealsRoot> response) {
                /* check if the response is successful and the body is not null and the meals list is not null */
                if (response.body() != null && response.body().getMeals() != null) {
                    Log.i(TAG, "filterMealsByArea found: " + response.body().getMeals().size());
                    networkCallback.onSuccessFilteredMeals(response.body().getMeals(), AREA_TYPE);
                } else {
                    networkCallback.onFailureResult("No categories found.");
                }
            }

            @Override
            public void onFailure(Call<FilterMealsRoot> call, Throwable throwable) {

            }
        });
    }

    /*  filter meals by ingredient */
    @Override
    public void filterMealsByIngredient(String ingredient, NetworkDelegate networkCallback) {
        mealService.filterMealsByIngredient(ingredient).enqueue(new Callback<FilterMealsRoot>() {
            @Override
            public void onResponse(Call<FilterMealsRoot> call, Response<FilterMealsRoot> response) {
                if (response.body() != null && response.body().getMeals() != null) {
                    Log.i(TAG, "filterMealsByIngredient found: " + response.body().getMeals().size());
                    networkCallback.onSuccessFilteredMeals(response.body().getMeals(), INGREDIENT_TYPE);
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

    /*  fetch ingredient image use for ingredient image */
    public void fetchIngredientImage(String ingredientName, NetworkDelegate networkCallback) {
        String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredientName + "-Small.png";

        mealService.getIngredientImage(imageUrl).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null ) {
                    Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                    networkCallback.onSuccessIngredientImage(bitmap, ingredientName);
                } else {
                    Log.e(TAG, "fetchIngredientImage:Failed to fetch image: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                Log.e(TAG, "Error: " + throwable.getMessage());
                networkCallback.onFailureResult(throwable.getMessage());
            }
        });
    }
}
