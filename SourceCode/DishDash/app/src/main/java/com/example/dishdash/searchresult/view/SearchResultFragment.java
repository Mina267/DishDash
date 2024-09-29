package com.example.dishdash.searchresult.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dishdash.R;
import com.example.dishdash.db.MealLocalDataSourceImpl;
import com.example.dishdash.db.MealPlanLocalDataSourceImpl;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepositoryImpl;
import com.example.dishdash.network.MealRemoteDataSourceImpl;
import com.example.dishdash.searchresult.presenter.SearchResultPresenter;
import com.example.dishdash.searchresult.presenter.SearchResultPresenterImpl;

import java.util.ArrayList;
import java.util.List;


public class SearchResultFragment extends Fragment implements SearchResultView, OnSearchResultClickListener {
    private static final String TAG = "SearchResultFragment";

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

        /* Get Fragment manager*/
        mgr = getChildFragmentManager();

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





        SearchView searchView = view.findViewById(R.id.search_view);

        /* Keep SearchView expanded by default */
        searchView.setIconifiedByDefault(false);
        /* Set the hint text */
        searchView.setQueryHint(getString(R.string.search_hint));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                searchResultPresenter.getMealByName(query);
                searchView.setQuery("", false);
                /* Close the keyboard after search */
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });











        txtSearchResult = view.findViewById(R.id.txtSearchResult);
        recyclerViewSearchResult = view.findViewById(R.id.recyclerViewSearchResult);

        searchResultAdapter = new SearchResultAdapter(getContext(), new ArrayList<>(), this);

        /* 2 columns grid */
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerViewSearchResult.setLayoutManager(gridLayoutManager);
        recyclerViewSearchResult.setAdapter(searchResultAdapter);


        /* Check if meals list is empty or null */
        if (mealsList == null || mealsList.isEmpty()) {
            txtSearchResult.setVisibility(View.VISIBLE);
            recyclerViewSearchResult.setVisibility(View.GONE);
        } else {
            txtSearchResult.setVisibility(View.GONE);
            searchResultAdapter.updateData(mealsList);
            searchStatus = true;
        }

        searchResultPresenter = new SearchResultPresenterImpl(
                this, MealRepositoryImpl.getInstance(
                MealRemoteDataSourceImpl.getInstance(),
                MealLocalDataSourceImpl.getInstance(getContext()),
                MealPlanLocalDataSourceImpl.getInstance(getContext())
        ));
    }

    @Override
    public void showSearchResult(List<Meal> meals) {
        Log.i(TAG, "showSearchResult: " + meals);
         if (!searchStatus) {
            txtSearchResult.setVisibility(View.GONE);
            recyclerViewSearchResult.setVisibility(View.VISIBLE);
            searchResultAdapter.updateData(meals);
            searchStatus = true;
        }
        else {
            searchResultAdapter.updateData(meals);
        }

    }

    @Override
    public void onFailureSearch(String errorMsg) {
        txtSearchResult.setVisibility(View.VISIBLE);
        recyclerViewSearchResult.setVisibility(View.GONE);
        searchStatus = false;

    }

    @Override
    public void onMealMarkClick(Meal meal) {
        searchResultPresenter.addToFav(meal);
        Toast.makeText(getContext(), meal.getStrMeal() + "Add to Favorite", Toast.LENGTH_SHORT).show();
    }
}