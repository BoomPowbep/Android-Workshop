package com.mickaeldebalme.android.newsletter.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mickaeldebalme.android.newsletter.Fragments.ArticlesListFragment;
import com.mickaeldebalme.android.newsletter.Listeners.ArticleListener;
import com.mickaeldebalme.android.newsletter.Models.Article;
import com.mickaeldebalme.android.newsletter.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {

    private List<Article> articles;
    private ArticleListener listener;


    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public ArticleAdapter(List<Article> articles, ArticlesListFragment listener) {
        this.articles = articles;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.single_article_mini, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindItem(articles.get(position));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle, mDescription;
        ImageView mImage, mShare, mLike;
        View view;

        MyViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            mTitle = itemView.findViewById(R.id.article_title);
            mDescription = itemView.findViewById(R.id.article_description);
            mImage = itemView.findViewById(R.id.article_image);
            mShare = itemView.findViewById(R.id.share_btn);
            mLike = itemView.findViewById(R.id.like_btn);
        }

        private void bindItem(final Article article) {

            mShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onShare(article);
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelect(article);
                }
            });

            mLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onLike(article);
                }
            });

            mTitle.setText(article.getTitle());
            mDescription.setText(article.getDescription());
            Picasso.get()
                    .load(article.getUrlToImage())
                    .fit()
                    .centerCrop()
                    .into(mImage);
        }
    }
}
