package com.vasic.example.komentarproject.ui.adapter.comment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vasic.example.komentarproject.databinding.RvItemCommentDetailsNewsBinding;
import com.vasic.example.komentarproject.databinding.RvItemSubcategoryBinding;
import com.vasic.example.komentarproject.model.networking.RetrofitService;
import com.vasic.example.komentarproject.model.response.newsdetail.comment.CommentResponseModel;
import com.vasic.example.komentarproject.model.response.newsdetail.comment.DataCommentResponseModel;
import com.vasic.example.komentarproject.ui.activity.ui.PostCommentActivity;
import com.vasic.example.komentarproject.ui.adapter.menu.SubcategoryAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommetnReplyAdapter extends RecyclerView.Adapter<CommetnReplyAdapter.CommentReplyViewHolder>{

    private ArrayList<DataCommentResponseModel> list;
    private boolean isExpandeble = false;
    private RetrofitService service;


    public CommetnReplyAdapter(ArrayList<DataCommentResponseModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CommentReplyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemCommentDetailsNewsBinding binding = RvItemCommentDetailsNewsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new CommentReplyViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull CommentReplyViewHolder holder, int position) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://komentar.rs/wp-json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(RetrofitService.class);
        DataCommentResponseModel data = list.get(position);
        holder.binding.textViewPerson.setText(data.name);
        holder.binding.textViewDate.setText(data.created_at);
        holder.binding.textViewContent.setText(data.content);
        holder.binding.textViewCountLike.setText(data.positive_votes+"");
        holder.binding.textViewCountUnLike.setText(data.negative_votes+"");
        holder.binding.textViewAnswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.children!=null){
                    if(!isExpandeble){
                        if(data.children.size()>0){
                            holder.binding.commentReplyContainer.setVisibility(View.VISIBLE);
                            holder.binding.recyclerViewCommentRepley.setLayoutManager(new LinearLayoutManager(view.getContext()));
                            holder.binding.recyclerViewCommentRepley.setAdapter(new CommetnReplyAdapter(data.children));
                            isExpandeble = true;
                        }
                    }
                    else if(isExpandeble){
                        if(data.children.size()>0){
                            holder.binding.commentReplyContainer.setVisibility(View.GONE);
                            holder.binding.recyclerViewCommentRepley.setLayoutManager(new LinearLayoutManager(view.getContext()));
                            holder.binding.recyclerViewCommentRepley.setAdapter(new CommetnReplyAdapter(data.children));
                            isExpandeble = false;
                        }
                    }
                }
            }
        });
        holder.binding.imageVIewLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.postLike(Integer.parseInt(data.id),true).enqueue(new Callback<CommentResponseModel>() {
                    @Override
                    public void onResponse(Call<CommentResponseModel> call, Response<CommentResponseModel> response) {
                        Toast.makeText(view.getContext().getApplicationContext(),"Uspesno ste lakjovali",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<CommentResponseModel> call, Throwable t) {

                    }
                });
            }
        });
        holder.binding.imageViewUnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.postDislike(Integer.parseInt(data.id),true).enqueue(new Callback<CommentResponseModel>() {
                    @Override
                    public void onResponse(Call<CommentResponseModel> call, Response<CommentResponseModel> response) {
                        Toast.makeText(view.getContext().getApplicationContext(),"Uspesno ste dislajkovali komentar",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<CommentResponseModel> call, Throwable t) {

                    }
                });
            }
        });
        holder.binding.buttonReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(view.getContext().getApplicationContext(), PostCommentActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CommentReplyViewHolder extends RecyclerView.ViewHolder {

        public RvItemCommentDetailsNewsBinding binding;

        public CommentReplyViewHolder(RvItemCommentDetailsNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
