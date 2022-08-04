package com.vasic.example.komentarproject.ui.itemmodel.homepage;

import android.graphics.Color;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.vasic.example.komentarproject.databinding.RvItemRvCategoryboxBinding;
import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;
import com.vasic.example.komentarproject.ui.adapter.NewsAdapter;
import com.vasic.example.komentarproject.ui.adapter.viewpager.HomePageAdapter;

import java.util.ArrayList;

public class RvItemCategoryBox implements RecyclerViewHomePageItem{

    private ArrayList<NewsResponseModel> list;
    private String title;
    private String color;


    public RvItemCategoryBox(ArrayList<NewsResponseModel> list,String title,String color) {
        this.list = list;
        this.title = title;
        this.color = color;

    }

    @Override
    public int getType() {
        return 6;
    }

    @Override
    public void bind(HomePageAdapter.HomePageViewHolder holder) {

            RvItemRvCategoryboxBinding binding = (RvItemRvCategoryboxBinding) holder.binding;
            binding.textViewTitle.setText(title);
            binding.view.setBackgroundColor(Color.parseColor(color));
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
            binding.recyclerView.setAdapter(new NewsAdapter(list,holder.itemView.getContext(),title));


    }
}
