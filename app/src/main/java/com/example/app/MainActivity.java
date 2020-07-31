package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.base.BaseActivity;
import com.example.app.collections.Car;

import java.util.ArrayList;

import com.example.app.fragment.FragmentChooser;
import com.example.app.fragment.FragmentViewer;
import com.example.app.model.ParcableModel;
import com.example.app.model.VolumeModelItem;
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
            findViewById(R.id.btn_link).setVisibility(View.GONE);
            fragmentViewer = (FragmentViewer) getSupportFragmentManager().findFragmentById(R.id.fragment_two);
        }

        ObjectSelectListener objectSelectListener = new ObjectSelectListener() {
            @Override
            public void selected(int num) {
                displaySelected (num);
            }

            public void buttonSelected () {
                if (inLandscapeMode) {
                    fragmentViewer = (FragmentViewer) getSupportFragmentManager().findFragmentById(R.id.fragment_two);
                fragmentViewer.clearScreen();}
            }
        };
        fragmentChooser.setObjectSelectListener(objectSelectListener);
    }

    public void displaySelected (int num) {
        ArrayList<VolumeModelItem> models = fragmentChooser.getModels();

        if (inLandscapeMode) {
            fragmentViewer.setText(models.get(num).toString());
            fragmentViewer.assignLink(models.get(num).getSelflink());
            findViewById(R.id.btn_link).setVisibility(View.VISIBLE);
            findViewById(R.id.intent_data_text).setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);

        } else {
            ParcableModel pmodel = new ParcableModel(
                    models.get(num).getTitle(),
                    models.get(num).getAuthors(),
                    models.get(num).getPublisher(),
                    models.get(num).getDescription(),
                    models.get(num).getSelflink()
            );
            Intent explicitIntent = new Intent(MainActivity.this, SecondActivity.class);
            explicitIntent.putExtra(Constants.EXTRA_MODEL, pmodel);
            startActivity(explicitIntent);
        }
    }


    private void showNameToast(String name) {
        Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();
    }
}