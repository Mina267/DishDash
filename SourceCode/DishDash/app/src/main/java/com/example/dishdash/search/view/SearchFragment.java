package com.example.dishdash.search.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdash.R;
import com.example.dishdash.databinding.FragmentSearchBinding;
import com.example.dishdash.db.MealLocalDataSourceImpl;
import com.example.dishdash.db.MealPlanLocalDataSourceImpl;

import com.example.dishdash.model.Categories;
import com.example.dishdash.model.ListAllArea;
import com.example.dishdash.model.ListAllIngredient;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepositoryImpl;
import com.example.dishdash.model.ShowSnakeBar;
import com.example.dishdash.network.MealRemoteDataSourceImpl;
import com.example.dishdash.search.presenter.SearchPresenter;
import com.example.dishdash.search.presenter.SearchPresenterImpl;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchMealsView, onSearchClickListener {
    private static final String TAG = "SearchFragment";
    /* Recycler Views */
    private RecyclerView recyclerViewCountries;
    private RecyclerView recyclerViewCategories;
    private RecyclerView recyclerViewIngredients;
    /* Adapters */
    private CategoriesSearchAdapter categoriesSearchAdapter;
    private AreasSearchAdapter areasSearchAdapter;
    private IngredientsSearchAdapter ingredientsSearchAdapter;
    /* Layout Managers */
    private LinearLayoutManager categoriesLayoutManager;
    private LinearLayoutManager areasLayoutManager;
    private LinearLayoutManager ingredientsLayoutManager;

    private SearchPresenter searchPresenter;

    private SearchView  searchView;
    private TextView txtViewAllSearch;
    FragmentManager mgr;
    private FragmentSearchBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        txtViewAllSearch = view.findViewById(R.id.txtViewAllSearch);


        /* get Fragment manager */
        mgr = getChildFragmentManager();
        
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /* SearchView
         */
        searchView = view.findViewById(R.id.search_view);

            /* Keep SearchView expanded by default */
            searchView.setIconifiedByDefault(false);
            /* Set the hint text */
            searchView.setQueryHint(getString(R.string.search_hint));

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                searchPresenter.getMealByName(query);
                searchView.setQuery("", false);
                /* Close the keyboard after search */
                InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });


        txtViewAllSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Navigate to SearchFragment */
                NavController navController = Navigation.findNavController((Activity) getActivity(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.action_navigation_search_to_ingredientsFragment);
            }
        });

        /* new instance of adapters */
        categoriesSearchAdapter = new CategoriesSearchAdapter(getContext(), new ArrayList<>(), this);
        areasSearchAdapter = new AreasSearchAdapter(getContext(), new ArrayList<>(), this);
        ingredientsSearchAdapter = new IngredientsSearchAdapter(getContext(), new ArrayList<>(), this);

        /* Recycler Views */
        recyclerViewCategories = view.findViewById(R.id.recyclerViewSearchCategories);
        recyclerViewCountries = view.findViewById(R.id.recyclerViewSearchCountries);
        recyclerViewIngredients = view.findViewById(R.id.recyclerViewSearchIngredients);

        /* new instance of layout managers */
        categoriesLayoutManager = new LinearLayoutManager(  getContext(), RecyclerView.HORIZONTAL, false);
        areasLayoutManager = new LinearLayoutManager(  getContext(), RecyclerView.HORIZONTAL, false);
        ingredientsLayoutManager = new LinearLayoutManager(  getContext(), RecyclerView.HORIZONTAL, false);

        /* new instance of presenter */
        searchPresenter = new SearchPresenterImpl(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext()), MealPlanLocalDataSourceImpl.getInstance(getContext()) ), getContext());

        /* Set recycler views layout managers */
        recyclerViewCategories.setLayoutManager(categoriesLayoutManager);
        recyclerViewCountries.setLayoutManager(areasLayoutManager);
        recyclerViewIngredients.setLayoutManager(ingredientsLayoutManager);

        /* Set recycler views adapters */
        recyclerViewCategories. setAdapter(categoriesSearchAdapter);
        recyclerViewCountries. setAdapter(areasSearchAdapter);
        recyclerViewIngredients.setAdapter(ingredientsSearchAdapter);

        /* Get Data from API */
        searchPresenter.getMealCategories();
        searchPresenter.getMealAreas();
        searchPresenter.getMealIngredients();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    /* View CallBack */
    @Override
    public void showAllCategories(List<Categories> categoriesList) {
        if (categoriesList != null) {
            /* Update the adapter with the new data of categories */
            categoriesSearchAdapter.updateData(categoriesList);
        }
    }

    /* View CallBack */
    @Override
    public void showAllAreas(List<ListAllArea> areasList) {
        if (areasList != null) {
            /* Update the adapter with the new data of Areas */
            areasSearchAdapter.updateData(areasList);
        }
    }

    /* View CallBack */
    @Override
    public void showAllIngredients(List<ListAllIngredient> ingredientList) {
        if (ingredientList != null) {
            /* Update the adapter with the new data of Ingredients */
            ingredientsSearchAdapter.updateData(ingredientList);
        }
    }

    /* View CallBack */
    @Override
    public void showSearchResult(List<Meal> meals) {
        if (meals != null) {
            /* Navigate to SearchResultFragment to result of search */
            Log.i(TAG, "showSearchResult: ");
            NavController navController = Navigation.findNavController((Activity) getActivity(), R.id.nav_host_fragment_activity_main);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("meals", new ArrayList<>(meals));
            if (navController.getCurrentDestination().getId() == R.id.navigation_search) {
                navController.navigate(R.id.action_navigation_search_to_searchResultFragment, bundle);
            }
        }
    }

    @Override
    public void onStartNoNetwork() {
        ShowSnakeBar.customSnackbar(getContext() ,getView(), "No Connection", "", v1 -> {
        }, R.drawable.wifi_off_24px);
        showAlertDialog();

    }

    void showAlertDialog() {
        /* Use the view to find the NavController The
         * propose of using  NavController to navigate to Meal Plan if there is no network connection */
        NavController navController = Navigation.findNavController(requireView());

        /* Create an AlertDialog That */
        new androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setTitle("No Network Connection")
                .setMessage("You are offline. Do you want to view your meal plan or open Wi-Fi settings?")
                .setPositiveButton("Meal Plan", (dialog, which) -> {
                    /* Navigate to the Meal Plan fragment */
                    NavOptions navOptions = new NavOptions.Builder()
                            .setPopUpTo(R.id.navigation_home, true)
                            .build();
                    navController.navigate(R.id.navigation_mealplan, null, navOptions);
                })
                .setNegativeButton("Wi-Fi Settings", (dialog, which) -> {
                    // Open Wi-Fi settings
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                })
                .setCancelable(false)
                .show();
    }




    @Override
    public void newtworkAvailable() {
        ShowSnakeBar.customSnackbar(getContext() ,getView(), "Connected", "", v1 -> {
        }, R.drawable.wifi_24px);
        /* start fetching data */
        searchPresenter.getMealCategories();
        searchPresenter.getMealAreas();
        searchPresenter.getMealIngredients();

    }

    @Override
    public void networkLost() {
        ShowSnakeBar.customSnackbar(getContext() ,getView(), "Network Lost", "", v1 -> {
        }, R.drawable.wifi_off_24px);

    }

    /* Listener CallBack */
    @Override
    public void onAreaClickListener(String area) {
        if (area != null) {
            searchPresenter.getMealByArea(area);
        }
    }

    /* Listener CallBack */
    @Override
    public void onIngredientClickListener(String ingredient) {
        if (ingredient != null) {
            searchPresenter.getMealByIngredient(ingredient);
        }
    }

    /* Listener CallBack */
    @Override
    public void onCategoryClickListener(String category) {
        if (category != null) {
            searchPresenter.getMealByCategory(category);
        }
    }

    /* Utils onResume and onPuase to make sure that it will check for Network every time return to fragment */
    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
        searchPresenter.startMonitoringNetwork();

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
        searchPresenter.stopMonitoringNetwork();
    }
}