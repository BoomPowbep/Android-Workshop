package com.mickaeldebalme.android.newsletter.network;

import com.mickaeldebalme.android.newsletter.models.ArticlesApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface ArticlesAPI
 * @author mickaeldebalme
 */
public interface ArticlesAPI {

    @GET("everything/")
    Call<ArticlesApiResponse> listArticles(@Query("q") String keyword, @Query("apiKey") String key);
}
