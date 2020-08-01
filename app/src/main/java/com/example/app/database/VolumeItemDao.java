package com.example.app.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.app.model.VolumeModelItem;

import java.util.List;

@Dao
public interface VolumeItemDao {

    @Query("SELECT * FROM volumeItemsTable")
    LiveData<List<VolumeModelItem>> getAll();

    @Query("SELECT * FROM volumeItemsTable WHERE id = :id")
    LiveData<VolumeModelItem> getById(long id);

    @Query("SELECT * FROM volumeItemsTable WHERE bookTitle = :title")
    LiveData<VolumeModelItem> getByTitle(String title);

    @Insert
    void insert(VolumeModelItem item);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<VolumeModelItem> items);

    @Update
    void update(VolumeModelItem item);

    @Delete
    void delete(VolumeModelItem item);

    @Query("DELETE FROM volumeItemsTable")
    void deleteAll();

}
