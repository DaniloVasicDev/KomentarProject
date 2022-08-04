package com.vasic.example.komentarproject.ui.itemmodel.newsdetalitem;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.vasic.example.komentarproject.databinding.RvItemSmallNewsBinding;
import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;
import com.vasic.example.komentarproject.ui.activity.NewsDetailActivity;
import com.vasic.example.komentarproject.ui.adapter.NewsDetailAdapter;

public class RvItemSmallDetailsNews implements RecyclerViewItemDetailsModel{

    private NewsResponseModel newsModel;

    public RvItemSmallDetailsNews(NewsResponseModel newsModel) {
        this.newsModel = newsModel;
    }

    @Override
    public int getTypeModel() {
        return 5;
    }

    @Override
    public void bindNewsDetails(NewsDetailAdapter.DetailsViewHolder holder) {
        RvItemSmallNewsBinding binding = (RvItemSmallNewsBinding) holder.binding;
        Picasso.get().load(newsModel.image).into(binding.imageViewSmall);
        binding.textViewInfo.setText(newsModel.title);
        binding.textViewCategory.setText(newsModel.category.name);
        binding.textViewTime.setText(newsModel.created_at);
        binding.textViewCategory.setTextColor(Color.parseColor(newsModel.category.color));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext().getApplicationContext(), NewsDetailActivity.class);
                intent.putExtra("id", newsModel.id);
                view.getContext().startActivity(intent);
            }
        });
    }
}
