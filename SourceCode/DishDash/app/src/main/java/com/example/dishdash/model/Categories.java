package com.example.dishdash.model;


import com.google.gson.annotations.SerializedName;


public class Categories {

    @SerializedName("idCategory")
    String idCategory;

    @SerializedName("strCategory")
    String strCategory;

    @SerializedName("strCategoryThumb")
    String strCategoryThumb;

    @SerializedName("strCategoryDescription")
    String strCategoryDescription;


    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }
    public String getIdCategory() {
        return idCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }
    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategoryThumb(String strCategoryThumb) {
        this.strCategoryThumb = strCategoryThumb;
    }
    public String getStrCategoryThumb() {
        return strCategoryThumb;
    }

    public void setStrCategoryDescription(String strCategoryDescription) {
        this.strCategoryDescription = strCategoryDescription;
    }
    public String getStrCategoryDescription() {
        return strCategoryDescription;
    }

}