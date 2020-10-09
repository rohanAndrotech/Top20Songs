package com.example.top20songs;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.top20songs.database.DatabaseClient;
import com.example.top20songs.model.Entry;
import com.example.top20songs.model.Feed;
import com.example.top20songs.presenter.MainPresenter;
import com.example.top20songs.view.MainActivityView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.top20songs.database.DatabaseClient.getDataBaseInstance;
import static com.example.top20songs.service.ApiClient.getAPIClientInstance;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private static final String TAG = "MainActivity";
    private EntryAdapter mEntryAdapter;
    private ProgressDialog progressdialog;
    private MainPresenter mainPresenter;
    private DatabaseClient mDatabaseClient;

    @BindView(R.id.top_songs_RV)
    RecyclerView topSongsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        if (NetworkUtils.isNetworkAvailable(MainActivity.this)) {
            mainPresenter.getSongList();
        } else {
            mainPresenter.getSongsListFromDataBase();
        }
    }

    private void initView() {
        progressdialog = new ProgressDialog(this);
        mainPresenter = new MainPresenter(getApplicationContext(),this,
                getAPIClientInstance(),getDataBaseInstance(getApplicationContext()));
        topSongsRV.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void showList(ArrayList<Entry> entryArrayList) {
        mEntryAdapter = new EntryAdapter(MainActivity.this, entryArrayList);
        topSongsRV.setAdapter(mEntryAdapter);
    }

    @Override
    public void showProgress() {
        progressdialog.setMessage("Please Wait....");
        progressdialog.show();
    }

    @Override
    public void hideProgress() {
        progressdialog.dismiss();
    }
}