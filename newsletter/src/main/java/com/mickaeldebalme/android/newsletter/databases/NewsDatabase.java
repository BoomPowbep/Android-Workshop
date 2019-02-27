package com.mickaeldebalme.android.newsletter.databases;

import com.mickaeldebalme.android.newsletter.daos.ArticleDao;
import com.mickaeldebalme.android.newsletter.models.Article;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Article.class}, version = 1)
public abstract class NewsDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();
}
