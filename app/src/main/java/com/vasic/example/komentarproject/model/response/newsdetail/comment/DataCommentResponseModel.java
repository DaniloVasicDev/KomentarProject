package com.vasic.example.komentarproject.model.response.newsdetail.comment;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class DataCommentResponseModel implements Parcelable {

    public int negative_votes;
    public int positive_votes;
    public String created_at;
    public String news;
    public String name;
    public String parent_comment;
    public String id;
    public String content;
    public ArrayList<DataCommentResponseModel> children;


    protected DataCommentResponseModel(Parcel in) {
        negative_votes = in.readInt();
        positive_votes = in.readInt();
        created_at = in.readString();
        news = in.readString();
        name = in.readString();
        parent_comment = in.readString();
        id = in.readString();
        content = in.readString();
        children = in.createTypedArrayList(DataCommentResponseModel.CREATOR);
    }

    public static final Creator<DataCommentResponseModel> CREATOR = new Creator<DataCommentResponseModel>() {
        @Override
        public DataCommentResponseModel createFromParcel(Parcel in) {
            return new DataCommentResponseModel(in);
        }

        @Override
        public DataCommentResponseModel[] newArray(int size) {
            return new DataCommentResponseModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(negative_votes);
        parcel.writeInt(positive_votes);
        parcel.writeString(created_at);
        parcel.writeString(news);
        parcel.writeString(name);
        parcel.writeString(parent_comment);
        parcel.writeString(id);
        parcel.writeString(content);
        parcel.writeTypedList(children);
    }
}
