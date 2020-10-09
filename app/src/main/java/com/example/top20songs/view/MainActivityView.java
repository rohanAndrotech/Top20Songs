package com.example.top20songs.view;

import com.example.top20songs.model.Entry;

import java.util.ArrayList;

public interface MainActivityView extends BaseView {
    void showList( ArrayList<Entry> entryArrayList);
}