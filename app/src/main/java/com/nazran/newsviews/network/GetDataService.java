package com.nazran.newsviews.network;

import com.nazran.newsviews.models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface GetDataService {

    @GET("/v2/top-headlines?country=us")
    Call<News> getAllArticle(@Header("Authorization") String authHeader);
}
