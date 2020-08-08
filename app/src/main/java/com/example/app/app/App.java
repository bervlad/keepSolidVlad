package com.example.app.app;

import android.app.Application;
import android.util.Log;

import androidx.room.Room;

import com.example.app.MainActivity;
import com.example.app.database.AppDatabase;
import com.example.app.utils.listeners.ApplicationManager;

public class App extends Application {

    private AppDatabase appDatabase;
    private  ApplicationManager applicationManager;


    public void setApplicationManager(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "volumeitems")
                .allowMainThreadQueries()
                .build();
        appDatabase.repoItemDao().deleteAll();
      //  ApplicationManager.cacheLoadedItems(null);
//        ApplicationManager applicationManager = new ApplicationManager(this);
        ApplicationManager manager = new ApplicationManager(this);
        manager.cacheLoadedItems(null);
    }

    public AppDatabase getDatabase() {
        return appDatabase;
    }

    public ApplicationManager getApplicationManager() {
        return applicationManager;
    }
}
