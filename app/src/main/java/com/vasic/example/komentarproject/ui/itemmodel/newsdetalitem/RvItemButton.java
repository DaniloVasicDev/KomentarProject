package com.vasic.example.komentarproject.ui.itemmodel.newsdetalitem;

import android.content.Intent;
import android.view.View;

import com.vasic.example.komentarproject.databinding.RvItemButtonNewsDetailsBinding;
import com.vasic.example.komentarproject.model.response.newsdetail.NewsDetailDataResponse;
import com.vasic.example.komentarproject.model.response.newsdetail.comment.DataCommentResponseModel;
import com.vasic.example.komentarproject.ui.activity.ui.AllCommnetActivity;
import com.vasic.example.komentarproject.ui.activity.ui.PostCommentActivity;
import com.vasic.example.komentarproject.ui.adapter.NewsDetailAdapter;

import java.util.ArrayList;

public class RvItemButton implements RecyclerViewItemDetailsModel{

    public String name;
    public NewsDetailDataResponse data;
    public ArrayList<DataCommentResponseModel> commetList;

    public RvItemButton(NewsDetailDataResponse newsData) {
        this.data = newsData;
    }

    public RvItemButton(String name, NewsDetailDataResponse data,ArrayList<DataCommentResponseModel> commetList) {
        this.name = name;
        this.data = data;
        this.commetList = commetList;
    }

    @Override
    public int getTypeModel() {
        return 3;
    }

    @Override
    public void bindNewsDetails(NewsDetailAdapter.DetailsViewHolder holder) {
        RvItemButtonNewsDetailsBinding binding = (RvItemButtonNewsDetailsBinding) holder.binding;
        if(commetList!=null){
                binding.textViewTitle.setText(name);
                binding.plusIcon.setVisibility(View.GONE);
                binding.allComments.setVisibility(View.VISIBLE);
                binding.textViewCountComment.setVisibility(View.VISIBLE);
                binding.textViewCountComment.setText(""+data.comments_count);
                binding.imageView7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext().getApplicationContext(), AllCommnetActivity.class);
                        intent.putExtra("commentList",commetList);
                        view.getContext().startActivity(intent);
                    }
                });

        }
        if(binding.plusIcon.getVisibility() == View.VISIBLE){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(holder.itemView.getContext(),PostCommentActivity.class);
                    intent.putExtra("post_comment_id",data.id);
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
