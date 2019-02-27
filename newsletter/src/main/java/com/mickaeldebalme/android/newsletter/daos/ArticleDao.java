package com.mickaeldebalme.android.newsletter.daos;

import com.mickaeldebalme.android.newsletter.models.Article;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * Interface ArticleDAO
 * @author mickaeldebalme
 */
@Dao
public interface ArticleDao {

    @Query("SELECT * FROM article")
    List<Article> getAll();

    @Query("SELECT * FROM article WHERE id = :id")
    Article findOne(int id);

    @Query("SELECT * FROM article WHERE id IN (:ids)")
    Article findByIds(int[] ids);

    @Query("SELECT * FROM article WHERE title LIKE :title")
    Article findByTitle(String title);

    @Insert
    void insertAll(List<Article> articles);

    @Delete
    void delete(Article article);
}
