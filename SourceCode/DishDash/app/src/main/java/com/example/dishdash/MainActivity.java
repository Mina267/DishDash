package com.example.dishdash;

import android.os.Bundle;
import android.util.Log;

import com.example.dishdash.model.Meal;
import com.example.dishdash.network.MealRemoteDataSource;
import com.example.dishdash.network.MealRemoteDataSourceImpl;
import com.example.dishdash.network.NetworkDelegate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.dishdash.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NetworkDelegate {

    private ActivityMainBinding binding;
    private static final String TAG = "MainActivity";
    MealRemoteDataSource mealRemoteDataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_mealplan)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        mealRemoteDataSource = MealRemoteDataSourceImpl.getInstance();
        mealRemoteDataSource.searchMealsByName("Arrabiata", this);
        mealRemoteDataSource.searchMealsByFirstLetter('a', this);
    }


    @Override
    public void onSuccessResult(List<Meal> mealsList) {
        Log.i(TAG, "onSuccessResult: " + mealsList);
        Log.i(TAG, "onSuccessResult: " + mealsList.get(0).getStrCategory());

    }

    @Override
    public void onFailureResult(String errorMsg) {
        Log.i(TAG, "onFailureResult: ");
    }

}