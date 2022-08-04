package com.vasic.example.komentarproject.ui.activity.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.vasic.example.komentarproject.R;
import com.vasic.example.komentarproject.databinding.ActivityPostCommentBinding;
import com.vasic.example.komentarproject.model.networking.RetrofitService;
import com.vasic.example.komentarproject.model.response.newsdetail.commentinsert.BodyInsertResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostCommentActivity extends AppCompatActivity {

    private ActivityPostCommentBinding binding;
    private int id;
    private RetrofitService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostCommentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        id = getIntent().getExtras().getInt("post_comment_id");

        String name = binding.textViewName.getText().toString();
        String surname = binding.textViewSurname.getText().toString();
        String content = binding.textViewContent.getText().toString();

        binding.buttonPostComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.textViewName.getText().toString().isEmpty() &&
                        binding.textViewSurname.getText().toString().isEmpty() &&
                        binding.textViewContent.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Morate da popunite sva polja",Toast.LENGTH_LONG).show();
                }
                else{
                    postComment(name,surname,content);
                }
            }
        });

    }

    private void postComment(String name,String surname,String content){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://komentar.rs/wp-json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(RetrofitService.class);

        BodyInsertResponse data = new BodyInsertResponse(String.valueOf(id),name,surname,content);

        service.createPost(data).enqueue(new Callback<BodyInsertResponse>() {
            @Override
            public void onResponse(Call<BodyInsertResponse> call, Response<BodyInsertResponse> response) {
                binding.textViewName.setText("");
                binding.textViewSurname.setText("");
                binding.textViewContent.setText("");
                Toast.makeText(getApplicationContext(),response.code()+"",Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(Call<BodyInsertResponse> call, Throwable t) {

            }
        });

    }
}