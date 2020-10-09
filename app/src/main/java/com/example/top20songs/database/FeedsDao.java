package com.example.top20songs.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.top20songs.model.Feed;

@Dao
public interface FeedsDao {

    @Query("SELECT * FROM Feed")
    Feed getAll();

    @Insert
    void insert(Feed entry);
}