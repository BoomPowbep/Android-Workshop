package com.mickaeldebalme.android.newsletter.models;

import java.util.List;

/**
 * Classe ArticlesApiResponse
 * @author mickaeldebalme
 */
public class ArticlesApiResponse {

    private String status;
    private int totalResults;
    private List<Article> articles;

    /**
     * Constructor
     * @param status String
     * @param totalResults int
     * @param articles List<Article>
     */
    public ArticlesApiResponse(String status, int totalResults, List<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * GET articles
     * @return List<Article>
     */
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
