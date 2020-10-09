package com.example.top20songs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.top20songs.model.Entry;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String TAG = "EntryAdapter";
    private List<Entry> mEntryAdapterList;
    private Context mContext;

    public EntryAdapter(Context context, List<Entry> entryList) {
        this.mContext = context;
        this.mEntryAdapterList = entryList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.entry_adapter_item,
                viewGroup, false);
        return new EntryAdapter.EntryAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        final Entry album = mEntryAdapterList.get(position);
        final EntryAdapter.EntryAdapterViewHolder entryAdapterViewHolder =
                (EntryAdapter.EntryAdapterViewHolder) viewHolder;
        entryAdapterViewHolder.songTitle.setText(album.getTitle());
        entryAdapterViewHolder.songName.setText(album.getName());
    }

    @Override
    public int getItemCount() {
        return mEntryAdapterList == null ? 0 : mEntryAdapterList.size();
    }

    public class EntryAdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.song_title)
        TextView songTitle;
        @BindView(R.id.song_name)
        TextView songName;

        EntryAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
