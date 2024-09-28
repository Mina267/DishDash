package com.example.dishdash.foryou.view;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdash.MainActivity;
import com.example.dishdash.R;
import com.example.dishdash.databinding.FragmentHomeBinding;
import com.example.dishdash.db.MealLocalDataSourceImpl;
import com.example.dishdash.db.MealPlanLocalDataSourceImpl;
import com.example.dishdash.detailsrecipes.view.DetailsRecipesFragment;
import com.example.dishdash.foryou.presenter.ForYouPresenter;
import com.example.dishdash.foryou.presenter.ForYouPresenterImpl;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepositoryImpl;
import com.example.dishdash.network.MealRemoteDataSourceImpl;

import java.util.ArrayList;
import java.util.List;

public class ForYouFragment extends Fragment  implements OnMealClickListener, ForYouView {
    private static final String TAG = "ForYouFragment";
    private FragmentHomeBinding binding;

    ForYouAdapter forYouAdapter;
    RecyclerView foryouRecyclerView;
    LinearLayoutManager layoutManager;
    FragmentManager mgr;
    ForYouPresenter forYouPresenter;



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


        foryouRecyclerView = (RecyclerView) view.findViewById(R.id.foryou_recycler);

        forYouAdapter = new ForYouAdapter(getContext(), new ArrayList<>(), this, new ForYouAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Meal meal, int position) {

            }
        });
        layoutManager = new LinearLayoutManager(  getContext(), RecyclerView.HORIZONTAL, false);

        forYouPresenter = new ForYouPresenterImpl(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext()), MealPlanLocalDataSourceImpl.getInstance(getContext()) ));


        foryouRecyclerView.setLayoutManager(layoutManager);





        foryouRecyclerView. setAdapter(forYouAdapter);



        forYouPresenter.getRandomProduct();


    }




    @Override
    public void showData(List<Meal> Meal) {
        Log.i(TAG, "showData: " + Meal);
        for (int i = 0; i  < 10 ; i++)
            Log.i(TAG, "showData: mael " + i  + ":  "+ Meal.get(i).getStrCategory());

        forYouAdapter.updateData(Meal);
        forYouAdapter.notifyDataSetChanged();

    }

    @Override
    public void showErrMsg(String error) {
        foryouRecyclerView.setVisibility(View.GONE);

        Log.i(TAG, "onFailureResult: ");
        Toast.makeText(getContext().getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onForYouMealClick(Meal meal) {
        forYouPresenter.addToFavourite(meal);
        Toast.makeText(getContext().getApplicationContext(), meal.getStrMeal() + " Addded to Favorites", Toast.LENGTH_SHORT).show();
    }
}

