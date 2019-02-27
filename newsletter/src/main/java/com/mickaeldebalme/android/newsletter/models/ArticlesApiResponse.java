package com.mickaeldebalme.android.newsletter.models;

import java.util.List;

/**
 * Classe ArticlesApiResponse
 * @author mickaeldebalme
 */
public class ArticlesApiResponse {

    private List<Article> articles;

    /**
     * Constructor
     * @param status String
     * @param totalResults int
     * @param articles List<Article>
     */
    public ArticlesApiResponse(String status, int totalResults, List<Article> articles) {
        this.articles = articles;
    }

    /**
     * GET articles
     * @return List<Article>
     */
    public List<Article> getArticles() {
        return articles;
    }
}
