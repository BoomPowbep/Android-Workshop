package com.mickaeldebalme.android.newsletter.Listeners;

import com.mickaeldebalme.android.newsletter.Models.Article;

public interface ArticleListener {
    void onSelect(Article article);
    void onShare(Article article);
    void onLike(Article article);
}
