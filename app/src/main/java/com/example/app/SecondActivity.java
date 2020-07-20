package com.example.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.base.BaseActivity;
import com.example.app.collections.Car;

import com.example.app.fragment.FragmentChooser;
import com.example.app.fragment.FragmentViewer;
import com.example.app.utils.Constants;

public class SecondActivity extends BaseActivity {
    private Car car;
    private FragmentChooser fragmentChooser;
    private FragmentViewer fragmentViewer;
    boolean inLandscapeMode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

}