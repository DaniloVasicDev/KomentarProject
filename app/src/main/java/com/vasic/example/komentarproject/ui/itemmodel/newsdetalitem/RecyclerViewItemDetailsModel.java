package com.vasic.example.komentarproject.ui.itemmodel.newsdetalitem;

import com.vasic.example.komentarproject.ui.adapter.NewsDetailAdapter;

public interface RecyclerViewItemDetailsModel {

    int getTypeModel();
    void bindNewsDetails(NewsDetailAdapter.DetailsViewHolder holder);
}
