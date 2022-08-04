package com.vasic.example.komentarproject.ui.adapter.viewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.vasic.example.komentarproject.data.DataContainer;
import com.vasic.example.komentarproject.databinding.RvItemEditorsChoiceBinding;
import com.vasic.example.komentarproject.databinding.RvItemRvCategoryboxBinding;
import com.vasic.example.komentarproject.databinding.RvItemRvSliderBinding;
import com.vasic.example.komentarproject.databinding.RvItemSlideBarTopBinding;
import com.vasic.example.komentarproject.databinding.RvItemSmallNewsBinding;
import com.vasic.example.komentarproject.databinding.RvItemVideoBoxBinding;
import com.vasic.example.komentarproject.model.response.homepage.HomePageCategoryResponse;
import com.vasic.example.komentarproject.model.response.homepage.HomePageDataResponse;
import com.vasic.example.komentarproject.model.response.homepage.HomePageResponse;
import com.vasic.example.komentarproject.model.response.news.NewsResponseModel;
import com.vasic.example.komentarproject.ui.itemmodel.homepage.RecyclerViewHomePageItem;
import com.vasic.example.komentarproject.ui.itemmodel.homepage.RvItemCategoryBox;
import com.vasic.example.komentarproject.ui.itemmodel.homepage.RvItemEditorsChoice;
import com.vasic.example.komentarproject.ui.itemmodel.homepage.RvItemSlideBarTop;
import com.vasic.example.komentarproject.ui.itemmodel.homepage.RvItemSlider;
import com.vasic.example.komentarproject.ui.itemmodel.homepage.RvItemSmallNewsTop;
import com.vasic.example.komentarproject.ui.itemmodel.homepage.RvItemVideoBox;

import java.util.ArrayList;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.HomePageViewHolder>{

    private ArrayList<RecyclerViewHomePageItem> items;
    private Context context;
    private HomePageDataResponse dataResponse;


    public HomePageAdapter(Context context,HomePageDataResponse dataResponse) {
        this.items = new ArrayList<>();
        this.context = context;
        this.dataResponse = dataResponse;
        //Slider
        this.items.add(new RvItemSlider(dataResponse.slider, context));

        //Top
        for(int i=0;i<dataResponse.top.size();i++){
            NewsResponseModel newsModel = dataResponse.top.get(i);
            this.items.add(new RvItemSmallNewsTop(newsModel));
        }

        //EditorChoice
        if(dataResponse.editors_choice.size()>0){
            this.items.add(new RvItemEditorsChoice(dataResponse.editors_choice));
        }

        //SliderBarTop
        this.items.add(new RvItemSlideBarTop(dataResponse.most_read,dataResponse.latest,dataResponse.most_comented));

       // CategoryBox
        HomePageCategoryResponse categoryModel = dataResponse.category.get(0);
        this.items.add(new RvItemCategoryBox(categoryModel.news,categoryModel.title,categoryModel.color));

        //VideoBox
        this.items.add(new RvItemVideoBox(dataResponse.videos));

        //CategoryBox
        for(int i =1;i<dataResponse.category.size();i++){
            HomePageCategoryResponse category = dataResponse.category.get(i);
            this.items.add(new RvItemCategoryBox(category.news, category.title, category.color));
        }

    }

    @NonNull
    @Override
    public HomePageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ViewBinding binding= null;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case 0:
                binding = RvItemRvSliderBinding.inflate(inflater,parent,false);
                break;
            case 1:
                binding = RvItemSmallNewsBinding.inflate(inflater,parent,false);
                break;
            case 2:
                binding = RvItemSlideBarTopBinding.inflate(inflater,parent,false);
                break;
            case 3:
                binding = RvItemEditorsChoiceBinding.inflate(inflater,parent,false);
                break;
            case 4:
                binding = RvItemVideoBoxBinding.inflate(inflater,parent,false);
                break;
            default:
                binding = RvItemRvCategoryboxBinding.inflate(inflater,parent,false);
                break;
        }

        return new HomePageAdapter.HomePageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomePageViewHolder holder, int position) {
        this.items.get(position).bind(holder);
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return this.items.get(position).getType();
    }

    public class HomePageViewHolder extends RecyclerView.ViewHolder {

        public ViewBinding binding;

        public HomePageViewHolder(ViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
