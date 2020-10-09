package com.example.top20songs.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.TypeConverter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Entity
@Root(name = "entry", strict = false)
public class Entry implements Serializable {

    @ColumnInfo(name = "title")
    @Element(name="title")
    private String title;

    @ColumnInfo(name = "name")
    @Element(name="name")
    private String name;

    public Entry() {
    }

    public Entry(String title, String name) {
        this.title = title;
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\n\nEntry{" +
                "title='" + title + '\'' +
                ", name='" + name + '\'' +
                '}'+ "\n" +
                "---------------\n";
    }

}
