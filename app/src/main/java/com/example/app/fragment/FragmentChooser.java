package com.example.app.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.R;
import com.example.app.collections.Car;

import java.util.ArrayList;

import com.example.app.utils.listeners.ObjectSelectListener;


public class FragmentChooser extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private ArrayList <Car> models;

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
        AppCompatButton btn1 = v.findViewById(R.id.btn_1);
        AppCompatButton btn2 = v.findViewById(R.id.btn_2);
        AppCompatButton btn3 = v.findViewById(R.id.btn_3);
        AppCompatButton btn4 = v.findViewById(R.id.btn_4);
        AppCompatButton btn5 = v.findViewById(R.id.btn_5);
        ArrayList <Car> models = generateModels ();

        btn1.setText(models.get(0).getName());
        btn2.setText(models.get(1).getName());
        btn3.setText(models.get(2).getName());
        btn4.setText(models.get(3).getName());
        btn5.setText(models.get(4).getName());

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (objectSelectListener != null) {
                    objectSelectListener.btn1Selected();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (objectSelectListener != null) {
                    objectSelectListener.btn2Selected();
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (objectSelectListener != null) {
                    objectSelectListener.btn3Selected();
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (objectSelectListener != null) {
                    objectSelectListener.btn4Selected();
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (objectSelectListener != null) {
                    objectSelectListener.btn5Selected();
                }
            }
        });

        return v;
    }

    public ArrayList<Car> generateModels () {

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

        return models;
    }

    public ArrayList<Car> getModels () {
        return models;
    }

    public void setObjectSelectListener(ObjectSelectListener listener) {
        this.objectSelectListener = listener;
    }



}