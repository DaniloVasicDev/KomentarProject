package com.vasic.example.komentarproject.ui.itemmodel.newsdetalitem;

import android.webkit.WebViewClient;

import com.vasic.example.komentarproject.databinding.RvItemWebiewDetailsNewsBinding;
import com.vasic.example.komentarproject.model.response.newsdetail.NewsDetailDataResponse;
import com.vasic.example.komentarproject.ui.adapter.NewsDetailAdapter;

public class RvItemWebView implements RecyclerViewItemDetailsModel{

    private NewsDetailDataResponse newsModel;

    public RvItemWebView(NewsDetailDataResponse newsModel) {
        this.newsModel = newsModel;
    }

    @Override
    public int getTypeModel() {
        return 1;
    }

    @Override
    public void bindNewsDetails(NewsDetailAdapter.DetailsViewHolder holder) {
        RvItemWebiewDetailsNewsBinding binding = (RvItemWebiewDetailsNewsBinding) holder.binding;
        binding.webView.setWebViewClient(new WebViewClient());
        binding.webView.loadUrl(newsModel.url);
    }
}
