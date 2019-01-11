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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnected();
        if (isConnected) {
            this.loadArticlesFromNetwork();
            Toast.makeText(getContext(), "Connected to internet", Toast.LENGTH_SHORT).show();
        } else {
            this.loadArticlesFromDatabase();
            Toast.makeText(getContext(), "No connection", Toast.LENGTH_SHORT).show();
        }
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


    /**
     * Charge les articles depuis l'API
     */
    private void loadArticlesFromNetwork() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ArticlesAPI service = retrofit.create(ArticlesAPI.class);


        Call<ArticlesApiResponse> response = service.listArticles("cryptocurrency", Constants.API_KEY);
        response.enqueue(new Callback<ArticlesApiResponse>() {
            private NewsDatabase db;

            @Override
            public void onResponse(Call<ArticlesApiResponse> call, Response<ArticlesApiResponse> response) {
                articles = response.body().getArticles();

                saveArticlesInDb(articles);

                adapter.setArticles(articles);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArticlesApiResponse> call, Throwable t) {
                System.out.println("RES ERR - " + t.getLocalizedMessage());
            }
        });
    }

    /**
     * Sauvegarde les articles dans la db
     *
     * @param articles Liste des articles à sauver dans la db
     */
    private void saveArticlesInDb(final List<Article> articles) {

        Task.callInBackground(new Callable<Void>() {
            public Void call() {
                NewsDatabase db = Room.databaseBuilder(getContext(), NewsDatabase.class, "news-db").build();
                for (int i = 0; i < articles.size(); i++) {
                    db.articleDao().insertAll(articles.get(i));
                }
                return null;
            }
        }).continueWith(new Continuation<Void, Object>() {
            @Override
            public Object then(Task<Void> task) throws Exception {

                Toast.makeText(getContext(), "Articles insterted in DB", Toast.LENGTH_SHORT).show();
                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
    }

    /**
     * Charge les articles depuis la base de données
     */
    private void loadArticlesFromDatabase() {

        Task.callInBackground(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                NewsDatabase db = Room.databaseBuilder(getContext(), NewsDatabase.class, "news-db").build();
                articles = db.articleDao().getAll();
                return null;
            }
        }).continueWith(new Continuation<Void, Object>() {
            @Override
            public Object then(Task<Void> task) throws Exception {

                Toast.makeText(getContext(), "Articles loaded from DB", Toast.LENGTH_SHORT).show();
                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);

        //List<Article> articles = this.db.articleDao().getAll();
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
