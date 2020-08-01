package com.example.app.app;

import android.app.Application;
import android.util.Log;

import androidx.room.Room;

import com.example.app.database.AppDatabase;

public class App extends Application {

    private AppDatabase appDatabase;

    @Override
    public void onCreate() {

        super.onCreate();
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "volumeitems")
                .allowMainThreadQueries()
                .build();
        appDatabase.repoItemDao().deleteAll();
    }

    public AppDatabase getDatabase() {
        return appDatabase;
    }

}
