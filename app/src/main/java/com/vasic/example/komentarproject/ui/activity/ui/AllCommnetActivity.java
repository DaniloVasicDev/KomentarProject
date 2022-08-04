package com.vasic.example.komentarproject.ui.activity.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.vasic.example.komentarproject.R;
import com.vasic.example.komentarproject.databinding.ActivityAllCommnetBinding;
import com.vasic.example.komentarproject.model.response.newsdetail.comment.DataCommentResponseModel;
import com.vasic.example.komentarproject.ui.adapter.comment.CommetnReplyAdapter;

import java.util.ArrayList;

public class AllCommnetActivity extends AppCompatActivity {

    private ActivityAllCommnetBinding binding;
    private ArrayList<DataCommentResponseModel> commentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllCommnetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        commentList = getIntent().getParcelableArrayListExtra("commentList");

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.recyclerView.setAdapter(new CommetnReplyAdapter(commentList));

    }
}