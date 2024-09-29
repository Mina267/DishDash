package com.example.dishdash.ingredients.view;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dishdash.R;
import com.example.dishdash.db.MealLocalDataSourceImpl;
import com.example.dishdash.db.MealPlanLocalDataSourceImpl;
import com.example.dishdash.ingredients.presenter.IngredientsPresenter;
import com.example.dishdash.ingredients.presenter.IngredientsPresenterImpl;
import com.example.dishdash.model.ListAllIngredient;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepositoryImpl;
import com.example.dishdash.network.MealRemoteDataSourceImpl;

import java.util.ArrayList;
import java.util.List;


public class IngredientsFragment extends Fragment implements IngredientsMealsView, onIngredientsClickListener {
    private static final String TAG = "IngredientsFragment";
    RecyclerView recyclerViewIngredients;
    IngredientsAdapter ingredientsAdapter;
    GridLayoutManager ingredientsLayoutManager;
    IngredientsPresenter ingredientsPresenter;
    FragmentManager mgr;


    public IngredientsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);
        /* get Fragment manager */
        mgr = getChildFragmentManager();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ingredientsPresenter = new IngredientsPresenterImpl(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext()), MealPlanLocalDataSourceImpl.getInstance(getContext()) ));
        ingredientsAdapter = new IngredientsAdapter(getContext(), new ArrayList<>(), this);
        recyclerViewIngredients = view.findViewById(R.id.recyclerViewIngredientsList);
        ingredientsLayoutManager = new GridLayoutManager(getContext(), 4);
        recyclerViewIngredients.setLayoutManager(ingredientsLayoutManager);
        recyclerViewIngredients.setAdapter(ingredientsAdapter);
        ingredientsPresenter.getMealIngredients();


    }

    @Override
    public void showAllIngredients(List<ListAllIngredient> ingredientList) {
        ingredientsAdapter.updateData(ingredientList);

    }

    @Override
    public void showSearchResult(List<Meal> meal) {
        Log.i(TAG, "showSearchResult: ");
        NavController navController = Navigation.findNavController((Activity) getActivity(), R.id.nav_host_fragment_activity_main);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("meals", new ArrayList<>(meal));
        navController.navigate(R.id.action_ingredientsFragment_to_searchResultFragment, bundle);
    }

    @Override
    public void onIngredientClickListener(String ingredient) {
        ingredientsPresenter.getMealByIngredient(ingredient);

    }
}