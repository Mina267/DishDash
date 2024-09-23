package com.example.dishdash.model;


import com.google.gson.annotations.SerializedName;


public class ListAllArea {

    @SerializedName("strArea")
    String strArea;


    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }
    public String getStrArea() {
        return strArea;
    }

}