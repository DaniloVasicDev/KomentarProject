package com.vasic.example.komentarproject.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vasic.example.komentarproject.databinding.FragmentSearchBinding;
import com.vasic.example.komentarproject.model.networking.RetrofitService;
import com.vasic.example.komentarproject.model.response.news.ResponseModel;
import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;
import com.vasic.example.komentarproject.ui.adapter.NewsAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private ArrayList<NewsResponseModel> newsList = new ArrayList<>();
    private RetrofitService service;


    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
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
         binding =  FragmentSearchBinding.inflate(inflater,container,false);

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
        loadData();
        updateUI();
    }

    private void updateUI(){

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(new NewsAdapter(newsList));

    }

    private void loadData(){
        binding.buttonSearch.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                service.getNewsBySearch(String.valueOf(binding.textViewSearch.getText())).enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        if(binding.textViewSearch.getText().length()==0){
                            Toast.makeText(getContext(),"Unesite pojam u polje pretrazi",Toast.LENGTH_LONG).show();
                        }
                        else{
                            if(response.body().data.news.size()>0){
                                newsList = response.body().data.news;
                                updateUI();
                                binding.textViewNoContent.setVisibility(View.GONE);
                            }
                            else{
                                binding.textViewNoContent.setVisibility(View.VISIBLE);
                                binding.textViewNoContent.setText("Ne posotje rezulatati za uneti termin  "+binding.textViewSearch.getText().toString());
                            }

                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(getContext(),"Proverite internet konekciju",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}