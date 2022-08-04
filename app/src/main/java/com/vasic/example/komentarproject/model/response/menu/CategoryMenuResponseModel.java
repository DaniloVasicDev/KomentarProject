package com.vasic.example.komentarproject.model.response.menu;

import android.os.Parcel;
import android.os.Parcelable;

import com.vasic.example.komentarproject.model.response.news.SubcategoryResponseMode;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryMenuResponseModel implements Serializable {
    public String type;
    public int id;
    public String name;
    public String color;
    public String description;
    public ArrayList<CategoryMenuResponseModel> subcategories;


}
