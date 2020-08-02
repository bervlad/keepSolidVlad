package com.example.app.utils.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app.R;
import com.example.app.utils.listeners.OnHistoryRecyclerItemClickListener;

import java.util.ArrayList;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter <HistoryRecyclerAdapter.ViewHolder> {
    private ArrayList<String> items;
    private Context ctx;
    private OnHistoryRecyclerItemClickListener listener;

    public HistoryRecyclerAdapter(ArrayList<String> items, Context ctx, OnHistoryRecyclerItemClickListener listener) {
        this.items = items;
        this.ctx = ctx;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HistoryRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull HistoryRecyclerAdapter.ViewHolder holder, int position) {

        holder.title.setText(items.get(position));

        holder.container.setOnClickListener(view -> {
            if (listener != null) {
                listener.onItemClick(view, holder.getAdapterPosition(), items.get(holder.getAdapterPosition()));
            }
        });

    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        View container;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.hist_volume_title);
            container = itemView;

        }
    }
}
