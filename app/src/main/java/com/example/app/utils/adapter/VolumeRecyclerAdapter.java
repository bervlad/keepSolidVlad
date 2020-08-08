package com.example.app.utils.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app.R;
import com.example.app.fragment.screens.chooser.FragmentChooser;
import com.example.app.model.VolumeModelItem;
import com.example.app.utils.listeners.OnVolumeItemRecyclerItemClickListener;

import java.util.ArrayList;

public class VolumeRecyclerAdapter extends RecyclerView.Adapter<VolumeRecyclerAdapter.ViewHolder> {
    private ArrayList<VolumeModelItem> items;
    private FragmentChooser ctx;
    private OnVolumeItemRecyclerItemClickListener listener;

    public VolumeRecyclerAdapter(ArrayList<VolumeModelItem> items, FragmentChooser ctx) {
        this.items = items;
        this.ctx = ctx;
    }

    public VolumeRecyclerAdapter(ArrayList<VolumeModelItem> items, FragmentChooser ctx, OnVolumeItemRecyclerItemClickListener listener) {
        this.items = items;
        this.ctx = ctx;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VolumeRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        String auth = items.get(position).getAuthors();

        if(TextUtils.isEmpty(auth)) {
            holder.volumeAuthors.setVisibility(View.GONE);
        } else {
            holder.volumeAuthors.setText(auth);
            holder.volumeAuthors.setVisibility(View.VISIBLE);
        }

        holder.volumeTitle.setText(items.get(position).getTitle());

        if (items.get(position).getVolumeInfo().getImageLinks()!=null) {
            Glide.with(holder.logo).load(items.get(position).getVolumeInfo().getImageLinks().getSmallThumbnail()).placeholder(R.drawable.ic_book_open_page_variant).into(holder.logo);
        } else {
            Glide.with(holder.logo).load(R.drawable.ic_book_open_page_variant).placeholder(R.drawable.ic_book_open_page_variant).into(holder.logo);
        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(view, holder.getAdapterPosition());
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView volumeTitle;
        TextView volumeAuthors;
        AppCompatImageView logo;
        View container;


        public ViewHolder(View itemView) {
            super(itemView);

            volumeAuthors = itemView.findViewById(R.id.tv_volume_authors);
            volumeTitle= itemView.findViewById(R.id.tv_volume_title);
            logo = itemView.findViewById(R.id.iv_logo);
            container = itemView;
        }
    }
    public void setListener(OnVolumeItemRecyclerItemClickListener listener) {
        this.listener = listener;
    }

    public void setItems(ArrayList<VolumeModelItem> items) {
        this.items = items;
    }
    public ArrayList<VolumeModelItem> getItems() {
        return items;
    }

}
