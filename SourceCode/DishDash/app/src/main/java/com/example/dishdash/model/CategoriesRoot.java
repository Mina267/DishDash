package com.example.dishdash.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class CategoriesRoot {

    @SerializedName("categories")
    List<Categories> categories;


    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }
    public List<Categories> getCategories() {
        return categories;
    }

}