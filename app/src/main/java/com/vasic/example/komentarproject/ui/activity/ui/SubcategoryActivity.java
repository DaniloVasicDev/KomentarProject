package com.vasic.example.komentarproject.ui.activity.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.vasic.example.komentarproject.R;
import com.vasic.example.komentarproject.databinding.ActivitySubcategoryBinding;
import com.vasic.example.komentarproject.model.networking.RetrofitService;
import com.vasic.example.komentarproject.model.response.menu.CategoryMenuResponseModel;
import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;
import com.vasic.example.komentarproject.model.response.news.ResponseModel;
import com.vasic.example.komentarproject.ui.adapter.NewsAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubcategoryActivity extends AppCompatActivity {

    private ActivitySubcategoryBinding binding;
    private RetrofitService service;
    private ArrayList<NewsResponseModel> list;
    private CategoryMenuResponseModel categoryModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubcategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://komentar.rs/wp-json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(RetrofitService.class);

        categoryModel = (CategoryMenuResponseModel) getIntent().getSerializableExtra("subactegory_id");



        service.getCategory(categoryModel.id).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                list = response.body().data.news;
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                binding.recyclerView.setAdapter(new NewsAdapter(list));
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });

        binding.textViewTitle.setText(categoryModel.name);
        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}