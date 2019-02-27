package com.mickaeldebalme.android.newsletter.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mickaeldebalme.android.newsletter.adapters.ArticleAdapter;
import com.mickaeldebalme.android.newsletter.listeners.ArticleListener;
import com.mickaeldebalme.android.newsletter.models.Article;
import com.mickaeldebalme.android.newsletter.R;
import com.mickaeldebalme.android.newsletter.viewmodels.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ArticlesListFragment extends Fragment implements ArticleListener {

    private List<Article> articles = new ArrayList<>();
    private ArticleAdapter adapter;
    private ArticleViewModel model;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = ViewModelProviders.of(getActivity()).get(ArticleViewModel.class);
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
//        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
//        browserIntent.setData(Uri.parse(article.getUrl()));
//        startActivity(browserIntent);
        model.setSelected(article);
        showDetail();
    }

    private void showDetail() {
        ArticleDetailFragment frag = new ArticleDetailFragment();
        FragmentTransaction transac = getActivity().getSupportFragmentManager().beginTransaction();
        transac.replace(R.id.fragment_container, frag);
        transac.addToBackStack(null);
        transac.commit();
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

    /**
     * Click sur le bouton like
     * @param article Article concerné par le like
     */
    @Override
    public void onLike(Article article) {

        ImageView likeImg = recyclerView.findViewById(R.id.like_btn);

        if(article.isLiked()) {
            article.setLiked(false);
            likeImg.setImageResource(R.drawable.empty_h);
        }
        else {
            article.setLiked(true);
            likeImg.setImageResource(R.drawable.full_h);
        }

    }
}
