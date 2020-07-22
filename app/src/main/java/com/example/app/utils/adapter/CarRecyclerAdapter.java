package com.example.app.utils.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.R;
import com.example.app.collections.Car;
import com.example.app.fragment.FragmentChooser;
import com.example.app.utils.listeners.OnCarRecyclerItemClickListener;

import java.util.ArrayList;

public class CarRecyclerAdapter extends RecyclerView.Adapter<CarRecyclerAdapter.ViewHolder> {
    private ArrayList<Car> items;
    private FragmentChooser ctx;
    private OnCarRecyclerItemClickListener listener;

    public CarRecyclerAdapter (ArrayList<Car> items, FragmentChooser ctx) {
        this.items = items;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false);

        final ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(view, viewHolder.getAdapterPosition());
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.carName.setText(items.get(position).getName());
        holder.carColor.setText(items.get(position).getColor());

        if (position == items.size() - 1) {
            holder.divider.setVisibility(View.INVISIBLE);
        } else {
            holder.divider.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView carName;;
        TextView carColor;
        View divider;

        public ViewHolder(View itemView) {
            super(itemView);

            carName = itemView.findViewById(R.id.car_name);
            carColor = itemView.findViewById(R.id.car_color);
            divider = itemView.findViewById(R.id.divider);

        }
    }
    public void setListener(OnCarRecyclerItemClickListener listener) {
        this.listener = listener;
    }

    public void setItems(ArrayList<Car> items) {
        this.items = items;
    }
    public ArrayList<Car> getItems() {
        return items;
    }

}
