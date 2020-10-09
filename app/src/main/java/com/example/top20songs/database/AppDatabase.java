package com.example.top20songs.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.top20songs.model.Feed;

@Database(entities = {Feed.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FeedsDao topSongsDao();
}