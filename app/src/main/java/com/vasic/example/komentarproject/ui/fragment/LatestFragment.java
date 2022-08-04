package com.vasic.example.komentarproject.ui.fragment;

import android.content.Intent;
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
import com.vasic.example.komentarproject.data.DataContainer;
import com.vasic.example.komentarproject.databinding.FragmentLatestBinding;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LatestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LatestFragment extends Fragment {


    public FragmentLatestBinding binding;
    public RetrofitService service;
    private ArrayList<NewsResponseModel> list = new ArrayList<>();

    public LatestFragment() {
        // Required empty public constructor
    }

    public static LatestFragment newInstance( ) {
        LatestFragment fragment = new LatestFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        binding = FragmentLatestBinding.inflate(inflater, container,false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://komentar.rs/wp-json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(RetrofitService .class);

        service.getLatestNews().enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
               list  = response.body().data.news;

                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.recyclerView.setAdapter(new NewsAdapter(list,view.getContext()));
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(view.getContext(),"Proverite internet konekciju",Toast.LENGTH_LONG).show();
            }
        });


    }
}