package com.newsapp.duraimurugan.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.newsapp.duraimurugan.AdapterClass.NewsAdapter;
import com.newsapp.duraimurugan.AdapterClass.NewsClick;
import com.newsapp.duraimurugan.ModelClass.NewsResponse;
import com.newsapp.duraimurugan.Network.API;
import com.newsapp.duraimurugan.Network.Connection;
import com.newsapp.duraimurugan.R;
import com.newsapp.duraimurugan.Utility.MyProgressDialog;
import com.newsapp.duraimurugan.Utility.Utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsActivity extends AppCompatActivity implements NewsClick {
    Context mContext;
    Connection connection;
    API api;
    MyProgressDialog myProgressDialog;
    NewsAdapter newsAdapter;
    ArrayList<NewsResponse.ArticleResponse> articleResponses = new ArrayList<>();

    RecyclerView mRecyclerViewNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = NewsActivity.this;
        myProgressDialog = new MyProgressDialog(mContext, "Please wait..");
        connection = new Connection();
        api = connection.createRestAdaptor(mContext);
        mRecyclerViewNews = findViewById(R.id.list_news);
        mRecyclerViewNews.setLayoutManager(new LinearLayoutManager(mContext));
        newsAdapter = new NewsAdapter(mContext, articleResponses);
        mRecyclerViewNews.setAdapter(newsAdapter);
        newsAdapter.setNewsClick(this);

        myProgressDialog.show();
        getNews();


    }

    private void getNews() {
      connection.createRestAdaptor(mContext).getNews().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                dismissProgress();

      try {
                        if (response != null) {
                            if (response.body() != null) {
                                articleResponses = response.body().getArticles();
                                newsAdapter.updateList(articleResponses, mContext);
                            }
                        }
                    } catch (Exception e) {

                    }



                if (response.raw().cacheResponse() != null) {
                    Log.e("Network", "response came from cache");
                    try {
                        articleResponses = response.body().getArticles();
                        newsAdapter.updateList(articleResponses, mContext);
                    }catch (Exception e){

                    }
                }


            }
            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.i("TAG", "onResponse: " + t.getMessage());
                dismissProgress();
            }
        });
    }

    private void dismissProgress() {
        try {
            if (null != myProgressDialog && myProgressDialog.isShowing())
                myProgressDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getposition(int position) {
        if (articleResponses.size() > 0) {
            try {
                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                intent.putExtra("url", articleResponses.get(position).getUrl());
                startActivity(intent);
            } catch (Exception e) {

            }
        }
    }







}