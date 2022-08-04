package com.vasic.example.komentarproject.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vasic.example.komentarproject.data.DataContainer;
import com.vasic.example.komentarproject.databinding.FragmentHomeBinding;
import com.vasic.example.komentarproject.model.networking.RetrofitService;
import com.vasic.example.komentarproject.model.response.homepage.HomePageDataResponse;
import com.vasic.example.komentarproject.model.response.homepage.HomePageResponse;
import com.vasic.example.komentarproject.ui.adapter.viewpager.ViewPagerNewsAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RetrofitService service;
    private HomePageDataResponse data;

    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();

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
       binding = FragmentHomeBinding.inflate(inflater,container,false);
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

        service.getHomePageNews().enqueue(new Callback<HomePageResponse>() {
            @Override
            public void onResponse(Call<HomePageResponse> call, Response<HomePageResponse> response) {
                data = response.body().data;
                ViewPagerNewsAdapter adapter = new ViewPagerNewsAdapter(getParentFragmentManager(), DataContainer.categoryList,getActivity().getApplicationContext(),data);
                binding.viewPager.setAdapter(adapter);
                binding.viewPager.setCurrentItem(0);
                binding.tabLayout.setupWithViewPager(binding.viewPager);
            }

            @Override
            public void onFailure(Call<HomePageResponse> call, Throwable t) {
                Toast.makeText(view.getContext(),"Proverite internet konekciju",Toast.LENGTH_LONG).show();
            }
        });



    }

}