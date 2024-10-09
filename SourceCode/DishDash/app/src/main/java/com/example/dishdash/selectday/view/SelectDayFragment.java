package com.example.dishdash.selectday.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dishdash.R;
import com.example.dishdash.db.MealLocalDataSourceImpl;
import com.example.dishdash.db.MealPlanLocalDataSourceImpl;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepositoryImpl;
import com.example.dishdash.model.ShowSnakeBar;
import com.example.dishdash.network.MealRemoteDataSourceImpl;
import com.example.dishdash.selectday.presenter.SelectDayPresenter;
import com.example.dishdash.selectday.presenter.SelectDayPresenterImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class SelectDayFragment extends Fragment implements SelectDayView {

    private String mealSelectedDate;
    private Button btnContinue;
    private ImageView img_selectDay;
    private TextView txt_selectDay;
    private Meal meal;
    SelectDayPresenter selectDayPresenter;
    Communicator communicator;
    Calendar calendar;

    public SelectDayFragment() {
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
        View view = inflater.inflate(R.layout.fragment_select_day, container, false);

        btnContinue = view.findViewById(R.id.btnContinue);
        img_selectDay = view.findViewById(R.id.img_selectDay);
        txt_selectDay  = view.findViewById(R.id.txt_selectDay);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        /*
         * Retrieve the meal passed from the previous fragment
         */
        meal = getArguments().getParcelable("meal");


        /* Init for mealSelectedDate with current date */
        calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy", Locale.getDefault());
        mealSelectedDate = dateFormat.format(calendar.getTime());



        /* Image and text of meal */
        Glide.with(getContext())
                .load(meal.getStrMealThumb())
                .apply(new RequestOptions().override(200, 200)
                        .placeholder(R.drawable.nophotoavailable)
                        .error(R.drawable.ic_launcher_foreground))
                .into(img_selectDay);
        txt_selectDay.setText(meal.getStrMeal());

        /* Use this to communicate with the MainActivity to pop up snake bar */
        communicator = (Communicator) getActivity();
        /* Continue button */
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LiveData<Boolean> mealExistsLiveData = selectDayPresenter.isMealPlanExists(meal, mealSelectedDate);

                mealExistsLiveData.observe(getViewLifecycleOwner(), aBoolean -> {
                    if (aBoolean) {

                        ShowSnakeBar.customSnackbar(getContext() ,getView(), "Meal already exists in plan", "", v1 -> {
                        }, R.drawable.ic_delete);
                    } else {
                        /* add Meal to meal plan data base */
                        selectDayPresenter.addToMealPlan(meal, mealSelectedDate);
                        /* to Show date and day in snake bar */
                        communicator.viewData(mealSelectedDate);
                        /* Close the fragment and return to previous fragment */
                        getParentFragmentManager().popBackStack();
                    }
                    mealExistsLiveData.removeObservers(getViewLifecycleOwner());
                });
            }
        });


        CalendarView calendarView = view.findViewById(R.id.calendarView);

        /* Set the minimum date to today's date */
        calendarView.setMinDate(System.currentTimeMillis());

        /*
         * Set the maximum date to the end of next month
         *  public static Calendar getInstance ()
         * Gets a calendar using the default time zone and locale.
         */
        calendar = Calendar.getInstance();
        /* adds 1 month to the current date, moving the calendar forward by one month. */
        calendar.add(Calendar.DAY_OF_YEAR, 30);
        /*
         * then in sets the last date in the Calendar View to the computed date
         */
        calendarView.setMaxDate(calendar.getTimeInMillis()); // Set max date to 30 days from today


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                /* take date and save it to use in continue button */
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                mealSelectedDate = selectedDate;

            }
        });


        selectDayPresenter = new SelectDayPresenterImpl(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext()), MealPlanLocalDataSourceImpl.getInstance(getContext()) ));


    }

    @Override
    public void showErrMsg(String error) {

    }
}