package com.nazran.newsviews.network;

import com.nazran.newsviews.models.Date;
import com.nazran.newsviews.models.Number;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataServiceNumber {

    @GET("/{number}?json")
    Call<Number> getNumberDetail(@Path("number") long number);

    @GET("/{month}/{date}?json")
    Call<Date> getDateDetail(@Path("month") int month, @Path("date") int date);
}
