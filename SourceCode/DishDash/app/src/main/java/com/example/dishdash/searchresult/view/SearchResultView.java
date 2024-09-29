package com.example.dishdash.searchresult.view;

import androidx.lifecycle.LiveData;

import com.example.dishdash.model.Meal;

import java.util.List;

public interface SearchResultView {
    void showData(LiveData<List<Meal>> meals);;

}
