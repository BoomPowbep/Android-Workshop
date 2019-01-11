package com.mickaeldebalme.android.newsletter.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mickaeldebalme.android.newsletter.Adapters.ArticleAdapter;
import com.mickaeldebalme.android.newsletter.Databases.NewsDatabase;
import com.mickaeldebalme.android.newsletter.Listeners.ArticleListener;
import com.mickaeldebalme.android.newsletter.Models.Article;
import com.mickaeldebalme.android.newsletter.Models.ArticlesApiResponse;
import com.mickaeldebalme.android.newsletter.Network.ArticlesAPI;
import com.mickaeldebalme.android.newsletter.R;
import com.mickaeldebalme.android.newsletter.Utils.Constants;
import com.mickaeldebalme.android.newsletter.ViewModels.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import bolts.Continuation;
import bolts.Task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticlesListFragment extends Fragment implements ArticleListener {

    private List<Article> articles = new ArrayList<>();
    private ArticleAdapter adapter;
    private ArticleViewModel model;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = ViewModelProviders.of(this).get(ArticleViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        model.getArticles().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                adapter = new ArticleAdapter(articles, ArticlesListFragment.this);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.articles_list_fragment, container, false);

        recyclerView = view.findViewById(R.id.articles_list_fragment);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        return view;
    }


    /**
     * Click sur un article
     *
     * @param article l'article cliqué
     */
    @Override
    public void onSelect(Article article) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
        browserIntent.setData(Uri.parse(article.getUrl()));
        startActivity(browserIntent);
    }

    /**
     * Click sur un bouton share
     *
     * @param article Article à partager
     */
    @Override
    public void onShare(Article article) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, article.getTitle() + " " + article.getUrl());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
