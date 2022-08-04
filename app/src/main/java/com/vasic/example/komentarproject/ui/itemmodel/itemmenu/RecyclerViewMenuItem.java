package com.vasic.example.komentarproject.ui.itemmodel.itemmenu;

import com.vasic.example.komentarproject.ui.adapter.menu.MenuAdapter;

public interface RecyclerViewMenuItem {
    int getType();
    void bind(MenuAdapter.MenuViewHodler holder);
}
