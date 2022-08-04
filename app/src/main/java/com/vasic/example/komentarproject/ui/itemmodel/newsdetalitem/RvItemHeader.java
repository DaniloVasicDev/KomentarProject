package com.vasic.example.komentarproject.ui.itemmodel.newsdetalitem;

import com.vasic.example.komentarproject.databinding.RvItemHeaderDetailNewsBinding;
import com.vasic.example.komentarproject.model.response.newsdetail.NewsDetailDataResponse;
import com.vasic.example.komentarproject.ui.adapter.NewsDetailAdapter;

public class RvItemHeader implements RecyclerViewItemDetailsModel{

    private NewsDetailDataResponse newsModel;

    public RvItemHeader(NewsDetailDataResponse newsModel) {
        this.newsModel = newsModel;
    }

    @Override
    public int getTypeModel() {
        return 0;
    }

    @Override
    public void bindNewsDetails(NewsDetailAdapter.DetailsViewHolder holder) {

        RvItemHeaderDetailNewsBinding binding = (RvItemHeaderDetailNewsBinding) holder.binding;
        binding.textViewTitle.setText(newsModel.title);
        binding.textViewDate.setText(newsModel.created_at);
        binding.textViewAutor.setText(newsModel.author_name);
        binding.textViewIzvor.setText(newsModel.source);
        binding.textViewInfo.setText(newsModel.description);
        binding.textViewCountComment.setText(newsModel.comments_count+"");
    }
}
