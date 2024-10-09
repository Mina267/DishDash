package com.example.dishdash.favrecipes.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dishdash.R;
import com.example.dishdash.databinding.FragmentFavRecipesBinding;

import com.example.dishdash.db.MealLocalDataSourceImpl;
import com.example.dishdash.db.MealPlanLocalDataSourceImpl;
import com.example.dishdash.favrecipes.presenter.FavRecipesPresenter;
import com.example.dishdash.favrecipes.presenter.FavRecipesPresenterImpl;

import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepositoryImpl;
import com.example.dishdash.model.ShowSnakeBar;
import com.example.dishdash.network.MealRemoteDataSourceImpl;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class FavRecipesFragment extends Fragment implements FavRecipesView, OnFavoriteClickListener {

    private FragmentFavRecipesBinding binding;
    FavRecipesAdapter favRecipesAdapter;
    RecyclerView favRecyclerView;
    GridLayoutManager gridLayoutManager;
    FragmentManager mgr;
    FavRecipesPresenter favRecipesPresenter;

    public FavRecipesFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentFavRecipesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /* get Fragment manager */
        mgr = getChildFragmentManager();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favRecyclerView = view.findViewById(R.id.favRecyclerView);

        favRecipesAdapter = new FavRecipesAdapter(getContext(), new ArrayList<>(), this);

        /* 2 columns grid */
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        favRecyclerView.setLayoutManager(gridLayoutManager);

        favRecyclerView.setAdapter(favRecipesAdapter);

        favRecipesPresenter = new FavRecipesPresenterImpl(
                this, MealRepositoryImpl.getInstance(
                MealRemoteDataSourceImpl.getInstance(),
                MealLocalDataSourceImpl.getInstance(getContext()),
                MealPlanLocalDataSourceImpl.getInstance(getContext())
        )
        );

        favRecipesPresenter.getFavMeals();
    }
    
    

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void showData(LiveData<List<Meal>> meals) {
        Observer<List<Meal>> observer = new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                favRecipesAdapter.updateData(meals);
                favRecipesAdapter.notifyDataSetChanged();
            }
        };

        meals.observe(this, observer);
    }



    @Override
    public void onFavMealClick(Meal meal) {
        /* Remove the meal and notify the adapter */
        favRecipesPresenter.deleteMeal(meal);
        favRecipesAdapter.notifyDataSetChanged();


        /* Show a Snackbar with undo option */
        ShowSnakeBar.customSnackbar(getContext() ,getView(),   "Meal Deleted", "Undo", v1 -> {
            /* Undo the delete action  */
            favRecipesPresenter.addToFav(meal);
            favRecipesAdapter.notifyDataSetChanged();
            ShowSnakeBar.customSnackbar(getContext() ,getView(),   "Meal Restored", "", v2 -> {
            }, R.drawable.check_circle_24px);
        }, R.drawable.ic_delete);



    }




}