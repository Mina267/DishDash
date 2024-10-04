package com.example.dishdash;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.example.dishdash.network.NetworkConnectionStatus;
import com.example.dishdash.selectday.view.Communicator;
import com.example.dishdash.selectday.view.SelectDayFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.dishdash.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements Communicator {

    private ActivityMainBinding binding;
    private static final String TAG = "MainActivity";
    NetworkConnectionStatus connectionStatus;

    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Set up AppBarConfiguration with top-level destinations
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_mealplan, R.id.navigation_fav)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        /* Create and set an OnDestinationChangedListener */
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(NavController controller, androidx.navigation.NavDestination destination, Bundle arguments) {
                /* Check if the destination is the detailsRecipesFragment */
                if (destination.getId() == R.id.detailsRecipesFragment ||
                        destination.getId() == R.id.searchResultFragment ||
                        destination.getId() == R.id.ingredientsFragment ||
                        destination.getId() == R.id.selectDayFragment){
                    /* Hide BottomNavigationView when on the detailed fragment */
                    navView.setVisibility(View.GONE);
                } else {
                    /* Show BottomNavigationView for all other fragments */
                    navView.setVisibility(View.VISIBLE);
                }
            }
        });


    }


    @Override
    public void viewData(String info) {
        // Get the root view for the Snackbar to attach
        View rootView = findViewById(android.R.id.content);

        // Show Snackbar with action to navigate to MealPlanFragment
        Snackbar.make(rootView, "Meal added to " + info, Snackbar.LENGTH_LONG)
                .setAction("view meal plan", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NavOptions navOptions = new NavOptions.Builder()
                                .setPopUpTo(R.id.navigation_home, true)
                                .build();

                        navController.navigate(R.id.navigation_mealplan, null, navOptions);


                    }
                }).setBackgroundTint(getResources().getColor(R.color.cyan_900)) // Set custom background color
                .setActionTextColor(getResources().getColor(R.color.blue_grey_50)) // Set custom action text color
                .show();
    }








}
