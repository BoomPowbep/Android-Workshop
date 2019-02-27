package com.mickaeldebalme.android.newsletter.listeners;

import com.mickaeldebalme.android.newsletter.models.Article;

public interface ArticleListener {
    void onSelect(Article article);
    void onShare(Article article);
    void onLike(Article article);
}
