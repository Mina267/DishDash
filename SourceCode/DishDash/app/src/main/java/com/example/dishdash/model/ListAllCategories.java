package com.example.dishdash.model;

import com.google.gson.annotations.SerializedName;

   
public class ListAllCategories {

   @SerializedName("strCategory")
   String strCategory;


    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }
    public String getStrCategory() {
        return strCategory;
    }
    
}