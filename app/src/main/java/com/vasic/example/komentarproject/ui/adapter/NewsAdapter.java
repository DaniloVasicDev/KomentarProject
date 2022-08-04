package com.vasic.example.komentarproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.vasic.example.komentarproject.databinding.RvItemBigNewsBinding;
import com.vasic.example.komentarproject.databinding.RvItemSmallNewsBinding;
import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;
import com.vasic.example.komentarproject.ui.itemmodel.newsitem.RecyclerViewItemModel;
import com.vasic.example.komentarproject.ui.itemmodel.newsitem.RvItemBigNews;
import com.vasic.example.komentarproject.ui.itemmodel.newsitem.RvItemSmallNews;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private ArrayList<NewsResponseModel> news;
    private ArrayList<RecyclerViewItemModel> items;
    private Context context;

    public NewsAdapter(ArrayList<NewsResponseModel> news,Context context) {
        this.news = news;
        this.items = new ArrayList<>();
        this.context = context;

            for(int i=0;i<news.size();i++){
            NewsResponseModel newsModel = news.get(i);
            if(i ==0 ){
                this.items.add(new RvItemBigNews(newsModel));
            }
            else{
                this.items.add(new RvItemSmallNews(newsModel));
            }
        }

    }

    public NewsAdapter(ArrayList<NewsResponseModel> news,Context context,String title) {
        this.news = news;
        this.items = new ArrayList<>();
        this.context = context;

        for(int i=0;i<5;i++){
            NewsResponseModel newsModel = news.get(i);
            if(i ==0 ){
                this.items.add(new RvItemBigNews(newsModel));
            }
            else{
                this.items.add(new RvItemSmallNews(newsModel));
            }
        }

    }

    public NewsAdapter(ArrayList<NewsResponseModel> news){
        this.news = news;
        this.items = new ArrayList<>();

        if(news!=null){
            for(int i = 0;i<news.size();i++){
                NewsResponseModel newsModel = news.get(i);
                this.items.add(new RvItemSmallNews(newsModel));
            }
        }

    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewBinding binding = null;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case 0:
                binding = RvItemBigNewsBinding.inflate(inflater,parent,false);
                break;
            default:
                binding = RvItemSmallNewsBinding.inflate(inflater,parent,false);
                break;

        }

        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
       this.items.get(position).bindNews(holder);
    }



    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return this.items.get(position).getType();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        public ViewBinding binding;
        public NewsViewHolder(ViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
