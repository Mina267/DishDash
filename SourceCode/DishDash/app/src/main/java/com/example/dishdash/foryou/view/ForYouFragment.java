package com.example.dishdash.foryou.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dishdash.R;
import com.example.dishdash.databinding.FragmentHomeBinding;
import com.example.dishdash.db.MealLocalDataSource;
import com.example.dishdash.db.MealLocalDataSourceImpl;
import com.example.dishdash.db.MealPlanLocalDataSource;
import com.example.dishdash.db.MealPlanLocalDataSourceImpl;
import com.example.dishdash.model.Categories;
import com.example.dishdash.model.FilterMeals;
import com.example.dishdash.model.ListAllArea;
import com.example.dishdash.model.ListAllCategories;
import com.example.dishdash.model.ListAllIngredient;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealPlan;
import com.example.dishdash.model.MealPlanJunction;
import com.example.dishdash.network.MealRemoteDataSource;
import com.example.dishdash.network.MealRemoteDataSourceImpl;
import com.example.dishdash.network.NetworkDelegate;

import java.util.List;

public class ForYouFragment extends Fragment  implements NetworkDelegate {
    private static final String TAG = "ForYouFragment";
    private FragmentHomeBinding binding;
    MealRemoteDataSource mealRemoteDataSource;
    ImageView imageView;
    ImageView imageView2;
    MealLocalDataSource mealLocalDataSource;
    MealPlanLocalDataSource mealPlanLocalDataSource;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ForYouViewModel forYouViewModel =
                new ViewModelProvider(this).get(ForYouViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();






        mealRemoteDataSource = MealRemoteDataSourceImpl.getInstance();
        mealRemoteDataSource.searchMealsByName("Arrabiata", this);
        mealRemoteDataSource.searchMealsByFirstLetter('a', this);
        mealRemoteDataSource.listAllCategories(this);
        mealRemoteDataSource.filterMealsByCategory("Seafood", this);
        mealRemoteDataSource.filterMealsByArea("Canadian", this);
        mealRemoteDataSource.filterMealsByIngredient("chicken_breast", this);
        mealRemoteDataSource.listAllAreas(this);
        mealRemoteDataSource.listAllCategoriesSimple(this);
        mealRemoteDataSource.listAllIngredients(this);


        imageView = root.findViewById(R.id.imageView);
        imageView2 = root.findViewById(R.id.imageView2);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealLocalDataSource = MealLocalDataSourceImpl.getInstance(getContext());
        mealPlanLocalDataSource = MealPlanLocalDataSourceImpl.getInstance(getContext());


    }

    @Override
    public void onSuccessMeals(List<Meal> mealsList) {
        Log.i(TAG, "onSuccessMeals: " + mealsList);
        Log.i(TAG, "onSuccessMeals: " + mealsList.get(0).getStrCategory());

        Glide.with(getContext())
                .load(mealsList.get(0).getStrMealThumb())
                .apply(new RequestOptions().override(200, 200)
                        .placeholder(R.drawable.nophotoavailable)
                        .error(R.drawable.ic_launcher_foreground))
                .into(imageView);
        //favoriteMeal = new FavoriteMeal(mealsList.get(0)) ;
        mealLocalDataSource.insertMeal(mealsList.get(0));


        MealPlan mealPlan = new MealPlan();
        mealPlan.setDayName("Monday");
        mealPlanLocalDataSource.insertPlanMealForDay(mealPlan);

        MealPlanJunction junction = new MealPlanJunction(mealPlan.getIdDay(), mealsList.get(0).getIdMeal());

        mealPlanLocalDataSource.insertMealDayJunction(junction);

        LiveData<List<Meal>> mealss = mealPlanLocalDataSource.getMealsOfTheDay(mealPlan.idDay);

        //Log.i(TAG, "onSuccessMeals: " + mealsList.get(0).getStrCategory());

    }

    @Override
    public void onSuccessCategories(List<Categories> categoriesList) {
        Log.i(TAG, "onSuccessCategories: ");
        Log.i(TAG, "onSuccessCategories: " + categoriesList);
        Log.i(TAG, "onSuccessCategories: " + categoriesList.get(0).getStrCategory());

    }

    @Override
    public void onSuccessCategoriesSimple(List<ListAllCategories> categoriesList) {
        Log.i(TAG, "onSuccessCategoriesSimple: ");
        Log.i(TAG, "onSuccessCategories: " + categoriesList);
        Log.i(TAG, "onSuccessCategories: " + categoriesList.get(0).getStrCategory());
    }

    @Override
    public void onSuccessArea(List<ListAllArea> AreaList) {
        Log.i(TAG, "onSuccessArea: ");
        Log.i(TAG, "onSuccessArea: " + AreaList);
        Log.i(TAG, "onSuccessArea: " + AreaList.get(0).getStrArea());
    }

    @Override
    public void onSuccessIngredients(List<ListAllIngredient> IngredientsList) {
        Log.i(TAG, "onSuccessIngredients: ");
        Log.i(TAG, "onSuccessIngredients: " + IngredientsList);
        Log.i(TAG, "onSuccessIngredients: " + IngredientsList.get(0).getStrIngredient());
    }

    @Override
    public void onSuccessFilteredMeals(List<FilterMeals> FilterMealsList) {
        Log.i(TAG, "onSuccessFilteredMeals: ");
        Log.i(TAG, "onSuccessFilteredMeals: " + FilterMealsList);
        Log.i(TAG, "onSuccessFilteredMeals: " + FilterMealsList.get(0).getStrMeal());

        Glide.with(getContext())
                .load(FilterMealsList.get(0).getStrMealThumb())
                .apply(new RequestOptions().override(200, 200)
                        .placeholder(R.drawable.nophotoavailable)
                        .error(R.drawable.ic_launcher_foreground))
                .into(imageView2);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        Log.i(TAG, "onFailureResult: ");
    }
}

