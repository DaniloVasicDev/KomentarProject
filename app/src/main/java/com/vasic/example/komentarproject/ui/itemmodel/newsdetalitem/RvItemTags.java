package com.vasic.example.komentarproject.ui.itemmodel.newsdetalitem;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.vasic.example.komentarproject.databinding.RvItemRvTagBinding;
import com.vasic.example.komentarproject.model.response.newsdetail.TagResponseModel;
import com.vasic.example.komentarproject.ui.activity.ui.TagActivity;
import com.vasic.example.komentarproject.ui.adapter.NewsDetailAdapter;
import com.vasic.example.komentarproject.ui.adapter.TagsAdapter;

import java.util.ArrayList;

public class RvItemTags implements RecyclerViewItemDetailsModel{

    private  ArrayList<TagResponseModel> tagList;
    private TagResponseModel tag;


    public RvItemTags(ArrayList<TagResponseModel> tagList) {
        this.tagList = tagList;
    }

    @Override
    public int getTypeModel() {
        return 2;
    }

    @Override
    public void bindNewsDetails(NewsDetailAdapter.DetailsViewHolder holder) {
        RvItemRvTagBinding binding = (RvItemRvTagBinding) holder.binding;
        binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL));
        binding.recyclerView.setAdapter(new TagsAdapter(tagList));
    }
}
