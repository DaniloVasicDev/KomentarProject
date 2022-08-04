package com.vasic.example.komentarproject.ui.adapter.menu;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.vasic.example.komentarproject.databinding.RvItemMenuBinding;
import com.vasic.example.komentarproject.databinding.RvItemMenuTitleBinding;
import com.vasic.example.komentarproject.databinding.RvItemViewBinding;
import com.vasic.example.komentarproject.model.response.menu.CategoryMenuResponseModel;
import com.vasic.example.komentarproject.ui.itemmodel.itemmenu.RecyclerViewMenuItem;
import com.vasic.example.komentarproject.ui.itemmodel.itemmenu.RvItemMenu;
import com.vasic.example.komentarproject.ui.itemmodel.itemmenu.RvItemMenuTitle;
import com.vasic.example.komentarproject.ui.itemmodel.itemmenu.RvItemView;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHodler>{

    private ArrayList<CategoryMenuResponseModel> categoryList;
    private ArrayList<RecyclerViewMenuItem> items;


    public MenuAdapter(ArrayList<CategoryMenuResponseModel> categoryList) {
        this.categoryList = categoryList;
        this.items = new ArrayList<>();

        if(categoryList!=null){
            for(int i = 0;i<categoryList.size();i++){
                CategoryMenuResponseModel categoryModel = categoryList.get(i);
                this.items.add(new RvItemMenu(categoryModel));
            }
        }


        this.items.add(new RvItemView());

        this.items.add(new RvItemMenuTitle("vremenska prognoza"));
        this.items.add(new RvItemMenuTitle("KURSNA LISTA"));
        this.items.add(new RvItemMenuTitle("HOROSKOP"));

        this.items.add(new RvItemView());

        this.items.add(new RvItemMenuTitle("PUSH NOTIFIKACIJE"));
        this.items.add(new RvItemMenuTitle("MARKETING"));
        this.items.add(new RvItemMenuTitle("USLOVI KORIŠĆENJA"));
        this.items.add(new RvItemMenuTitle("KONTAKT"));



    }

    @NonNull
    @Override
    public MenuViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewBinding binding = null;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case 0:
                binding = RvItemMenuBinding.inflate(inflater,parent,false);
                break;
            case 1:
                binding = RvItemMenuTitleBinding.inflate(inflater,parent,false);
                break;
            case 2:
                binding = RvItemViewBinding.inflate(inflater,parent,false);
                break;
            default:
                break;

        }

        return new MenuViewHodler(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHodler holder, int position) {
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

    public class MenuViewHodler extends RecyclerView.ViewHolder {
        public ViewBinding binding;

        public MenuViewHodler(ViewBinding binding){
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
