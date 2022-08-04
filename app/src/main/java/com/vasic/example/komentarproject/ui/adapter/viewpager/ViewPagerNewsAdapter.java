package com.vasic.example.komentarproject.ui.adapter.viewpager;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.vasic.example.komentarproject.data.DataContainer;
import com.vasic.example.komentarproject.model.networking.RetrofitService;
import com.vasic.example.komentarproject.model.response.homepage.HomePageDataResponse;
import com.vasic.example.komentarproject.model.response.homepage.HomePageResponse;
import com.vasic.example.komentarproject.model.response.menu.CategoryMenuResponseModel;
import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;
import com.vasic.example.komentarproject.model.response.news.ResponseModel;
import com.vasic.example.komentarproject.ui.fragment.HomePageFragmentVP;
import com.vasic.example.komentarproject.ui.fragment.LatestFragment;
import com.vasic.example.komentarproject.ui.fragment.NewsListFragmentVP;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewPagerNewsAdapter extends FragmentPagerAdapter {

    private ArrayList<CategoryMenuResponseModel> categoryList;
    private Context context;
    private RetrofitService service;
    private ArrayList<NewsResponseModel> list = new ArrayList<>();
    private HomePageDataResponse data;



    public ViewPagerNewsAdapter(@NonNull FragmentManager fm, ArrayList<CategoryMenuResponseModel> categoryList, Context context, HomePageDataResponse data) {
        super(fm);
        this.categoryList = categoryList;
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if(position == 0){
            HomePageFragmentVP fragmentVP =  HomePageFragmentVP.newInstance(context,data);
            return fragmentVP;
        }
        else if(position == 1){

            LatestFragment fragmentVP = LatestFragment.newInstance();
            return fragmentVP;
        }
        else{
            CategoryMenuResponseModel categoryModel = categoryList.get(position-2);

             NewsListFragmentVP fragmentVP = NewsListFragmentVP.newInstance(categoryModel.id,context);
            return fragmentVP;
        }

    }

    @Override
    public int getCount() {
        return categoryList.size()+2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0){
            return  "Naslovna";
        }
        else if(position == 1){
            return "Najnovije";
        }
        else {
            CategoryMenuResponseModel category = categoryList.get(position-2);
            return category.name;
        }
    }
}
