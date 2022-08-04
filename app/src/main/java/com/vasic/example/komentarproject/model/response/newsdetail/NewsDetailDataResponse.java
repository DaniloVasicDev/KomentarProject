package com.vasic.example.komentarproject.model.response.newsdetail;

import com.vasic.example.komentarproject.model.response.news.CategoryResponseModel;
import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;

import java.io.Serializable;
import java.util.ArrayList;

public class NewsDetailDataResponse implements Serializable {

    public int id;
    public String image;
    public String image_source;
    public String author_name;
    public String source;
    public CategoryResponseModel category;
    public String title;
    public String description;
    public int comment_enabled;
    public int comments_count;
    public int shares_count;
    public String created_at;
    public String url;
    public String click_type;
    public ArrayList<TagResponseModel> tags;
    public ArrayList<NewsResponseModel> related_news;
    public ArrayList<NewsResponseModel> category_news;




}
