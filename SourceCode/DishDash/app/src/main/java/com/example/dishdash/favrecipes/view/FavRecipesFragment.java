package com.example.dishdash.favrecipes.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dishdash.R;
import com.example.dishdash.databinding.FragmentFavRecipesBinding;
import com.example.dishdash.databinding.FragmentMealplanBinding;
import com.example.dishdash.databinding.FragmentSearchBinding;
import com.example.dishdash.mealplan.view.MealPlanViewModel;


public class FavRecipesFragment extends Fragment {

    private FragmentFavRecipesBinding binding;


    public FavRecipesFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MealPlanViewModel notificationsViewModel =
                new ViewModelProvider(this).get(MealPlanViewModel.class);

        binding = FragmentFavRecipesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}