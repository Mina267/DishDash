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

    /* The expression language lets you write expressions that handle events dispatched by views.
     * The Data Binding Library automatically generates the classes required to bind the views in the layout with your data objects.
     * A binding class is generated for each layout file.
     * By default, the name of the class is based on the name of the layout file, converted to Pascal case,
     * with the Binding suffix added to it. For example, the preceding layout filename is activity_main.xml,
     * so the corresponding generated binding class is ActivityMainBinding.
     */
    private ActivityMainBinding binding;
    private static final String TAG = "MainActivity";
    NetworkConnectionStatus connectionStatus;
    /* used to manage app navigation between fragments. */
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* binding: View binding is used to access views in XML layouts without needing findViewById().
         * This field references the root layout of the activity. */
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /* Bottom navigation bars make it easy for users to explore and switch between top-level views in a single tap */
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Set up AppBarConfiguration with top-level destinations
        /*  This defines the top-level destinations, which are fragments that are accessible
         * directly from the bottom navigation view (Home, Search, Meal Plan, Favorites).*/
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_mealplan, R.id.navigation_fav)
                .build();


        /*
         * It holds the navigation graph and exposes methods that allow your app to move between the destinations in the graph.
         * It tracks which destinations the user has visited, and allows the user to move between destinations.
         *
         */
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        /*  NavigationUI class. This class contains static methods that manage navigation with the top app bar, the navigation drawer, and bottom navigation.
         *  (Top app bar)
         */
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        /* This line links the BottomNavigationView (which shows the bottom navigation menu) with the NavController,
         * allowing users to navigate between fragments via the bottom menu.
         * The selected item in the NavigationBarView will automatically be updated when the destination changes.
         */
        NavigationUI.setupWithNavController(binding.navView, navController);

        /* Create and set an OnDestinationChangedListener */
        /* Callback for when the currentDestination or its arguments change. This navigation may be to a destination that has not been seen before,
         * or one that was previously on the back stack. This method is called after navigation is complete,
         * but associated transitions may still be playing.
         */
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
        /* Get the root view for the Snackbar to attach
         * android.R.id.content gives you the root element of a view, without having to know its actual name/type/ID
         * refers to the root view of the activity's entire layout, which makes it a good candidate for anchoring a Snackbar.
         */
        View rootView = findViewById(android.R.id.content);

        /* Show Snackbar with action to navigate to MealPlanFragment */
        /* Snackbar.make(): This creates a Snackbar message that will be displayed to the user.
         * rootView: The root view in which the Snackbar will be shown.
         *  "Meal added to " + info: The message displayed,
         * Snackbar.LENGTH_LONG: Specifies that the Snackbar will be shown for a long duration (about 3.5 seconds).
         */

        Snackbar.make(rootView, "Meal added to " + info, Snackbar.LENGTH_LONG)
                .setAction("view meal plan", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NavOptions navOptions = new NavOptions.Builder()
                                .setPopUpTo(R.id.navigation_home, true)
                                .build();

                        navController.navigate(R.id.navigation_mealplan, null, navOptions);


                    } /* from material begin website */
                }).setBackgroundTint(getResources().getColor(R.color.cyan_900)) // Set custom background color
                .setActionTextColor(getResources().getColor(R.color.blue_grey_50)) // Set custom action text color
                .show();
    }








}
