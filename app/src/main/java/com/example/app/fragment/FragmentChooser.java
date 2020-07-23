package com.example.app.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.InputActivity;
import com.example.app.MainActivity;
import com.example.app.R;
import com.example.app.collections.Car;

import java.util.ArrayList;

import com.example.app.utils.adapter.CarRecyclerAdapter;
import com.example.app.utils.listeners.ObjectSelectListener;
import com.example.app.utils.listeners.OnCarRecyclerItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.app.utils.Constants.EXTRA_CAR;
import static com.example.app.utils.Constants.EXTRA_CARS;


public class FragmentChooser extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    RecyclerView recyclerView;
    private ArrayList <Car> models;
    CarRecyclerAdapter carRecyclerAdapter;
    private ObjectSelectListener objectSelectListener;


    public FragmentChooser() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_chooser, container, false);
        recyclerView=v.findViewById(R.id.rv_recycler);

        generateModels ();
        adapterInit();

        FloatingActionButton addBtn = v.findViewById(R.id.fab_add);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (models != null && carRecyclerAdapter != null) {
                    objectSelectListener.buttonSelected();
                }
            }
        });
        return v;
    }

    public void generateModels () {

        models = new ArrayList<>();

        Car Opel = new Car ("Opel", "Red", 200, 100);
        Car Ford = new Car ("Ford", "Yellow", 250, 120);
        Car Smart = new Car ("Smart", "Blue", 250, 120);
        Car Toyota = new Car ("Toyota", "Blue", 250, 120);
        Car Lexus = new Car ("Lexus", "Gold", 200, 100);

        models.add(Opel);
        models.add(Ford);
        models.add(Smart);
        models.add(Toyota);
        models.add(Lexus);

    }

    private void adapterInit() {
        carRecyclerAdapter=new CarRecyclerAdapter(models, this);

        carRecyclerAdapter.setListener(new OnCarRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                objectSelectListener.selected(position);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(carRecyclerAdapter);
    }

    public ArrayList<Car> getModels () {
        return models;
    }

    public void addModel (Car newCar) {
        models.add (newCar);
        carRecyclerAdapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(recyclerView.getBottom());
    }


    public void setObjectSelectListener(ObjectSelectListener listener) {
        this.objectSelectListener = listener;
    }



}