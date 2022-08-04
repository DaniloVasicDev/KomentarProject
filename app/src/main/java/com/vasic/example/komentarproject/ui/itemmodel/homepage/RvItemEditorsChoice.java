package com.vasic.example.komentarproject.ui.itemmodel.homepage;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;

import com.vasic.example.komentarproject.R;
import com.vasic.example.komentarproject.databinding.RvItemEditorsChoiceBinding;
import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;
import com.vasic.example.komentarproject.ui.adapter.SliderAdapter;
import com.vasic.example.komentarproject.ui.adapter.viewpager.HomePageAdapter;
import com.vasic.example.komentarproject.ui.adapter.viewpager.homepage.SlideBarAdapter;

import java.util.ArrayList;
import java.util.Objects;

import me.relex.circleindicator.CircleIndicator2;

public class RvItemEditorsChoice implements RecyclerViewHomePageItem{

    private ArrayList<NewsResponseModel> list;

    public RvItemEditorsChoice(ArrayList<NewsResponseModel> list) {
        this.list = list;
    }

    @Override
    public int getType() {
        return 3;
    }

    @Override
    public void bind(HomePageAdapter.HomePageViewHolder holder) {

        RvItemEditorsChoiceBinding binding = (RvItemEditorsChoiceBinding) holder.binding;
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        binding.recyclerView.setAdapter(new SliderAdapter(list));

        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        binding.recyclerView.setOnFlingListener(null);
        pagerSnapHelper.attachToRecyclerView(binding.recyclerView);

        CircleIndicator2 indicator = holder.itemView.findViewById(R.id.indicator);
        indicator.attachToRecyclerView(binding.recyclerView, pagerSnapHelper);

    }
}
