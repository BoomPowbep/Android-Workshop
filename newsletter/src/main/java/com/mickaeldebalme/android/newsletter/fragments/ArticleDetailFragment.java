package com.mickaeldebalme.android.newsletter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mickaeldebalme.android.newsletter.models.Article;
import com.mickaeldebalme.android.newsletter.R;
import com.mickaeldebalme.android.newsletter.viewmodels.ArticleViewModel;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class ArticleDetailFragment extends Fragment {

    private ArticleViewModel model;
    private View view;
    private TextView title;
    private TextView description;
    private ImageView image;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ViewModelProviders.of(getActivity()).get(ArticleViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.article_detail_fragment, container, false);
        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.desc);
        image = view.findViewById(R.id.image);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        model.getSelected().observe(this, new androidx.lifecycle.Observer<Article>() {
            @Override
            public void onChanged(Article article) {
                title.setText(article.getTitle());
                description.setText(article.getContent());
                Picasso.get()
                        .load(article.getUrlToImage())
                        .fit()
                        .centerCrop()
                        .into(image);
            }
        });
    }


}
