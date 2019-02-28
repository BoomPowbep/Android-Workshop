package com.mickaeldebalme.android.newsletter.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Classe Article
 * @author mickaeldebalme
 */
@Entity
public class Article {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String urlToImage, title, description, url, content;

    private boolean liked;

    /**
     * constructor
     * @param id int
     * @param urlToImage string
     * @param title string
     * @param description string
     * @param url string
     * @param content string
     * @param liked boolean
     */
    public Article(int id, String urlToImage, String title, String description, String url, String content, boolean liked) {
        this.id = id;
        this.urlToImage = urlToImage;
        this.title = title;
        this.description = description;
        this.url = url;
        this.content = content;
        this.liked = liked;
    }

    /**
     * GET id
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * SET id
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * GET image url
     * @return string
     */
    public String getUrlToImage() {
        return urlToImage;
    }

    /**
     * SET image url
     * @param urlToImage string
     */
    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    /**
     * GET title
     * @return string
     */
    public String getTitle() {
        return title;
    }

    /**
     * SET title
     * @param title string
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * GET description
     * @return string
     */
    public String getDescription() {
        return description;
    }

    /**
     * SET description
     * @param description string
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * GET url
     * @return string
     */
    public String getUrl() {
        return url;
    }

    /**
     * SET url
     * @param url string
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * GET content
     * @return string
     */
    public String getContent() {
        return content;
    }

    /**
     * permet de vérifier si un article est liké
     * @return boolean
     */
    public boolean isLiked() {
        return liked;
    }

    /**
     * Définit le statut de like d'un article
     * @param liked boolean
     */
    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    /**
     * Convertit l'objet en string.
     * @return string
     */
    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", urlToImage='" + urlToImage + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                ", liked=" + liked +
                '}';
    }
}
