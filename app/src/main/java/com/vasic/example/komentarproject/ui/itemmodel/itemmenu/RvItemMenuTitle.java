package com.vasic.example.komentarproject.ui.itemmodel.itemmenu;

import com.vasic.example.komentarproject.databinding.RvItemMenuTitleBinding;
import com.vasic.example.komentarproject.ui.adapter.menu.MenuAdapter;

public class RvItemMenuTitle implements RecyclerViewMenuItem{
    private String title;

    public RvItemMenuTitle(String title) {
        this.title = title;
    }

    @Override
    public int getType() {
        return 1;
    }

    @Override
    public void bind(MenuAdapter.MenuViewHodler holder) {
        RvItemMenuTitleBinding binding = (RvItemMenuTitleBinding) holder.binding;
        binding.textViewTitle.setText(title);
    }
}
