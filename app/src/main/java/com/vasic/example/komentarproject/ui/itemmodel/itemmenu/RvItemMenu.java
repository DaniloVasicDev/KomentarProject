package com.vasic.example.komentarproject.ui.itemmodel.itemmenu;

import android.graphics.Color;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.vasic.example.komentarproject.R;
import com.vasic.example.komentarproject.databinding.RvItemMenuBinding;
import com.vasic.example.komentarproject.model.response.menu.CategoryMenuResponseModel;
import com.vasic.example.komentarproject.ui.adapter.menu.MenuAdapter;
import com.vasic.example.komentarproject.ui.adapter.menu.SubcategoryAdapter;

public class RvItemMenu implements RecyclerViewMenuItem {

    private CategoryMenuResponseModel categoryModel;
    private boolean isExpandable = false;

    public RvItemMenu(CategoryMenuResponseModel categoryModel) {
        this.categoryModel = categoryModel;
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public void bind(MenuAdapter.MenuViewHodler holder) {
        RvItemMenuBinding binding = (RvItemMenuBinding) holder.binding;
        if(categoryModel.subcategories.size()>0){
            binding.textViewtitleCategory.setText(categoryModel.name);
            binding.imageViewMore.setVisibility(View.VISIBLE);
            binding.view.setBackgroundColor(Color.parseColor(categoryModel.color));
            binding.imageViewMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 if(!isExpandable){
                     binding.sybcategoryContainerd.setVisibility(View.VISIBLE);
                     binding.imageViewMore.setVisibility(View.VISIBLE);
                     binding.imageViewMore.setImageDrawable(view.getResources().getDrawable(R.drawable.ic_back));
                     binding.imageViewMore.setRotation(90);
                     binding.viewSybcategory.setBackgroundColor(Color.parseColor(categoryModel.color));
                     binding.recyclerViewSybcategory.setLayoutManager(new LinearLayoutManager(view.getContext()));
                     binding.recyclerViewSybcategory.setAdapter(new SubcategoryAdapter(categoryModel.subcategories));
                     isExpandable = true;
                 }
                 else if(isExpandable){
                     binding.sybcategoryContainerd.setVisibility(View.GONE);
                     binding.imageViewMore.setVisibility(View.VISIBLE);
                     binding.imageViewMore.setImageDrawable(view.getResources().getDrawable(R.drawable.ic_back));
                     binding.imageViewMore.setRotation(270);
                     binding.viewSybcategory.setBackgroundColor(Color.parseColor(categoryModel.color));
                     binding.recyclerViewSybcategory.setLayoutManager(new LinearLayoutManager(view.getContext()));
                     binding.recyclerViewSybcategory.setAdapter(new SubcategoryAdapter(categoryModel.subcategories));
                     isExpandable = false;
                 }
                }
            });
        }
        binding.textViewtitleCategory.setText(categoryModel.name);
        binding.view.setBackgroundColor(Color.parseColor(categoryModel.color));
    }

}
