package com.example.dishdash.searchresult.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dishdash.R;
import com.example.dishdash.db.MealLocalDataSourceImpl;
import com.example.dishdash.db.MealPlanLocalDataSourceImpl;
import com.example.dishdash.favrecipes.presenter.FavRecipesPresenterImpl;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepositoryImpl;
import com.example.dishdash.network.MealRemoteDataSourceImpl;
import com.example.dishdash.searchresult.presenter.SearchResultPresenter;
import com.example.dishdash.searchresult.presenter.SearchResultPresenterImpl;

import java.util.ArrayList;
import java.util.List;


public class SearchResultFragment extends Fragment implements SearchResultView, OnSearchResultClickListener {


    private List<Meal> meals;
    SearchResultAdapter searchResultAdapter;
    RecyclerView recyclerViewSearchResult;
    GridLayoutManager gridLayoutManager;
    FragmentManager mgr;
    SearchResultPresenter searchResultPresenter;


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

        // Retrieve the meal list passed from the previous fragment
        meals = getArguments().getParcelableArrayList("meals");

        // Get Fragment manager
        mgr = getChildFragmentManager();

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewSearchResult = view.findViewById(R.id.recyclerViewSearchResult);
        searchResultAdapter = new SearchResultAdapter(getContext(), meals, this);

        /* 2 columns grid */
        gridLayoutManager = new GridLayoutManager(getContext(), 2);

        recyclerViewSearchResult.setLayoutManager(gridLayoutManager);
        recyclerViewSearchResult.setAdapter(searchResultAdapter);

        searchResultPresenter = new SearchResultPresenterImpl(
                this, MealRepositoryImpl.getInstance(
                MealRemoteDataSourceImpl.getInstance(),
                MealLocalDataSourceImpl.getInstance(getContext()),
                MealPlanLocalDataSourceImpl.getInstance(getContext())
        )
        );
    }

    @Override
    public void showData(LiveData<List<Meal>> meals) {

    }

    @Override
    public void onMealMarkClick(Meal meal) {
        searchResultPresenter.addToFav(meal);
    }
}