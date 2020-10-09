package com.example.top20songs.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {
    private Context mCtx;
    private static DatabaseClient mInstance;
    //our app database object
    private AppDatabase appDatabase;
 
    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;
        //creating the app database with Room database builder
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "TopSongsList").
                allowMainThreadQueries().build();
    }
 
    public static synchronized DatabaseClient getDataBaseInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }
 
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}