package com.mickaeldebalme.android.newsletter.Network;

import com.mickaeldebalme.android.newsletter.Models.ArticlesApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticlesAPI {

    @GET("everything/")
    Call<ArticlesApiResponse> listArticles(@Query("q") String keyword, @Query("apiKey") String key);
}
