package com.example.app.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.app.model.VolumeModelItem;

@Database(entities = {VolumeModelItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract VolumeItemDao repoItemDao();

}
