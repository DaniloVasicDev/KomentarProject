package com.vasic.example.komentarproject.ui.itemmodel.newsdetalitem;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.vasic.example.komentarproject.databinding.RvItemCommentDetailsNewsBinding;
import com.vasic.example.komentarproject.model.networking.RetrofitService;
import com.vasic.example.komentarproject.model.response.newsdetail.comment.CommentResponseModel;
import com.vasic.example.komentarproject.model.response.newsdetail.comment.DataCommentResponseModel;
import com.vasic.example.komentarproject.ui.activity.ui.PostCommentActivity;
import com.vasic.example.komentarproject.ui.adapter.NewsDetailAdapter;
import com.vasic.example.komentarproject.ui.adapter.comment.CommetnReplyAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RvItemComment implements RecyclerViewItemDetailsModel{

    private DataCommentResponseModel dataComment;
    private boolean isExpandeble = false;
    private RetrofitService service;

    public RvItemComment(DataCommentResponseModel dataComment) {
        this.dataComment = dataComment;
    }

    @Override
    public int getTypeModel() {
        return 4;
    }

    @Override
    public void bindNewsDetails(NewsDetailAdapter.DetailsViewHolder holder) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://komentar.rs/wp-json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(RetrofitService .class);

        RvItemCommentDetailsNewsBinding binding = (RvItemCommentDetailsNewsBinding) holder.binding;
        binding.textViewPerson.setText(dataComment.name);
        binding.textViewDate.setText(dataComment.created_at);
        binding.textViewContent.setText(dataComment.content);
        binding.textViewCountLike.setText(dataComment.positive_votes+"");
        binding.textViewCountUnLike.setText(dataComment.negative_votes+"");
        binding.textViewAnswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isExpandeble){
                    if(dataComment.children.size()>0){
                        binding.commentReplyContainer.setVisibility(View.VISIBLE);
                        binding.recyclerViewCommentRepley.setLayoutManager(new LinearLayoutManager(view.getContext()));
                        binding.recyclerViewCommentRepley.setAdapter(new CommetnReplyAdapter(dataComment.children));
                        isExpandeble = true;
                    }
                }
                else if(isExpandeble){
                    if(dataComment.children.size()>0){
                        binding.commentReplyContainer.setVisibility(View.GONE);
                        binding.recyclerViewCommentRepley.setLayoutManager(new LinearLayoutManager(view.getContext()));
                        binding.recyclerViewCommentRepley.setAdapter(new CommetnReplyAdapter(dataComment.children));
                        isExpandeble = false;
                    }
                }
            }
        });
        binding.imageVIewLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            service.postLike(Integer.parseInt(dataComment.id),true).enqueue(new Callback<CommentResponseModel>() {
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
        binding.imageViewUnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.postDislike(Integer.parseInt(dataComment.id),true).enqueue(new Callback<CommentResponseModel>() {
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
        binding.buttonReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(),PostCommentActivity.class);
                intent.putExtra("post_comment_id",dataComment.id);
                holder.itemView.getContext().startActivity(intent);

            }
        });

    }
}
