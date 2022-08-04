package com.vasic.example.komentarproject.model.response.homepage;

import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;

import java.util.ArrayList;

public class HomePageCategoryResponse {

    public int id;
    public String title;
    public String color;
    public ArrayList<NewsResponseModel> news;

}
