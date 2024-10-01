package com.example.dishdash.mealplan.presenter;

import androidx.lifecycle.LiveData;

import com.example.dishdash.mealplan.view.MealPlanView;
import com.example.dishdash.model.MealPlan;
import com.example.dishdash.model.MealRepository;

import java.util.List;

public class MealPlanPresenterImpl implements MealPlanPresenter {

    MealRepository mealRepository;
    MealPlanView _view;

    public MealPlanPresenterImpl(MealPlanView _view, MealRepository mealRepository) {
        this.mealRepository = mealRepository;
        this._view = _view;
    }

    @Override
    public void deleteMealPlan(MealPlan mealPlan) {
        mealRepository.deleteMealPlan(mealPlan);
    }

    @Override
    public LiveData<List<MealPlan>> getMealPlanByDate(String date) {
        return mealRepository.getMealsOfTheDay(date);
    }

}
