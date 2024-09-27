package com.example.dishdash.foryou.view;

import com.example.dishdash.model.Meal;

import java.util.List;

public interface ForYouView {
    public void showData(List<Meal> Meal);
    public void showErrMsg(String error);
}


