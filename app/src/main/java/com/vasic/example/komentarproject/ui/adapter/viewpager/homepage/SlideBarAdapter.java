package com.vasic.example.komentarproject.ui.adapter.viewpager.homepage;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vasic.example.komentarproject.databinding.RvItemMiniNewsBinding;
import com.vasic.example.komentarproject.databinding.RvItemSlideBarTopBinding;
import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;
import com.vasic.example.komentarproject.ui.activity.NewsDetailActivity;

import java.util.ArrayList;
import java.util.Locale;

public class SlideBarAdapter extends RecyclerView.Adapter<SlideBarAdapter.SlideBarViewHolder>{

    private ArrayList<NewsResponseModel> list;

    public SlideBarAdapter(ArrayList<NewsResponseModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public SlideBarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemMiniNewsBinding binding = RvItemMiniNewsBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new SlideBarViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SlideBarViewHolder holder, int position) {
        NewsResponseModel news = list.get(position);

        String time = news.created_at.substring(11,16);
        holder.binding.textViewTime.setText(time);
        holder.binding.textViewTitle.setText(news.title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext().getApplicationContext(), NewsDetailActivity.class);
                intent.putExtra("id", news.id);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SlideBarViewHolder extends RecyclerView.ViewHolder {

        public RvItemMiniNewsBinding binding;

        public SlideBarViewHolder(RvItemMiniNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
