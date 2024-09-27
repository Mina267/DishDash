package com.example.dishdash.foryou.view;

import android.os.Parcelable;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ForYouViewModel extends ViewModel {

    private static final String TAG = "ForYouViewModel";
    private final MutableLiveData<Parcelable> meal;

    public ForYouViewModel() {
        meal = new MutableLiveData<>();
        Log.i(TAG, "ForYouViewModel: ");
    }

    public void setMeal(Parcelable data) {
        meal.setValue(data);
    }

    public LiveData<Parcelable> getMeal() {
        return meal;
    }
}


