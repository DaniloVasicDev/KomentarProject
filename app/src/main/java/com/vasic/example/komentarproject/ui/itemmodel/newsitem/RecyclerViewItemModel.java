package com.vasic.example.komentarproject.ui.itemmodel.newsitem;

import com.vasic.example.komentarproject.ui.adapter.NewsAdapter;
import com.vasic.example.komentarproject.ui.adapter.NewsDetailAdapter;
import com.vasic.example.komentarproject.ui.adapter.VidesAdapter;

public interface RecyclerViewItemModel {

    int getType();

    void bindNews(NewsAdapter.NewsViewHolder holder);
    void bindVideos(VidesAdapter.VideoViewHolder holder);


}
