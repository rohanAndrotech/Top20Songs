package com.example.top20songs.presenter;

import android.content.Context;

import com.example.top20songs.database.DatabaseClient;
import com.example.top20songs.model.Entry;
import com.example.top20songs.model.Feed;
import com.example.top20songs.service.ApiClient;
import com.example.top20songs.view.MainActivityView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MainPresenterTest {
    private MainPresenter mMainPresenter;
    private MainActivityView mMainActivityView;
    private DatabaseClient mDatabaseClient;
    private ApiClient mApiClient;
    private Context mContext;

    @Before
    public void setUp() {
        mMainActivityView = mock(MainActivityView.class);
        mApiClient = mock(ApiClient.class);
        mDatabaseClient = mock(DatabaseClient.class);
        mContext = mock(Context.class);
        mMainPresenter = new MainPresenter(mContext, mMainActivityView, mApiClient, mDatabaseClient);
    }

    /**
     * On Success of API test.
     */
    @Test
    public void testOnSuccess() {
        Feed feed = new Feed();
        Entry entry = new Entry();
        ArrayList<Entry>arrayList = new ArrayList<>();
        entry.setName("Demo");
        entry.setName("DemoTile");
        arrayList.add(entry);
        feed.setEntrys(arrayList);
        mMainPresenter = new MainPresenter(mContext, mMainActivityView, mApiClient, mDatabaseClient);
        mMainPresenter.onSuccess(feed);
        verify(mMainActivityView, times(1)).hideProgress();
    }

    /**
     * On Error of API test.
     */
    @Test
    public void testOnError() {
        mMainPresenter.onError();
        verify(mMainActivityView, times(1)).hideProgress();
    }

}