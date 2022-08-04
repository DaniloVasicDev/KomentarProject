package com.vasic.example.komentarproject.ui.itemmodel.homepage;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.vasic.example.komentarproject.databinding.RvItemSmallNewsBinding;
import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;
import com.vasic.example.komentarproject.ui.activity.NewsDetailActivity;
import com.vasic.example.komentarproject.ui.adapter.viewpager.HomePageAdapter;

public class RvItemSmallNewsTop implements RecyclerViewHomePageItem{

    private NewsResponseModel news;

    public RvItemSmallNewsTop(NewsResponseModel news) {
        this.news = news;
    }

    @Override
    public int getType() {
        return 1;
    }

    @Override
    public void bind(HomePageAdapter.HomePageViewHolder holder) {
        RvItemSmallNewsBinding binding = (RvItemSmallNewsBinding) holder.binding;
        Picasso.get().load(news.image).into(binding.imageViewSmall);
        binding.textViewInfo.setText(news.title);
        binding.textViewCategory.setText(news.category.name);
        binding.textViewTime.setText(news.created_at);
        binding.textViewCategory.setTextColor(Color.parseColor(news.category.color));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext().getApplicationContext(), NewsDetailActivity.class);
                intent.putExtra("id", news.id);
                view.getContext().startActivity(intent);
            }
        });
    }
}
