package com.mickaeldebalme.android.newsletter.Databases;

import com.mickaeldebalme.android.newsletter.DAOs.ArticleDao;
import com.mickaeldebalme.android.newsletter.Models.Article;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Article.class}, version = 1)
public abstract class NewsDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();
}
