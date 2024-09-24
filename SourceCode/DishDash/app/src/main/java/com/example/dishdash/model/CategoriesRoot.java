package com.example.dishdash.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


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