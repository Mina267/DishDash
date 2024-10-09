package com.example.dishdash.searchresult.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.dishdash.R;
import com.example.dishdash.db.MealLocalDataSourceImpl;
import com.example.dishdash.db.MealPlanLocalDataSourceImpl;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepositoryImpl;
import com.example.dishdash.model.ShowSnakeBar;
import com.example.dishdash.network.MealRemoteDataSourceImpl;
import com.example.dishdash.searchresult.presenter.SearchResultPresenter;
import com.example.dishdash.searchresult.presenter.SearchResultPresenterImpl;

import java.util.ArrayList;
import java.util.List;


public class SearchResultFragment extends Fragment implements SearchResultView, OnSearchResultClickListener {
    private static final String TAG = "SearchResultFragment";
    private Button btnIngredients;
    private List<Meal> mealsList;
    private SearchResultAdapter searchResultAdapter;
    private RecyclerView recyclerViewSearchResult;
    private GridLayoutManager gridLayoutManager;
    private FragmentManager mgr;
    private SearchResultPresenter searchResultPresenter;
    private TextView txtSearchResult;
    private Boolean searchStatus = false;

    public SearchResultFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);

        /* Retrieve the meal list passed from the previous fragment*/
        mealsList = getArguments().getParcelableArrayList("meals");

        btnIngredients = view.findViewById(R.id.btnIngredients);

        /* Get Fragment manager*/
        mgr = getChildFragmentManager();

        // Handle back button action
        setHasOptionsMenu(true);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





        SearchView searchView = view.findViewById(R.id.search_view);
        /* Sets the default or resting state of the search field.
         * If true, a single search icon is shown by default and expands to show the text field and other buttons when pressed. */
        /* Keep SearchView expanded by default by using false */
        searchView.setIconifiedByDefault(false);
        /* Set the hint text */
        searchView.setQueryHint(getString(R.string.search_hint));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                /* Returning false here means the search query has not been handled, and the default behavior (such as hiding the keyboard) will occur.
                 * no action will happen in the submits
                 */
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                /* Check if the input is not empty before making a network call */
                if (!newText.isEmpty()) {
                    /* Call the presenter to fetch meals as the user types */
                    searchResultPresenter.getMealByName(newText);
                }
                /* Return true to indicate the query has been handled
                 * Means I handle the logic of the search not use the default behavior
                 */
                return true;
            }
        });





        txtSearchResult = view.findViewById(R.id.txtSearchResult);
        recyclerViewSearchResult = view.findViewById(R.id.recyclerViewSearchResult);

        searchResultAdapter = new SearchResultAdapter(getContext(), new ArrayList<>(), this);

        /* 2 columns grid */
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerViewSearchResult.setLayoutManager(gridLayoutManager);
        recyclerViewSearchResult.setAdapter(searchResultAdapter);


        /* Check if meals list is empty or null so that Show the text "No Search result" */
        if (mealsList == null || mealsList.isEmpty()) {
            txtSearchResult.setVisibility(View.VISIBLE);
            recyclerViewSearchResult.setVisibility(View.GONE);
        } else {
            txtSearchResult.setVisibility(View.GONE);
            searchResultAdapter.updateData(mealsList);
            searchStatus = true;
        }

        /* Initialize the presenter */
        searchResultPresenter = new SearchResultPresenterImpl(
                this, MealRepositoryImpl.getInstance(
                MealRemoteDataSourceImpl.getInstance(),
                MealLocalDataSourceImpl.getInstance(getContext()),
                MealPlanLocalDataSourceImpl.getInstance(getContext())
        ));


        btnIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);

                NavOptions navOptionsSearch = new NavOptions.Builder()
                        .setPopUpTo(R.id.searchResultFragment, true)  // This will pop searchResultFragment
                        .build();
                navController.navigate(R.id.ingredientsFragment, null, navOptionsSearch);

            }
        });


        /* Mark the saved meals for the adapter to use with favorite floating button */
        searchResultPresenter.getSavedMeals();

    }

    @Override
    public void showSearchResult(List<Meal> meals) {
        Log.i(TAG, "showSearchResult: " + meals);
         if (!searchStatus) {
             /* Check if meals list is empty or null so that remove the text "No Search result" */
            txtSearchResult.setVisibility(View.GONE);
            /* Show the recycler view */
            recyclerViewSearchResult.setVisibility(View.VISIBLE);
            /* Update the adapter with the new search results */
            searchResultAdapter.updateData(meals);
            /* Set the search status to true so that text "No Search result" is not shown */
            searchStatus = true;
        }
        else {
            searchResultAdapter.updateData(meals);
        }

    }

    @Override
    public void onFailureSearch(String errorMsg) {
        /* Hide the recycler view */
        txtSearchResult.setVisibility(View.VISIBLE);
        /* Show the text "No Search result" */
        recyclerViewSearchResult.setVisibility(View.GONE);
        /* Set the search status to false so that text "No Search result" is shown */
        searchStatus = false;
    }

    public void markSavedMeals(LiveData<List<Meal>> meals) {
        /* Update the adapter with the saved meals */
        Observer<List<Meal>> observer = new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> mealsList) {
                Log.i(TAG, "onChanged: ");
                if (mealsList != null)
                {
                    /* Mark the saved meals for the adapter to use with favorite floating button */
                    searchResultAdapter.setSavedMeals(mealsList);
                }

            }
        };

        meals.observe(this, observer);
    }




    @Override
    public void onAddToFavoriteClick(Meal meal) {
        searchResultPresenter.addToFavourite(meal);
        ShowSnakeBar.customSnackbar(getContext() ,getView(), "Added to Favorites", "VIEW", v1 -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);;
            NavOptions navOptions = new NavOptions.Builder()
                    .setPopUpTo(R.id.navigation_home, true)
                    .build();
            navController.navigate(R.id.navigation_fav, null, navOptions);
        }, R.drawable.check_circle_24px);

    }

    @Override
    public void onRemoveFromFavoriteClick(Meal meal) {
        searchResultPresenter.deleteMeal(meal);


        ShowSnakeBar.customSnackbar(getContext() ,getView(), " Removed from Favorites", "Undo", v1 -> {
            searchResultPresenter.addToFavourite(meal);
        }, R.drawable.ic_delete);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Check if the clicked item is the back arrow (home button)
        if (item.getItemId() == android.R.id.home) {
            // Handle back arrow press - navigate up
            NavController navController = Navigation.findNavController(getView());
            navController.navigateUp();
            return true; // Indicate that the event was handled
        }
        return super.onOptionsItemSelected(item); // Let other menu items be handled normally
    }


}