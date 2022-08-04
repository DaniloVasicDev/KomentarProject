package com.vasic.example.komentarproject.ui.itemmodel.homepage;

import com.vasic.example.komentarproject.ui.adapter.viewpager.HomePageAdapter;

public interface RecyclerViewHomePageItem {

    int getType();
    void bind(HomePageAdapter.HomePageViewHolder holder);
}
