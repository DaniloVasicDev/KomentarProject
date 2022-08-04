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
import com.vasic.example.komentarproject.data.DataContainer;
import com.vasic.example.komentarproject.databinding.FragmentHomePageVPBinding;
import com.vasic.example.komentarproject.model.networking.RetrofitService;
import com.vasic.example.komentarproject.model.response.homepage.HomePageDataResponse;
import com.vasic.example.komentarproject.model.response.homepage.HomePageResponse;
import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;
import com.vasic.example.komentarproject.ui.adapter.viewpager.HomePageAdapter;
import com.vasic.example.komentarproject.ui.adapter.viewpager.ViewPagerNewsAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePageFragmentVP#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageFragmentVP extends Fragment {

    private FragmentHomePageVPBinding binding;
    private Context context;
    private HomePageDataResponse dataResponse;
    public HomePageFragmentVP() {

    }

    public static HomePageFragmentVP newInstance(Context context, HomePageDataResponse dataResponse) {
        HomePageFragmentVP fragment = new HomePageFragmentVP();
        fragment.context = context;
        fragment.dataResponse = dataResponse;
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
         binding = FragmentHomePageVPBinding.inflate(inflater,container,false);
         return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        binding.recyclerView.setAdapter(new HomePageAdapter(getActivity().getApplicationContext(),dataResponse));

    }
}