package com.vasic.example.komentarproject.ui.itemmodel.homepage;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.vasic.example.komentarproject.R;
import com.vasic.example.komentarproject.databinding.RvItemRvSliderBinding;
import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;
import com.vasic.example.komentarproject.ui.adapter.SliderAdapter;
import com.vasic.example.komentarproject.ui.adapter.viewpager.HomePageAdapter;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator2;

public class RvItemSlider implements RecyclerViewHomePageItem {

    private ArrayList<NewsResponseModel> newsList;
    private Context context;

    public RvItemSlider(ArrayList<NewsResponseModel> newsList,Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public void bind(HomePageAdapter.HomePageViewHolder holder) {

        RvItemRvSliderBinding binding = (RvItemRvSliderBinding) holder.binding;
        binding.recyclerViewHorizontal.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), RecyclerView.HORIZONTAL,false));
        binding.recyclerViewHorizontal.setAdapter(new SliderAdapter(newsList));

        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        binding.recyclerViewHorizontal.setOnFlingListener(null);
        pagerSnapHelper.attachToRecyclerView(binding.recyclerViewHorizontal);

        CircleIndicator2 indicator = holder.itemView.findViewById(R.id.indicator);
        indicator.attachToRecyclerView(binding.recyclerViewHorizontal, pagerSnapHelper);
    }
}
