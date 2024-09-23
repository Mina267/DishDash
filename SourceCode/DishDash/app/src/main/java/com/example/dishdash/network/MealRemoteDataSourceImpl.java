package com.example.dishdash.network;

import android.util.Log;

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
                    networkCallback.onSuccessResult(response.body().getMeals());
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
                    networkCallback.onSuccessResult(response.body().getMeals());
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
                    networkCallback.onSuccessResult(response.body().getMeals());
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
}
