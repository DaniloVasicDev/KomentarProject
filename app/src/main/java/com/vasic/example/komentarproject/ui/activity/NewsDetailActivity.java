package com.vasic.example.komentarproject.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.vasic.example.komentarproject.databinding.ActivityNewsDetailBinding;
import com.vasic.example.komentarproject.model.networking.RetrofitService;
import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;
import com.vasic.example.komentarproject.model.response.newsdetail.NewsDetailDataResponse;
import com.vasic.example.komentarproject.model.response.newsdetail.NewsDetailResponseModel;
import com.vasic.example.komentarproject.model.response.newsdetail.comment.CommentResponseModel;
import com.vasic.example.komentarproject.model.response.newsdetail.comment.DataCommentResponseModel;
import com.vasic.example.komentarproject.ui.activity.ui.AllCommnetActivity;
import com.vasic.example.komentarproject.ui.adapter.NewsDetailAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsDetailActivity extends AppCompatActivity {

    private ActivityNewsDetailBinding binding;
    private NewsResponseModel newsModel;
    private RetrofitService service;
    private NewsDetailDataResponse newsData;
    private ArrayList<DataCommentResponseModel> commentList;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewsDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        id = getIntent().getExtras().getInt("id");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://komentar.rs/wp-json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(RetrofitService .class);

         service.getReletedNews(id).enqueue(new Callback<NewsDetailResponseModel>() {
             @Override
             public void onResponse(Call<NewsDetailResponseModel> call, Response<NewsDetailResponseModel> response) {
                 newsData = response.body().data;

                 service.getComments(id).enqueue(new Callback<CommentResponseModel>() {
                     @Override
                     public void onResponse(Call<CommentResponseModel> call, Response<CommentResponseModel> response) {
                         commentList = response.body().data;

                         binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                         binding.recyclerView.setAdapter(new NewsDetailAdapter(newsData,commentList));
                     }

                     @Override
                     public void onFailure(Call<CommentResponseModel> call, Throwable t) {

                     }
                 });

             }

             @Override
             public void onFailure(Call<NewsDetailResponseModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
             }
         });

        //button back
        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //button share
        binding.buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,newsData.url);
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });

        binding.buttonComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AllCommnetActivity.class);
                intent.putExtra("commentList",commentList);
                 startActivity(intent);

            }
        });

    }
}