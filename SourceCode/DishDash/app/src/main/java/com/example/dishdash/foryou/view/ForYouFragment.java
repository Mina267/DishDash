package com.example.dishdash.foryou.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdash.R;
import com.example.dishdash.databinding.FragmentHomeBinding;
import com.example.dishdash.db.MealLocalDataSourceImpl;
import com.example.dishdash.db.MealPlanLocalDataSourceImpl;
import com.example.dishdash.foryou.presenter.ForYouPresenter;
import com.example.dishdash.foryou.presenter.ForYouPresenterImpl;
import com.example.dishdash.mealplan.view.DaysAdapter;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepositoryImpl;
import com.example.dishdash.network.MealRemoteDataSourceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ForYouFragment extends Fragment  implements OnMealClickListener, ForYouView {
    private static final String TAG = "ForYouFragment";
    private FragmentHomeBinding binding;

    BannerAdapter bannerAdapter;
    RecyclerView foryouRecyclerView;
    RecyclerView foryou_recycler_egptian;
    RecyclerView foryou_recycler_vegan;
    RecyclerView foryou_recycler_recommended;


    LinearLayoutManager layoutManager;
    FragmentManager mgr;
    ForYouPresenter forYouPresenter;
    private static final int EGY_ADAPTER = 0;
    private static final int VEG_ADAPTER = 1;
    private static final int REC_ADAPTER = 2;
    List<ForYouAdapter> forYouAdapter = new ArrayList<ForYouAdapter>();;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ForYouViewModel forYouViewModel =
                new ViewModelProvider(this).get(ForYouViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();



        /* get Fragment manager */
        mgr = getChildFragmentManager();





        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        foryouRecyclerView = view.findViewById(R.id.foryou_recycler);
//        foryou_recycler_egptian = view.findViewById(R.id.foryou_recycler_egptian);
//        foryou_recycler_vegan = view.findViewById(R.id.foryou_recycler_vegan);
        foryou_recycler_recommended = view.findViewById(R.id.foryou_recycler_recommended);



        bannerAdapter = new BannerAdapter(getContext(), new ArrayList<>(), this);
        layoutManager = new LinearLayoutManager(  getContext(), RecyclerView.HORIZONTAL, false);

        forYouPresenter = new ForYouPresenterImpl(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext()), MealPlanLocalDataSourceImpl.getInstance(getContext()) ));


        foryouRecyclerView.setLayoutManager(layoutManager);
        foryouRecyclerView. setAdapter(bannerAdapter);


//        setupRecyclerView(foryou_recycler_egptian);
//        setupRecyclerView(foryou_recycler_vegan);
        setupRecyclerView(foryou_recycler_recommended);


        forYouPresenter.getMealByName("Al");
        forYouPresenter.getRandomProduct();
        forYouPresenter.getSavedMeals();


    }




    @Override
    public void showData(List<Meal> Meal) {
        Log.i(TAG, "showData: " + Meal);
        for (int i = 0; i  < 10 ; i++)
            Log.i(TAG, "showData: mael " + i  + ":  "+ Meal.get(i).getStrCategory());


        bannerAdapter.updateData(Meal);
        bannerAdapter.notifyDataSetChanged();

    }

    public void markSavedMeals(LiveData<List<Meal>> meals) {
        Observer<List<Meal>> observer = new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> mealsList) {
                Log.i(TAG, "onChanged: ");
                bannerAdapter.setSavedMeals(mealsList);
                forYouAdapter.get(0).setSavedMeals(mealsList);
               meals.removeObserver(this);
            }
        };

        meals.observe(this, observer);
    }

    @Override
    public void showResult(List<Meal> mealsList) {
        forYouAdapter.get(0).updateData(mealsList);
    }


    @Override
    public void showErrMsg(String error) {
        foryouRecyclerView.setVisibility(View.GONE);

        Log.i(TAG, "onFailureResult: ");
        Toast.makeText(getContext().getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddToFavoriteClick(Meal meal) {
        forYouPresenter.addToFavourite(meal);
        Toast.makeText(getContext().getApplicationContext(), meal.getStrMeal() + " Addded to Favorites", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRemoveFromFavoriteClick(Meal meal) {
        forYouPresenter.deleteMeal(meal);
        Toast.makeText(getContext().getApplicationContext(), meal.getStrMeal() + " Removed from Favorites", Toast.LENGTH_SHORT).show();
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        ForYouAdapter adapter = new ForYouAdapter(getContext(), new ArrayList<>(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(  getContext(), RecyclerView.HORIZONTAL, false);
        recyclerView. setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        forYouAdapter.add(adapter);
    }

















    @Override
    public void onPause() {
        super.onPause();
        forYouAdapter.clear();
    }
}

