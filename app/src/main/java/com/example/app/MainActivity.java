package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app.app.App;
import com.example.app.base.BaseActivity;

import java.util.ArrayList;

import com.example.app.database.AppDatabase;
import com.example.app.fragment.screens.chooser.ChooserContract;
import com.example.app.fragment.screens.chooser.FragmentChooser;
import com.example.app.fragment.screens.viewer.FragmentViewer;
import com.example.app.model.ParcableModel;
import com.example.app.model.VolumeModelItem;
import com.example.app.utils.listeners.ApplicationManager;
import com.example.app.utils.listeners.Constants;
import com.example.app.utils.listeners.ObjectSelectListener;
import com.example.app.utils.listeners.OnHistoryForResultListener;

public class MainActivity extends BaseActivity {

    private FrameLayout fragmentContainerChooser;
    private FrameLayout fragmentContainerViewer;

    private FragmentChooser fragmentChooser;
    private FragmentViewer fragmentViewer;


    RecyclerView recyclerView;

    boolean inLandscapeMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        fragmentContainerChooser = findViewById(R.id.fragment_container);


        initToolBarWithNav (getString(R.string.toolbar_title_main_activity));

        ChooserContract.Presenter presenter;
        ApplicationManager applicationSettingsManager = new ApplicationManager(MainActivity.this);

        inLandscapeMode = findViewById(R.id.fragment_two) != null;

        fragmentChooser = (FragmentChooser) getSupportFragmentManager().findFragmentById(R.id.fragment_one);
        //fragmentChooser.setDatabase(getDatabase());

        if (inLandscapeMode) {
            findViewById(R.id.btn_link).setVisibility(View.GONE);
            fragmentViewer = (FragmentViewer) getSupportFragmentManager().findFragmentById(R.id.fragment_two);
        }

        OnHistoryForResultListener onHistoryForResultListener = new OnHistoryForResultListener() {

            @Override
            public void historyIconSelected() {
                Intent explicitIntent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(explicitIntent);
            }
        };

        super.setListener(onHistoryForResultListener);


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

        if (getIntent().getExtras() != null) {
            String title = getIntent().getStringExtra(Constants.EXTRA_TITLE);
            fragmentChooser.performSearch(title);
        }

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


    public AppDatabase getDatabase() {
        return ((App)getApplication()).getDatabase();
    }

    private void showNameToast(String name) {
        Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();
    }


}