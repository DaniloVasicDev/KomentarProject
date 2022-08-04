package com.vasic.example.komentarproject.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.vasic.example.komentarproject.databinding.RvItemButtonNewsDetailsBinding;
import com.vasic.example.komentarproject.databinding.RvItemCommentDetailsNewsBinding;
import com.vasic.example.komentarproject.databinding.RvItemHeaderDetailNewsBinding;
import com.vasic.example.komentarproject.databinding.RvItemRvTagBinding;
import com.vasic.example.komentarproject.databinding.RvItemSmallNewsBinding;
import com.vasic.example.komentarproject.databinding.RvItemTitleDetailsNewsBinding;
import com.vasic.example.komentarproject.databinding.RvItemWebiewDetailsNewsBinding;
import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;
import com.vasic.example.komentarproject.model.response.newsdetail.NewsDetailDataResponse;
import com.vasic.example.komentarproject.model.response.newsdetail.comment.DataCommentResponseModel;
import com.vasic.example.komentarproject.ui.itemmodel.newsdetalitem.RecyclerViewItemDetailsModel;
import com.vasic.example.komentarproject.ui.itemmodel.newsdetalitem.RvItemButton;
import com.vasic.example.komentarproject.ui.itemmodel.newsdetalitem.RvItemComment;
import com.vasic.example.komentarproject.ui.itemmodel.newsdetalitem.RvItemHeader;
import com.vasic.example.komentarproject.ui.itemmodel.newsdetalitem.RvItemSmallDetailsNews;
import com.vasic.example.komentarproject.ui.itemmodel.newsdetalitem.RvItemTags;
import com.vasic.example.komentarproject.ui.itemmodel.newsdetalitem.RvItemTitle;
import com.vasic.example.komentarproject.ui.itemmodel.newsdetalitem.RvItemWebView;

import java.util.ArrayList;

public class NewsDetailAdapter extends RecyclerView.Adapter<NewsDetailAdapter.DetailsViewHolder> {


    private NewsDetailDataResponse newsData;
    private ArrayList<RecyclerViewItemDetailsModel> items; //lista itema,celija
    private ArrayList<DataCommentResponseModel> commentList = new ArrayList<>();//lista komentara
    private ArrayList<NewsResponseModel> newsList = new ArrayList<>();


    public NewsDetailAdapter(NewsDetailDataResponse newsData,ArrayList<DataCommentResponseModel> commentList) {
        this.newsData = newsData;
        this.commentList = commentList;
        this.items = new ArrayList<>();

        this.items.add(new RvItemHeader(this.newsData));

        this.items.add(new RvItemWebView(this.newsData));

        if(newsData.tags.size()>0){
            this.items.add(new RvItemTitle("Tagovi:","#143E6C"));
            this.items.add(new RvItemTags(newsData.tags));
        }

        this.items.add(new RvItemTitle("Komentari",newsData,"#143E6C"));


        this.items.add(new RvItemButton(newsData));

        for(int i =0;i<commentList.size();i++){
            DataCommentResponseModel data = commentList.get(i);
            this.items.add(new RvItemComment(data));
        }

        if(commentList.size()>0){
            this.items.add(new RvItemButton("Svi komentari",newsData,commentList));
        }

       if(newsData.related_news.size()>0){
           this.items.add(new RvItemTitle("Povezane vesti:","#88909D"));
           for(int i=0;i<newsData.related_news.size();i++){
               NewsResponseModel newsModel = newsData.related_news.get(i);
               this.items.add(new RvItemSmallDetailsNews(newsModel));
           }
       }


    }

    @NonNull
    @Override
    public NewsDetailAdapter.DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewBinding binding = null;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case 0:
                binding = RvItemHeaderDetailNewsBinding.inflate(inflater,parent,false);
                break;
            case 1:
                binding = RvItemWebiewDetailsNewsBinding.inflate(inflater,parent,false);
                break;
            case 2:
                binding  = RvItemRvTagBinding.inflate(inflater,parent,false);
                break;
            case 3:
                binding = RvItemButtonNewsDetailsBinding.inflate(inflater,parent,false);
                break;
            case 4:
                binding = RvItemCommentDetailsNewsBinding.inflate(inflater,parent,false);
                break;
            case 5:
                binding = RvItemSmallNewsBinding.inflate(inflater,parent,false);
                break;
            default:
                 binding = RvItemTitleDetailsNewsBinding.inflate(inflater,parent,false);
                 break;

        }

        return new NewsDetailAdapter.DetailsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {
        this.items.get(position).bindNewsDetails(holder);

    }


    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return this.items.get(position).getTypeModel();
    }

    public class DetailsViewHolder extends RecyclerView.ViewHolder {
        public ViewBinding binding;
        public DetailsViewHolder(ViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
