package com.mickaeldebalme.android.newsletter.Databases;

import android.content.Context;
import android.widget.Toast;

import java.util.concurrent.Callable;

import androidx.room.Room;
import bolts.Continuation;
import bolts.Task;

public class DatabaseHelper {
    private static NewsDatabase database;

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

    public static NewsDatabase getDatabase() {
        return database;
    }
}
