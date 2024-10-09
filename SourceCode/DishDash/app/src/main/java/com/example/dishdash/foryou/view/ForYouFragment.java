package com.example.dishdash.foryou.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdash.MainActivity;
import com.example.dishdash.R;
import com.example.dishdash.databinding.FragmentHomeBinding;
import com.example.dishdash.db.MealLocalDataSourceImpl;
import com.example.dishdash.db.MealPlanLocalDataSourceImpl;
import com.example.dishdash.foryou.presenter.ForYouPresenter;
import com.example.dishdash.foryou.presenter.ForYouPresenterImpl;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepositoryImpl;
import com.example.dishdash.model.ShowSnakeBar;
import com.example.dishdash.network.MealRemoteDataSourceImpl;
import com.example.dishdash.network.NetworkConnectionStatus;

import java.util.ArrayList;
import java.util.List;

public class ForYouFragment extends Fragment  implements OnMealClickListener, ForYouView {
    private static final String TAG = "ForYouFragment";
    private FragmentHomeBinding binding;

    BannerAdapter bannerAdapter;
    RecyclerView foryouRecyclerView;
    RecyclerView foryou_recycler_recommended;
    RecyclerView foryou_recycler_egptian;
    RecyclerView foryou_recycler_vegan;

    LinearLayoutManager layoutManager;
    FragmentManager mgr;
    ForYouPresenter forYouPresenter;
    ForYouAdapter recommendedAdapter;
    ForYouAdapter areaAdapter;
    ForYouAdapter categoryAdapter;
    NetworkConnectionStatus connectionStatus;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ForYouViewModel forYouViewModel =
                new ViewModelProvider(this).get(ForYouViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        foryouRecyclerView = view.findViewById(R.id.foryou_recycler);
        foryou_recycler_recommended = view.findViewById(R.id.foryou_recycler_recommended);
        foryou_recycler_egptian = view.findViewById(R.id.foryou_recycler_egptian);
        foryou_recycler_vegan = view.findViewById(R.id.foryou_recycler_vegan);
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



        /* banner adapter */
        bannerAdapter = new BannerAdapter(getContext(), new ArrayList<>(), this);
        layoutManager = new LinearLayoutManager(  getContext(), RecyclerView.HORIZONTAL, false);
        /* Initialize presenter */
        connectionStatus = NetworkConnectionStatus.getInstance(getContext());

        forYouPresenter = new ForYouPresenterImpl(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext()), MealPlanLocalDataSourceImpl.getInstance(getContext()) ), connectionStatus);

        /* set recycler banner view Adapter */
        foryouRecyclerView.setLayoutManager(layoutManager);
        foryouRecyclerView. setAdapter(bannerAdapter);


        /* set recycler foryou view Adapter init */
        recommendedAdapter = setupRecyclerView(foryou_recycler_recommended);
        areaAdapter = setupRecyclerView(foryou_recycler_egptian);
        categoryAdapter = setupRecyclerView(foryou_recycler_vegan);

        /* start fetching data */
        forYouPresenter.getMealByName("pr");
        forYouPresenter.getMealByArea("Egyptian");
        forYouPresenter.getMealByCategory("Veg");
        forYouPresenter.getRandomProduct();
        forYouPresenter.getSavedMeals();


    }





    @Override
    public void showData(Meal meal) {
        if (meal != null)
        {
            Log.i(TAG, "showData: " + meal.getStrMeal());
            bannerAdapter.updateDataMeal(meal);
            bannerAdapter.notifyDataSetChanged();

        }

    }

    public void markSavedMeals(LiveData<List<Meal>> meals) {
        /* Update the adapter with the saved meals */
        Observer<List<Meal>> observer = new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> mealsList) {
                Log.i(TAG, "onChanged: ");
                if (mealsList != null)
                {
                    bannerAdapter.setSavedMeals(mealsList);
                    recommendedAdapter.setSavedMeals(mealsList);
                }
            }
        };

        meals.observe(this, observer);
    }

    @Override
    public void showResultName(List<Meal> mealsList) {
        if (mealsList != null)
        {
            Log.i(TAG, "showResultName: ");
            for (Meal meal : mealsList)
            {
                Log.i(TAG, "showResultName: " + meal.getStrMeal());
            }

            recommendedAdapter.updateData(mealsList);

        }
    }

    @Override
    public void showResultArea(Meal meal) {
        if (meal != null)
        {
            Log.i(TAG, "showData: " + meal.getStrMeal());
            areaAdapter.updateDataMeal(meal);
            areaAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void showResultCategory(List<Meal> mealsList) {
        if (mealsList != null)
        {
            Log.i(TAG, "showResultName: ");
            for (Meal meal : mealsList)
            {
                Log.i(TAG, "showResultName: " + meal.getStrMeal());
            }

            categoryAdapter.updateData(mealsList);

        }
    }


    @Override
    public void showErrMsg(String error) {
        foryouRecyclerView.setVisibility(View.GONE);

        Log.i(TAG, "onFailureResult: ");
        ShowSnakeBar.customSnackbar(getContext() ,getView(), error, "", v1 -> {
        }, R.drawable.info_24px);
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
        forYouPresenter.getMealByName("Al");
        forYouPresenter.getRandomProduct();

    }

    @Override
    public void networkLost() {
        ShowSnakeBar.customSnackbar(getContext() ,getView(), "Network Lost", "", v1 -> {
        }, R.drawable.wifi_off_24px);
    }

    @Override
    public void onAddToFavoriteClick(Meal meal) {
        forYouPresenter.addToFavourite(meal);
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
        forYouPresenter.deleteMeal(meal);
        ShowSnakeBar.customSnackbar(getContext() ,getView(), "Removed from Favorites", "Undo", v1 -> {
            forYouPresenter.addToFavourite(meal);
        }, R.drawable.ic_delete);
    }

    private ForYouAdapter setupRecyclerView(RecyclerView recyclerView) {
        ForYouAdapter adapter = new ForYouAdapter(getContext(), new ArrayList<>(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(  getContext(), RecyclerView.HORIZONTAL, false);
        recyclerView. setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        return adapter;
    }

    /* Utils onResume and onPuase to make sure that it will check for Network every time return to fragment */
    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
        forYouPresenter.startMonitoringNetwork();

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
        forYouPresenter.stopMonitoringNetwork();
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }
}

