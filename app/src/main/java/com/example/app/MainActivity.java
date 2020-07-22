package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.base.BaseActivity;
import com.example.app.collections.Car;

import java.util.ArrayList;

import com.example.app.fragment.FragmentChooser;
import com.example.app.fragment.FragmentViewer;
import com.example.app.utils.Constants;
import com.example.app.utils.listeners.ObjectSelectListener;

public class MainActivity extends BaseActivity {


    private FragmentChooser fragmentChooser;
    private FragmentViewer fragmentViewer;
    RecyclerView recyclerView;

    boolean inLandscapeMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initToolBar (getString(R.string.toolbar_title_main_activity));

        inLandscapeMode = findViewById(R.id.fragment_two) != null;

        fragmentChooser = (FragmentChooser) getSupportFragmentManager().findFragmentById(R.id.fragment_one);
        if (inLandscapeMode) {
            fragmentViewer = (FragmentViewer) getSupportFragmentManager().findFragmentById(R.id.fragment_two);
        }

//        ObjectSelectListener objectSelectListener = new ObjectSelectListener() {
//
//            @Override
//            public void btn1Selected() {
//                displaySelected(0);
//            }
//
//            @Override
//            public void btn2Selected() {
//                displaySelected(1);
//            }
//
//            @Override
//            public void btn3Selected() {
//                displaySelected(2);
//            }
//
//            @Override
//            public void btn4Selected() {
//                displaySelected(3);
//            }
//
//            @Override
//            public void btn5Selected() {
//                displaySelected(4);
//            }
//
//        };

//
        ObjectSelectListener objectSelectListener = new ObjectSelectListener() {
            @Override
            public void selected(int num) {
                displaySelected (num);
            }

            public void buttonSelected () {
                Intent explicitIntent = new Intent(MainActivity.this, InputActivity.class);
                startActivityForResult(explicitIntent, Constants.NAME_REQUEST_CODE);
            }
        };
        fragmentChooser.setObjectSelectListener(objectSelectListener);

        
    }

    public void displaySelected (int num) {
        ArrayList<Car> models = fragmentChooser.getModels();

        if (inLandscapeMode) {
            fragmentViewer.setText(models.get(num).toString());
        } else {

            Intent explicitIntent = new Intent(MainActivity.this, SecondActivity.class);
            explicitIntent.putExtra(Constants.EXTRA_CAR, models.get(num));
            startActivity(explicitIntent);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.NAME_REQUEST_CODE) {
            if (data != null) {
                if (data.getExtras() != null) {
                    showNameToast("Hello");
                    Car newCar = data.getParcelableExtra(Constants.EXTRA_CAR);
                     //= getIntent().getParcelableExtra(Constants.EXTRA_CAR);
                    // Car newCar = new Car ("Forddd", "ddasd", 123,12);
                    fragmentChooser.addModel(newCar);
                }
            }
        }
    }

    private void showNameToast(String name) {
        Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();
    }


}