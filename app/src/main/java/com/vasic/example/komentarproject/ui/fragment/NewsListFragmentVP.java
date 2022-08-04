package com.vasic.example.komentarproject.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vasic.example.komentarproject.R;
import com.vasic.example.komentarproject.databinding.FragmentNewsListVPBinding;
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

public class NewsListFragmentVP extends Fragment {

    private FragmentNewsListVPBinding binding;
    private ArrayList<NewsResponseModel> newsList;
    private Context context;
    public int id;
    public RetrofitService service;



    public NewsListFragmentVP() {
        // Required empty public constructor
    }

    public static NewsListFragmentVP newInstance(int id,Context context) {
        NewsListFragmentVP fragment = new NewsListFragmentVP();
      fragment.id =id;
      fragment.context = context;
      fragment.newsList = new ArrayList<>();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewsListVPBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://komentar.rs/wp-json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(RetrofitService.class);

            service.getCategory(id).enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    newsList = response.body().data.news;

                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    binding.recyclerView.setAdapter(new NewsAdapter(newsList,context));
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    //Toast.makeText(context,"error",Toast.LENGTH_LONG).show();
                }

            });




    }
}