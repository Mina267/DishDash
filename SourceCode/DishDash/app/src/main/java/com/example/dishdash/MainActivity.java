package com.example.dishdash;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.dishdash.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String TAG = "MainActivity";

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

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);



        /* Create and set an OnDestinationChangedListener */
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(NavController controller, androidx.navigation.NavDestination destination, Bundle arguments) {
                /* Check if the destination is the detailsRecipesFragment */
                if (destination.getId() == R.id.detailsRecipesFragment || destination.getId() == R.id.searchResultFragment) {
                    /* Hide BottomNavigationView when on the detailed fragment */
                    navView.setVisibility(View.GONE);
                } else {
                    /* Show BottomNavigationView for all other fragments */
                    navView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
