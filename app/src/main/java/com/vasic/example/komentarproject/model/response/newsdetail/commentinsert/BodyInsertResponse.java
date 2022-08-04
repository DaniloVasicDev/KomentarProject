package com.vasic.example.komentarproject.model.response.newsdetail.commentinsert;

public class BodyInsertResponse {

    public String news;

    public String name;
    public String email;
    public String content;

    public BodyInsertResponse(String news,String name, String email, String content) {
        this.news = news;
        this.name = name;
        this.email = email;
        this.content = content;
    }
}
