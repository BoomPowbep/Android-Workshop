package com.mickaeldebalme.android.newsletter.viewmodels;

import android.util.Log;

import com.mickaeldebalme.android.newsletter.databases.DatabaseHelper;
import com.mickaeldebalme.android.newsletter.databases.NewsDatabase;
import com.mickaeldebalme.android.newsletter.models.Article;
import com.mickaeldebalme.android.newsletter.models.ArticlesApiResponse;
import com.mickaeldebalme.android.newsletter.network.ArticlesAPI;
import com.mickaeldebalme.android.newsletter.network.NetworkHelper;
import com.mickaeldebalme.android.newsletter.utils.Constants;

import java.util.List;
import java.util.concurrent.Callable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import bolts.Continuation;
import bolts.Task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Classe ArticleViewModel
 * @author mickaeldebalme
 */
public class ArticleViewModel extends ViewModel {

    private static final String LOGTAG = "ArticleViewModel";

    private MutableLiveData<List<Article>> articlesLiveData;
    private MutableLiveData<Article> selected = new MutableLiveData<>();

    /**
     * Initie le processus ce récupération des articles.
     * @return LiveData<List<Article>>
     */
    public LiveData<List<Article>> getArticles() {

        if (articlesLiveData == null) {
            articlesLiveData = new MutableLiveData<>();

            if (NetworkHelper.getNetworkStatus()) {
                this.loadArticlesFromNetwork();
            } else {
                this.loadArticlesFromDatabase();
            }
        }

        return articlesLiveData;
    }

    /**
     * SET article sélectionné
     * @param article Article
     */
    public void setSelected(Article article) {
        selected.setValue(article);
    }

    /**
     * GET article sélectionné
     * @return LiveData<Article>
     */
    public LiveData<Article> getSelected() {
        return selected;
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
                List<Article> articles = response.body().getArticles();
                articlesLiveData.setValue(articles);
                saveArticlesInDb(articles);
            }

            @Override
            public void onFailure(Call<ArticlesApiResponse> call, Throwable t) {
                Log.d(LOGTAG, "RES ERR - " + t.getLocalizedMessage());
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
                DatabaseHelper.getDatabase().articleDao().insertAll(articles);

                return null;
            }
        }).continueWith(new Continuation<Void, Object>() {
            @Override
            public Object then(Task<Void> task) throws Exception {
                Log.d(LOGTAG, "Articles saved in database");
                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
    }

    /**
     * Charge les articles depuis la base de données
     */
    private void loadArticlesFromDatabase() {

        Task.callInBackground(new Callable<List<Article>>() {
            @Override
            public List<Article> call() {
                return DatabaseHelper.getDatabase().articleDao().getAll();
            }
        }).continueWith(new Continuation<List<Article>, Object>() {
            @Override
            public Object then(Task<List<Article>> task) {
                articlesLiveData.setValue(task.getResult());
                Log.d(LOGTAG, "Articles loaded from database");
                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
    }
}
