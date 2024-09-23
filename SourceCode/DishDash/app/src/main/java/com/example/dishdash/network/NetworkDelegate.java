package com.example.dishdash.network;

import com.example.dishdash.model.Meal;

import java.util.List;

public interface NetworkDelegate {
    void onSuccessResult(List<Meal> mealsList);
    void onFailureResult(String errorMsg);
}
