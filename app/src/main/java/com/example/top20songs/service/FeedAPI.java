package com.example.top20songs.service;

import com.example.top20songs.model.Feed;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FeedAPI {
    //Non-static feed name
    @GET("topsongs/xml")
    Call<Feed> getFeedList();
}
