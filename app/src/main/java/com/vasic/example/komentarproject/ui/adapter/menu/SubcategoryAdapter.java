package com.vasic.example.komentarproject.ui.adapter.menu;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vasic.example.komentarproject.databinding.RvItemSubcategoryBinding;
import com.vasic.example.komentarproject.databinding.RvItemTagsDetailsNewsBinding;
import com.vasic.example.komentarproject.model.response.menu.CategoryMenuResponseModel;
import com.vasic.example.komentarproject.ui.activity.ui.SubcategoryActivity;
import com.vasic.example.komentarproject.ui.adapter.TagsAdapter;

import java.util.ArrayList;

public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryAdapter.SubcategoryViewHolder>{

    private ArrayList<CategoryMenuResponseModel> list;
    private CategoryMenuResponseModel categoryModel;


    public SubcategoryAdapter(ArrayList<CategoryMenuResponseModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public SubcategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemSubcategoryBinding binding = RvItemSubcategoryBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new SubcategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SubcategoryViewHolder holder, int position) {

        holder.binding.textViewTitle.setText(list.get(position).name);
        holder.binding.textViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SubcategoryActivity.class);
                intent.putExtra("subactegory_id",list.get(position));
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SubcategoryViewHolder extends RecyclerView.ViewHolder {

        public RvItemSubcategoryBinding binding;
        public SubcategoryViewHolder(RvItemSubcategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
