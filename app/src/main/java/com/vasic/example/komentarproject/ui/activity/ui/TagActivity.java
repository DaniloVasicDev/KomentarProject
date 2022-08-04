package com.vasic.example.komentarproject.ui.activity.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.vasic.example.komentarproject.R;
import com.vasic.example.komentarproject.databinding.ActivityTagBinding;
import com.vasic.example.komentarproject.model.networking.RetrofitService;
import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;
import com.vasic.example.komentarproject.model.response.news.ResponseModel;
import com.vasic.example.komentarproject.ui.adapter.NewsAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TagActivity extends AppCompatActivity {

    private ActivityTagBinding binding;
    private int id;
    private String title;
    private RetrofitService service;
    private ArrayList<NewsResponseModel> newsList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTagBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        id = getIntent().getExtras().getInt("tagId");
        title = getIntent().getExtras().getString("tagTitle");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://komentar.rs/wp-json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(RetrofitService .class);

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.textViewTitle.setText(title);

        service.getNewsByTag(id).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                newsList = response.body().data.news;
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                binding.recyclerView.setAdapter(new NewsAdapter(newsList));
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
            }
        });

    }
}