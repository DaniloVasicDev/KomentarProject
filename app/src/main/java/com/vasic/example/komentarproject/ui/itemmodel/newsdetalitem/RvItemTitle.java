package com.vasic.example.komentarproject.ui.itemmodel.newsdetalitem;

import android.graphics.Color;
import android.view.View;

import com.vasic.example.komentarproject.databinding.RvItemTitleDetailsNewsBinding;
import com.vasic.example.komentarproject.model.response.newsdetail.NewsDetailDataResponse;
import com.vasic.example.komentarproject.ui.adapter.NewsDetailAdapter;

public class RvItemTitle implements RecyclerViewItemDetailsModel{

    private String title;
    private String color_comment;
    private String color;
    private NewsDetailDataResponse newsData;

    public RvItemTitle(String title, String color) {
        this.title = title;
        this.color = color;
    }

    public RvItemTitle(String title, NewsDetailDataResponse newsData, String color) {
        this.title = title;
        this.newsData = newsData;
        this.color_comment = color;
    }

    @Override
    public int getTypeModel() {
        return 7;
    }

    @Override
    public void bindNewsDetails(NewsDetailAdapter.DetailsViewHolder holder) {
        RvItemTitleDetailsNewsBinding binding = (RvItemTitleDetailsNewsBinding) holder.binding;
        if(newsData!=null){
            binding.count.setText("("+newsData.comments_count+")");
            binding.count.setVisibility(View.VISIBLE);
            binding.textViewTitle.setText(title);
            binding.view.setBackgroundColor(Color.parseColor(color_comment));
        }
        else{
            binding.textViewTitle.setText(title);
            binding.view.setBackgroundColor(Color.parseColor(color));
        }

    }
}
