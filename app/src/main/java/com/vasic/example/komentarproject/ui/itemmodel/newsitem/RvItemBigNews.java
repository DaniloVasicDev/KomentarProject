package com.vasic.example.komentarproject.ui.itemmodel.newsitem;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.vasic.example.komentarproject.databinding.RvItemBigNewsBinding;
import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;
import com.vasic.example.komentarproject.ui.activity.NewsDetailActivity;
import com.vasic.example.komentarproject.ui.adapter.NewsAdapter;
import com.vasic.example.komentarproject.ui.adapter.VidesAdapter;

public class RvItemBigNews implements RecyclerViewItemModel {

    private NewsResponseModel news;

    public RvItemBigNews(NewsResponseModel news) {
        this.news = news;
    }

    @Override
    public int getType() {
        return 0;
    }


    @Override
    public void bindNews(NewsAdapter.NewsViewHolder holder) {

        RvItemBigNewsBinding binding = (RvItemBigNewsBinding) holder.binding;
        Picasso.get().load(news.image).into(binding.imageVewBig);
        binding.textViewInfo.setText(news.title);
        binding.textViewCategory.setText(news.category.name);
        binding.textViewTime.setText(news.created_at);
        binding.textViewCategory.setTextColor(Color.parseColor(news.category.color));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext().getApplicationContext(), NewsDetailActivity.class);
                intent.putExtra("id",news.id);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void bindVideos(VidesAdapter.VideoViewHolder holder) {

    }


}
