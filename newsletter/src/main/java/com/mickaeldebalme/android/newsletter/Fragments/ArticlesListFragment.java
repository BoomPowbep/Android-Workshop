package com.mickaeldebalme.android.newsletter.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mickaeldebalme.android.newsletter.Adapters.ArticleAdapter;
import com.mickaeldebalme.android.newsletter.Listeners.ArticleListener;
import com.mickaeldebalme.android.newsletter.Models.Article;
import com.mickaeldebalme.android.newsletter.Models.ArticlesApiResponse;
import com.mickaeldebalme.android.newsletter.Network.ArticlesAPI;
import com.mickaeldebalme.android.newsletter.R;
import com.mickaeldebalme.android.newsletter.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticlesListFragment extends Fragment implements ArticleListener {

    private List<Article> articles = new ArrayList<>();
    ArticleAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.loadArticles();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.articles_list_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.articles_list_fragment);
        adapter = new ArticleAdapter(articles, this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return view;
    }


    void loadArticles() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ArticlesAPI service = retrofit.create(ArticlesAPI.class);


        Call<ArticlesApiResponse> response = service.listArticles("bitcoin", Constants.API_KEY);
        response.enqueue(new Callback<ArticlesApiResponse>() {
            @Override
            public void onResponse(Call<ArticlesApiResponse> call, Response<ArticlesApiResponse> response) {
                articles = response.body().getArticles();
                adapter.setArticles(articles);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArticlesApiResponse> call, Throwable t) {
                System.out.println("RES ERR - " + t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onSelect(Article article) {

    }

    @Override
    public void onShare(Article article) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, article.getTitle());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
