package com.vasic.example.komentarproject.ui.adapter;

import android.content.Intent;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.vasic.example.komentarproject.databinding.RvItemSliderBinding;
import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;
import com.vasic.example.komentarproject.ui.activity.NewsDetailActivity;

import java.util.ArrayList;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder>{

    private ArrayList<NewsResponseModel> list;

    public SliderAdapter(ArrayList<NewsResponseModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemSliderBinding binding = RvItemSliderBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new SliderViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        NewsResponseModel newsModel = list.get(position);
        Picasso.get().load(newsModel.image).into(holder.binding.imageView);
        holder.binding.textViewTitle.setText(newsModel.title);
        holder.binding.textViewCategory.setText(newsModel.category.name);
        String time = newsModel.created_at.substring(11,16);
        holder.binding.textViewDate.setText(time);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext().getApplicationContext(), NewsDetailActivity.class);
                intent.putExtra("id",newsModel.id);
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder {

        public RvItemSliderBinding binding;

        public SliderViewHolder(RvItemSliderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
