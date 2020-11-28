package com.newsapp.duraimurugan.Network;

import com.newsapp.duraimurugan.ModelClass.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("v2/top-headlines?country=us&apiKey=3052f1e3660241cba0ec272c73025d49")
    Call<NewsResponse> getNews();
}
