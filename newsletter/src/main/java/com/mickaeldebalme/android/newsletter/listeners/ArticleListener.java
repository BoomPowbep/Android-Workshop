package com.mickaeldebalme.android.newsletter.listeners;

import com.mickaeldebalme.android.newsletter.models.Article;

/**
 * Interface ArticleListener
 * @author mickaeldebalme
 */
public interface ArticleListener {
    void onSelect(Article article);
    void onShare(Article article);
    void onLike(Article article);
}
