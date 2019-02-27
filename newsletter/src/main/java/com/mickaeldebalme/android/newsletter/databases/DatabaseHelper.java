package com.mickaeldebalme.android.newsletter.databases;

import android.content.Context;

import java.util.concurrent.Callable;

import androidx.room.Room;
import bolts.Continuation;
import bolts.Task;

public class DatabaseHelper {

    /**
     * Base de données
     */
    private static NewsDatabase database;

    /**
     * Initialisation
     * @param context contexte
     */
    public static void init(final Context context) {
        Task.callInBackground(new Callable<Void>() {
            public Void call() {
                database = Room.databaseBuilder(context, NewsDatabase.class, "news-db").build();
                return null;
            }
        }).continueWith(new Continuation<Void, Object>() {
            @Override
            public Object then(Task<Void> task) throws Exception {
                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
    }

    /**
     * Récupère la base de données
     * @return base de données
     */
    public static NewsDatabase getDatabase() {
        return database;
    }
}
