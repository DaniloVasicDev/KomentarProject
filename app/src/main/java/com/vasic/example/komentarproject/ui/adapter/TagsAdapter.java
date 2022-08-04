package com.vasic.example.komentarproject.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.vasic.example.komentarproject.databinding.RvItemTagsDetailsNewsBinding;
import com.vasic.example.komentarproject.model.response.newsdetail.TagResponseModel;
import com.vasic.example.komentarproject.ui.activity.ui.TagActivity;

import java.util.ArrayList;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.TagsViewHolder> {

    private ArrayList<TagResponseModel> tagList;

    public TagsAdapter(ArrayList<TagResponseModel> tagList) {
        this.tagList = tagList;
    }

    @NonNull
    @Override
    public TagsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         RvItemTagsDetailsNewsBinding binding = RvItemTagsDetailsNewsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
         return new TagsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TagsViewHolder holder, int position) {
        TagResponseModel tagModel = tagList.get(position);
        holder.binding.button.setText(tagModel.title);
        holder.binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext().getApplicationContext(), TagActivity.class);
                intent.putExtra("tagId",tagModel.id);
                intent.putExtra("tagTitle",tagModel.title);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tagList.size();
    }

    public class TagsViewHolder extends RecyclerView.ViewHolder {

        public RvItemTagsDetailsNewsBinding binding;
        public TagsViewHolder(RvItemTagsDetailsNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
