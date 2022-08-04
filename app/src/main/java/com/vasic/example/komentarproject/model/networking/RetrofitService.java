package com.vasic.example.komentarproject.model.networking;

import com.vasic.example.komentarproject.model.response.homepage.HomePageResponse;
import com.vasic.example.komentarproject.model.response.menu.MenuResponseModel;
import com.vasic.example.komentarproject.model.response.news.ResponseModel;
import com.vasic.example.komentarproject.model.response.newsdetail.NewsDetailResponseModel;
import com.vasic.example.komentarproject.model.response.newsdetail.comment.CommentResponseModel;
import com.vasic.example.komentarproject.model.response.newsdetail.commentinsert.BodyInsertResponse;
import com.vasic.example.komentarproject.model.response.newsdetail.commentinsert.CommentInsertResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("api/latest")
    Call<ResponseModel>getLatestNews();

    @GET("api/videos")
    Call<ResponseModel>getNewsVideos();

    @GET("api/search")
    Call<ResponseModel> getNewsBySearch(@Query("search_parameter") String term);

    @GET("api/newsdetails")
    Call<NewsDetailResponseModel> getReletedNews(@Query("id") int id);

    @GET("api/comments")
    Call<CommentResponseModel> getComments(@Query("id") int id);

    @GET("api/categories")
    Call<MenuResponseModel> getCategories();

    @GET("api/tag")
    Call<ResponseModel> getNewsByTag(@Query("tag") int tag);

    @POST("api/commentvote")
    Call<CommentResponseModel> postLike(@Query("comment") int id, @Query("vote") boolean vote);

    @POST("api/commentvote")
    Call<CommentResponseModel> postDislike(@Query("comment") int id, @Query("downvote") boolean vote);

    @GET("api/category/{id}")
    Call<ResponseModel> getCategory(@Path("id") int id);

    @GET("api/homepage")
    Call<HomePageResponse> getHomePageNews();

    @POST("api/commentinsert")
    Call<BodyInsertResponse> createPost(@Body BodyInsertResponse body);

}
