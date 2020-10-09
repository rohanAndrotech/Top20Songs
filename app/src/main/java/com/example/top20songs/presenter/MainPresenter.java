package com.example.top20songs.presenter;


import android.content.Context;

import com.example.top20songs.database.DatabaseClient;
import com.example.top20songs.model.Entry;
import com.example.top20songs.model.Feed;
import com.example.top20songs.service.ApiClient;
import com.example.top20songs.view.MainActivityView;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter {
    private ApiClient apiClient;
    private MainActivityView mainActivityView;
    private Context mContext;
    private DatabaseClient mDatabaseClient;

    public MainPresenter(Context context, MainActivityView mainActivityView, ApiClient apiClient,
                         DatabaseClient databaseClient) {
        this.mainActivityView = mainActivityView;
        if (this.apiClient == null) this.apiClient = apiClient;
        this.mContext = context;
        this.mDatabaseClient = databaseClient;
    }

    /**
     * Get Songs List From Server
     */
    public void getSongList() {
        mainActivityView.showProgress();
        apiClient.getTopSongsArtist(new ApiClient.ServerResponseListener() {
            @Override
            public void onSuccessResponseReceived(Feed response) {
                onSuccess(response);
            }

            @Override
            public void onErrorResponseReceived() {
                onError();
            }
        });
    }


    /**
     * Get Songs List From DataBase
     */
    public void getSongsListFromDataBase() {
        mainActivityView.showProgress();
        Feed feedData = mDatabaseClient.getAppDatabase().topSongsDao().getAll();
        if (feedData != null) {
            List<Entry> entrys = feedData.getEntrys();
            ArrayList<Entry> entryArrayList = new ArrayList<Entry>();
            for (int i = 0; i < entrys.size(); i++) {
                entryArrayList.add(new Entry(
                        entrys.get(i).getTitle(),
                        entrys.get(i).getName()));
            }
            mainActivityView.showList(entryArrayList);
        }
        mainActivityView.hideProgress();
    }

    /**
     * OnSuccess Response
     *
     * @param response Response object
     */
    protected void onSuccess(Feed response) {
        List<Entry> entrys = response.getEntrys();
        if (entrys != null) {
            ArrayList<Entry> entryArrayList = new ArrayList<Entry>();
            for (int i = 0; i < entrys.size(); i++) {
                entryArrayList.add(new Entry(
                        entrys.get(i).getTitle(),
                        entrys.get(i).getName()));
            }
            mainActivityView.showList(entryArrayList);
            mDatabaseClient.getAppDatabase().topSongsDao().insert(response);
        }
        mainActivityView.hideProgress();
    }

    /**
     * Error Code Data.
     */
    protected void onError() {
        mainActivityView.hideProgress();
    }
}
