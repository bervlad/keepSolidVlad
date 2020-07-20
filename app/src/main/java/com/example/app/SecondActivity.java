package com.example.app;


import android.content.Intent;
import android.os.Bundle;


import com.example.app.base.BaseActivity;
import com.example.app.collections.Car;
import com.example.app.fragment.FragmentViewer;
import com.example.app.utils.Constants;


public class SecondActivity extends BaseActivity {
    private Car car;

    private FragmentViewer fragmentViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initToolBarWithNav(getString(R.string.toolbar_title_second_activity));

        if (getIntent().getExtras() != null) {
            car = getIntent().getParcelableExtra(Constants.EXTRA_CAR);
        }

        String inputText = car.toString() ;

        fragmentViewer = (FragmentViewer) getSupportFragmentManager().findFragmentById(R.id.fragment_two);

        fragmentViewer.setText(inputText);

    }




}