package com.vasic.example.komentarproject.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.vasic.example.komentarproject.R;
import com.vasic.example.komentarproject.data.DataContainer;
import com.vasic.example.komentarproject.model.networking.RetrofitService;
import com.vasic.example.komentarproject.model.response.homepage.HomePageResponse;
import com.vasic.example.komentarproject.model.response.menu.MenuResponseModel;
import com.vasic.example.komentarproject.model.response.news.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashScreenActivity extends AppCompatActivity {

    private RetrofitService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://komentar.rs/wp-json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(RetrofitService .class);

        service.getCategories().enqueue(new Callback<MenuResponseModel>() {
            @Override
            public void onResponse(Call<MenuResponseModel> call, Response<MenuResponseModel> response) {
                DataContainer.categoryList = response.body().data;

                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<MenuResponseModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Proverite internet konekciju",Toast.LENGTH_LONG).show();
            }
        });

    }
}