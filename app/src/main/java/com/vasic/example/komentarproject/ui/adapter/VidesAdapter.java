package com.vasic.example.komentarproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.vasic.example.komentarproject.databinding.RvItemVideoBinding;
import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;
import com.vasic.example.komentarproject.ui.itemmodel.newsitem.RecyclerViewItemModel;
import com.vasic.example.komentarproject.ui.itemmodel.newsitem.RvItemVideo;

import java.util.ArrayList;

public class VidesAdapter extends RecyclerView.Adapter<VidesAdapter.VideoViewHolder> {

    private ArrayList<NewsResponseModel> news;
    private ArrayList<RecyclerViewItemModel> items;
    private Context context;

    public VidesAdapter(ArrayList<NewsResponseModel> news, Context context) {
        this.news = news;
        this.context = context;
        this.items = new ArrayList<>();

        for(NewsResponseModel newsModel: news){
            this.items.add(new RvItemVideo(newsModel,context));
        }
    }



    @NonNull
    @Override
    public VidesAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewBinding binding = null;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case 0:
                binding = RvItemVideoBinding.inflate(inflater,parent,false);
                break;
            default:
                break;

        }

        return new VideoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        this.items.get(position).bindVideos(holder);
    }


    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return  this.items.get(position).getType();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        public ViewBinding binding;
        public VideoViewHolder(ViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
