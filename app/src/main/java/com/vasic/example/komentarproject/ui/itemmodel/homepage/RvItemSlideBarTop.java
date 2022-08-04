package com.vasic.example.komentarproject.ui.itemmodel.homepage;

import android.graphics.Color;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.vasic.example.komentarproject.R;
import com.vasic.example.komentarproject.data.DataContainer;
import com.vasic.example.komentarproject.databinding.RvItemSlideBarTopBinding;
import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;
import com.vasic.example.komentarproject.ui.adapter.viewpager.HomePageAdapter;
import com.vasic.example.komentarproject.ui.adapter.viewpager.homepage.SlideBarAdapter;

import java.util.ArrayList;

public class RvItemSlideBarTop implements RecyclerViewHomePageItem{

    public ArrayList<NewsResponseModel> most_read;
    public ArrayList<NewsResponseModel> latest;
    public ArrayList<NewsResponseModel> most_comented;

    public RvItemSlideBarTop(ArrayList<NewsResponseModel> most_read, ArrayList<NewsResponseModel> latest, ArrayList<NewsResponseModel> most_comented) {
        this.most_read = most_read;
        this.latest = latest;
        this.most_comented = most_comented;
    }

    @Override
    public int getType() {
        return 2;
    }

    @Override
    public void bind(HomePageAdapter.HomePageViewHolder holder) {
        RvItemSlideBarTopBinding binding = (RvItemSlideBarTopBinding) holder.binding;

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        binding.recyclerView.setAdapter(new SlideBarAdapter(latest));

        binding.textViewMostRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               binding.textViewMostRead.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.black));
               binding.view2.setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.orange));
               binding.texiViewLatest.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.gray));
                binding.view1.setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.light_gray));
                binding.textViewComments.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.gray));
                binding.view3.setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.light_gray));
                binding.recyclerView.setAdapter(new SlideBarAdapter(most_read));
            }
        });
        binding.texiViewLatest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.texiViewLatest.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.black));
                binding.view1.setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.orange));
                binding.textViewMostRead.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.gray));
                binding.view2.setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.light_gray));
                binding.textViewComments.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.gray));
                binding.view3.setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.light_gray));
                binding.recyclerView.setAdapter(new SlideBarAdapter(latest));
            }
        });
        binding.textViewComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.textViewComments.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.black));
                binding.view3.setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.orange));
                binding.texiViewLatest.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.gray));
                binding.view1.setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.light_gray));
                binding.textViewMostRead.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.gray));
                binding.view2.setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.light_gray));
                binding.recyclerView.setAdapter(new SlideBarAdapter(most_comented));
            }
        });


    }
}
