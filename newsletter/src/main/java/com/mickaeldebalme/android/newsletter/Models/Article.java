package com.mickaeldebalme.android.newsletter.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Article {

    @PrimaryKey(autoGenerate = true)
    public int id;

    private String urlToImage, title, description, url, content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article(String urlToImage, String title, String description, String url, String content) {

        this.urlToImage = urlToImage;
        this.title = title;
        this.description = description;
        this.url = url;
        this.content = content;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Article{" +
                "urlToImage='" + urlToImage + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
