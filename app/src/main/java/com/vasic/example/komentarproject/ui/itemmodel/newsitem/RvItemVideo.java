package com.vasic.example.komentarproject.ui.itemmodel.newsitem;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.vasic.example.komentarproject.databinding.RvItemVideoBinding;
import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;
import com.vasic.example.komentarproject.ui.adapter.NewsAdapter;
import com.vasic.example.komentarproject.ui.adapter.VidesAdapter;

public class RvItemVideo implements RecyclerViewItemModel {

    private NewsResponseModel news;
    private Context context;

    public RvItemVideo(NewsResponseModel news, Context context) {
        this.news = news;
        this.context = context;
    }

    @Override
    public int getType() {
        return 0;
    }


    @Override
    public void bindNews(NewsAdapter.NewsViewHolder holder) {

    }

    @Override
    public void bindVideos(VidesAdapter.VideoViewHolder holder) {

        RvItemVideoBinding binding = (RvItemVideoBinding) holder.binding;
        Picasso.get().load(news.image).into(binding.imageView);
        binding.textViewTitle.setText(news.title);
        binding.textViewCategory.setText(news.category.name);
        binding.textViewDate.setText(news.created_at);
        binding.textViewCategory.setTextColor(Color.parseColor(news.category.color));
        binding.imageViewPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(news.url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            }
        });
    }




}
