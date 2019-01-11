package com.mickaeldebalme.android.newsletter.ViewModels;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.mickaeldebalme.android.newsletter.Databases.NewsDatabase;
import com.mickaeldebalme.android.newsletter.Models.Article;
import com.mickaeldebalme.android.newsletter.Models.ArticlesApiResponse;
import com.mickaeldebalme.android.newsletter.Network.ArticlesAPI;
import com.mickaeldebalme.android.newsletter.Utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;
import bolts.Continuation;
import bolts.Task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleViewModel extends ViewModel {

    private MutableLiveData<List<Article>> articlesLiveData;
    private List<Article> articles = new ArrayList<>();

    public LiveData<List<Article>> getArticles() {

        if(articlesLiveData == null) {
            articlesLiveData = new MutableLiveData<>();
        }

//        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        boolean isConnected = activeNetwork != null && activeNetwork.isConnected();
//        if (isConnected) {
//            this.loadArticlesFromNetwork();
//            Toast.makeText(getContext(), "Connected to internet", Toast.LENGTH_SHORT).show();
//        } else {
//            this.loadArticlesFromDatabase();
//            Toast.makeText(getContext(), "No connection", Toast.LENGTH_SHORT).show();
//        }

        return articlesLiveData;
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
                articlesLiveData.setValue(articles);
                articles = response.body().getArticles();

//                saveArticlesInDb(articles);


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
//    private void saveArticlesInDb(final List<Article> articles) {
//
//        Task.callInBackground(new Callable<Void>() {
//            public Void call() {
//                NewsDatabase db = Room.databaseBuilder(getContext(), NewsDatabase.class, "news-db").build();
//                for (int i = 0; i < articles.size(); i++) {
//                    db.articleDao().insertAll(articles.get(i));
//                }
//                return null;
//            }
//        }).continueWith(new Continuation<Void, Object>() {
//            @Override
//            public Object then(Task<Void> task) throws Exception {
//
//                Toast.makeText(getContext(), "Articles insterted in DB", Toast.LENGTH_SHORT).show();
//                return null;
//            }
//        }, Task.UI_THREAD_EXECUTOR);
//    }

    /**
     * Charge les articles depuis la base de données
     */
//    private void loadArticlesFromDatabase() {
//
//        Task.callInBackground(new Callable<Void>() {
//            @Override
//            public Void call() throws Exception {
//                NewsDatabase db = Room.databaseBuilder(getContext(), NewsDatabase.class, "news-db").build();
//                articles = db.articleDao().getAll();
//                return null;
//            }
//        }).continueWith(new Continuation<Void, Object>() {
//            @Override
//            public Object then(Task<Void> task) throws Exception {
//
//                Toast.makeText(getContext(), "Articles loaded from DB", Toast.LENGTH_SHORT).show();
//                return null;
//            }
//        }, Task.UI_THREAD_EXECUTOR);
//    }
}
