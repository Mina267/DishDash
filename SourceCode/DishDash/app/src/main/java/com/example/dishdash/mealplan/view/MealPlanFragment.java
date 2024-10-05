package com.example.dishdash.mealplan.view;

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
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdash.R;
import com.example.dishdash.databinding.FragmentMealplanBinding;
import com.example.dishdash.db.MealLocalDataSourceImpl;
import com.example.dishdash.db.MealPlanLocalDataSourceImpl;
import com.example.dishdash.mealplan.presenter.MealPlanPresenter;
import com.example.dishdash.mealplan.presenter.MealPlanPresenterImpl;

import com.example.dishdash.model.MealPlan;
import com.example.dishdash.model.MealRepositoryImpl;
import com.example.dishdash.network.MealRemoteDataSourceImpl;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MealPlanFragment extends Fragment implements MealPlanView, OnMealPlanClickListener {
    private static final String TAG = "MealPlanFragment";
    private FragmentMealplanBinding binding;
    private TextView firstDay;
    private TextView secondDay;
    private TextView thirdDay;
    private TextView fourthDay;
    private TextView fivethDay;
    private TextView sixthDay;
    private TextView seventhDay;

    private RecyclerView recyclerViewFirstDay;
    private RecyclerView recyclerViewSecondDay;
    private RecyclerView recyclerViewThirdDay;
    private RecyclerView recyclerViewFourthDay;
    private RecyclerView recyclerViewFivethDay;
    private RecyclerView recyclerViewSixthDay;
    private RecyclerView recyclerViewSeventhDay;

    private ImageView imgArrowBack;
    private ImageView imgArrowNext;
    TextView txtViewMealPlan;

    private int currentWeekOffset = 0;
    static private final String CURRENT_DAY = "currentDay";
    /* List of days adapters that use to save days adapters for each day */
    List<DaysAdapter> daysAdapters = new ArrayList<DaysAdapter>();


    FragmentManager mgr;
    MealPlanPresenter mealPlanPresenter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        binding = FragmentMealplanBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        firstDay = view.findViewById(R.id.firstDay);
        secondDay = view.findViewById(R.id.secondDay);
        thirdDay = view.findViewById(R.id.thirdDay);
        fourthDay = view.findViewById(R.id.fourthDay);
        fivethDay = view.findViewById(R.id.fivethDay);
        sixthDay = view.findViewById(R.id.sixthDay);
        seventhDay = view.findViewById(R.id.seventhDay);
        recyclerViewFirstDay = view.findViewById(R.id.recyclerViewFirstDay);
        recyclerViewSecondDay = view.findViewById(R.id.recyclerViewSecondDay);
        recyclerViewThirdDay = view.findViewById(R.id.recyclerViewThirdDay);
        recyclerViewFourthDay = view.findViewById(R.id.recyclerViewFourthDay);
        recyclerViewFivethDay = view.findViewById(R.id.recyclerViewFivethDay);
        recyclerViewSixthDay = view.findViewById(R.id.recyclerViewSixthDay);
        recyclerViewSeventhDay = view.findViewById(R.id.recyclerViewSeventhDay);
        imgArrowBack = view.findViewById(R.id.imgArrowBack);
        imgArrowNext = view.findViewById(R.id.imgArrowNext);
        txtViewMealPlan = view.findViewById(R.id.txtViewMealPlan);
        /* Set days names on text view start from today name to the next six days */
        setDayNames();

        /* get Fragment manager */
        mgr = getChildFragmentManager();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: ");


        if (savedInstanceState != null) {
            currentWeekOffset = savedInstanceState.getInt(CURRENT_DAY);
        }
        if (!daysAdapters.isEmpty())
        {
            daysAdapters.clear();
        }

        /* Create days adapters for each Day */
        setupRecyclerView(recyclerViewFirstDay);
        setupRecyclerView(recyclerViewSecondDay);
        setupRecyclerView(recyclerViewThirdDay);
        setupRecyclerView(recyclerViewFourthDay);
        setupRecyclerView(recyclerViewFivethDay);
        setupRecyclerView(recyclerViewSixthDay);
        setupRecyclerView(recyclerViewSeventhDay);




        imgArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentWeekOffset--;
                if (currentWeekOffset == -1)
                {
                    currentWeekOffset = 3;
                }
                updateMealPlansForCurrentWeek();

                if (currentWeekOffset == 0)
                {
                    txtViewMealPlan.setText(R.string.thisweek);
                }
                else {
                    txtViewMealPlan.setText(R.string.nextweek);
                }
            }
        });

        imgArrowNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentWeekOffset = (currentWeekOffset + 1) % 4;
                updateMealPlansForCurrentWeek();
                if (currentWeekOffset == 0)
                {
                    txtViewMealPlan.setText(R.string.thisweek);
                }
                else {
                    txtViewMealPlan.setText(R.string.nextweek);
                }
            }
        });

        /* Presenter */
        mealPlanPresenter = new MealPlanPresenterImpl(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext()), MealPlanLocalDataSourceImpl.getInstance(getContext()) ));


        if (daysAdapters.isEmpty()) {

            fetchMealPlansForSevenDays(currentWeekOffset);
        } else {
            updateMealPlansForCurrentWeek();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void setDayNames() {
        Calendar calendar = Calendar.getInstance();

        /* Get today day */
        firstDay.setText(getDayName(calendar));
        /* Increment the day by to get the next days */
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        secondDay.setText(getDayName(calendar));
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        thirdDay.setText(getDayName(calendar));
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        fourthDay.setText(getDayName(calendar));
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        fivethDay.setText(getDayName(calendar));
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        sixthDay.setText(getDayName(calendar));
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        seventhDay.setText(getDayName(calendar));

    }

    private String getDayName(Calendar calendar) {
        return new SimpleDateFormat("EEEE", Locale.getDefault()).format(calendar.getTime());
    }


    private void setupRecyclerView(RecyclerView recyclerView) {
        DaysAdapter adapter = new DaysAdapter(getContext(), new ArrayList<>(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(  getContext(), RecyclerView.HORIZONTAL, false);
        recyclerView. setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        daysAdapters.add(adapter);
    }

    private void fetchMealPlansForSevenDays(int weekOffset) {
        Calendar calendar = Calendar.getInstance();



        // Adjust the calendar to start from today or the same day in the upcoming week(s)
        calendar.add(Calendar.WEEK_OF_YEAR, weekOffset);

        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy", Locale.getDefault());

        /* Start of for loop to fetch meals for each day */
        for (int i = 0; i < 7; i++) {
            String date = dateFormat.format(calendar.getTime());
            // Fetch meals for the day
            Log.i(TAG, "onViewCreated: " + date);
            int indx = i;
            mealPlanPresenter.getMealPlanByDate(date).observe(getViewLifecycleOwner(), mealPlans -> {
                Log.i(TAG, "fetchMealPlansForSevenDays:   " + mealPlans);

                daysAdapters.get(indx).updateData(mealPlans);
                daysAdapters.get(indx).notifyDataSetChanged();
            });

            /* Move to the next day */
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        } /* End of for loop */
    }

    /* Clear adapters and fetch new data for the updated week */
    private void updateMealPlansForCurrentWeek() {
        for (DaysAdapter adapter : daysAdapters) {
            adapter.updateData(new ArrayList<>());
        }

        fetchMealPlansForSevenDays(currentWeekOffset);
    }


    @Override
    public void onMealPlanClick(MealPlan mealPlan) {
            mealPlanPresenter.deleteMealPlan(mealPlan);

            /* Show a Snackbar with undo option */
            Snackbar snackbar = Snackbar.make(getView(), mealPlan.getStrMeal() + " Deleted", Snackbar.LENGTH_LONG);

            snackbar.setAction("Undo", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /* Undo the delete action  */
                    mealPlanPresenter.addToMealPlan(mealPlan);

                }
            });

            /* Display the Snackbar */
            snackbar.show();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
        //daysAdapters.clear();

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: ");
        outState.putInt(CURRENT_DAY, currentWeekOffset);
    }
}