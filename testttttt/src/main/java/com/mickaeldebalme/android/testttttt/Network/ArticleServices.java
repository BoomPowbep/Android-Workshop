package com.mickaeldebalme.android.testttttt.Network;

import com.mickaeldebalme.android.testttttt.Models.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ArticleServices {

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
