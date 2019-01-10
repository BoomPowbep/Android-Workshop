package com.mickaeldebalme.android.newsletter.Models;

public class Article {

    private String urlToImage, title, description;

    public Article(String urlToImage, String title, String description) {
        this.urlToImage = urlToImage;
        this.title = title;
        this.description = description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Article{" +
                "urlToImage='" + urlToImage + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
