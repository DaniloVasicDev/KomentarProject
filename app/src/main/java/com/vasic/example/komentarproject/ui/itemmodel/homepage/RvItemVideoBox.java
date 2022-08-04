package com.vasic.example.komentarproject.ui.itemmodel.homepage;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.vasic.example.komentarproject.databinding.RvItemVideoBoxBinding;
import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;
import com.vasic.example.komentarproject.ui.adapter.VidesAdapter;
import com.vasic.example.komentarproject.ui.adapter.viewpager.HomePageAdapter;

import java.util.ArrayList;

public class RvItemVideoBox implements RecyclerViewHomePageItem{

    private ArrayList<NewsResponseModel> list;

    public RvItemVideoBox(ArrayList<NewsResponseModel> list) {
        this.list = list;
    }

    @Override
    public int getType() {
        return 4;
    }

    @Override
    public void bind(HomePageAdapter.HomePageViewHolder holder) {
        RvItemVideoBoxBinding binding = (RvItemVideoBoxBinding) holder.binding;
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        binding.recyclerView.setAdapter(new VidesAdapter(list,holder.itemView.getContext()));

    }
}
