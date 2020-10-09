package com.example.top20songs.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "Feed")
@Root(name = "feed", strict = false)
public class Feed implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @TypeConverters(DataConverter.class)
    @ElementList(inline = true,name = "entry")
    private List<Entry> entrys;

    public List<Entry> getEntrys() {
        return entrys;
    }

    public void setEntrys(List<Entry> entrys) {
        this.entrys = entrys;
    }

    @Override
    public String toString() {
        return "Feed: \n [Entrys: \n" + entrys +"]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
