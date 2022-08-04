package com.vasic.example.komentarproject.ui.itemmodel.itemmenu;

import com.vasic.example.komentarproject.databinding.RvItemViewBinding;
import com.vasic.example.komentarproject.ui.adapter.menu.MenuAdapter;

public class RvItemView implements RecyclerViewMenuItem{

    public RvItemView() {
    }

    @Override
    public int getType() {
        return 2;
    }

    @Override
    public void bind(MenuAdapter.MenuViewHodler holder) {
        RvItemViewBinding binding = (RvItemViewBinding) holder.binding;
    }
}
