package com.example.dishdash;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dishdash.model.Meal;
import com.example.dishdash.network.MealRemoteDataSource;
import com.example.dishdash.network.MealRemoteDataSourceImpl;
import com.example.dishdash.network.NetworkDelegate;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NetworkDelegate {
    private static final String TAG = "MainActivity";
    MealRemoteDataSource mealRemoteDataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mealRemoteDataSource = MealRemoteDataSourceImpl.getInstance();
        mealRemoteDataSource.searchMealsByFirstLetter('a', this);
    }

    @Override
    public void onSuccessResult(List<Meal> mealsList) {
        Log.i(TAG, "onSuccessResult: " + mealsList);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        Log.i(TAG, "onFailureResult: ");
    }
}