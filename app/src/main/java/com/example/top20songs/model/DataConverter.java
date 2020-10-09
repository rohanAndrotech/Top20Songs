package com.example.top20songs.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DataConverter {

    @TypeConverter
    public String fromEntryList(List<Entry> entryList) {
        if (entryList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Entry>>() {}.getType();
        String json = gson.toJson(entryList, type);
        return json;
    }

    @TypeConverter
    public List<Entry> toEntryList(String entryString) {
        if (entryString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Entry>>() {}.getType();
        List<Entry> entryList = gson.fromJson(entryString, type);
        return entryList;
    }
 }