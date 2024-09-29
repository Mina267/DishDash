package com.example.dishdash.search.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdash.R;
import com.example.dishdash.databinding.FragmentSearchBinding;
import com.example.dishdash.db.MealLocalDataSourceImpl;
import com.example.dishdash.db.MealPlanLocalDataSourceImpl;

import com.example.dishdash.model.Categories;
import com.example.dishdash.model.ListAllArea;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepositoryImpl;
import com.example.dishdash.network.MealRemoteDataSourceImpl;
import com.example.dishdash.search.presenter.SearchPresenter;
import com.example.dishdash.search.presenter.SearchPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchView, onSearchClickListener {

    RecyclerView recyclerViewCountries;
    RecyclerView recyclerViewCategories;
    SearchPresenter searchPresenter;
    CategoriesSearchAdapter categoriesSearchAdapter;
    AreasSearchAdapter areasSearchAdapter;
    LinearLayoutManager categoriesLayoutManager;
    LinearLayoutManager areasLayoutManager;

    FragmentManager mgr;
    
    private FragmentSearchBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SearchViewModel searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        /* get Fragment manager */
        mgr = getChildFragmentManager();
        
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        searchPresenter = new SearchPresenterImpl(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext()), MealPlanLocalDataSourceImpl.getInstance(getContext()) ));

        categoriesSearchAdapter = new CategoriesSearchAdapter(getContext(), new ArrayList<>(), this);
        areasSearchAdapter = new AreasSearchAdapter(getContext(), new ArrayList<>(), this);

        recyclerViewCategories = view.findViewById(R.id.recyclerViewSearchCategories);
        recyclerViewCountries = view.findViewById(R.id.recyclerViewCountries);

        categoriesLayoutManager = new LinearLayoutManager(  getContext(), RecyclerView.HORIZONTAL, false);
        areasLayoutManager = new LinearLayoutManager(  getContext(), RecyclerView.HORIZONTAL, false);


        searchPresenter = new SearchPresenterImpl(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext()), MealPlanLocalDataSourceImpl.getInstance(getContext()) ));

        recyclerViewCategories.setLayoutManager(categoriesLayoutManager);
        recyclerViewCountries.setLayoutManager(areasLayoutManager);


        recyclerViewCategories. setAdapter(categoriesSearchAdapter);
        recyclerViewCountries. setAdapter(areasSearchAdapter);

        searchPresenter.getMealCategories();
        searchPresenter.getMealAreas();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void showAllCategories(List<Categories> categoriesList) {
        categoriesSearchAdapter.updateData(categoriesList);
    }

    @Override
    public void showAllAreas(List<ListAllArea> areassList) {
        areasSearchAdapter.updateData(areassList);
    }

    @Override
    public void showSearchResult(List<Meal> meals) {
        NavController navController = Navigation.findNavController((Activity) getActivity(), R.id.nav_host_fragment_activity_main);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("meals", new ArrayList<>(meals));
        navController.navigate(R.id.action_navigation_search_to_searchResultFragment, bundle);
    }

    @Override
    public void onAreaClickListener(String area) {
        searchPresenter.getMealByArea(area);
    }

    @Override
    public void onIngredientClickListener(String ingredient) {
        searchPresenter.getMealByIngredient(ingredient);
    }

    @Override
    public void onCategoryClickListener(String category) {
        searchPresenter.getMealByCategory(category);
    }


}