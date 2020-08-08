package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.app.App;
import com.example.app.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import com.example.app.database.AppDatabase;
import com.example.app.fragment.screens.chooser.ChooserContract;
import com.example.app.fragment.screens.chooser.ChooserPresenter;
import com.example.app.fragment.screens.chooser.FragmentChooser;
import com.example.app.fragment.screens.viewer.FragmentViewer;
import com.example.app.fragment.screens.viewer.ViewerContract;
import com.example.app.fragment.screens.viewer.ViewerPresenter;
import com.example.app.model.ParcableModel;
import com.example.app.model.VolumeModelItem;
import com.example.app.utils.listeners.ApplicationManager;
import com.example.app.utils.listeners.Constants;
import com.example.app.utils.listeners.ObjectSelectListener;
import com.example.app.utils.listeners.OnHistoryForResultListener;

public class MainActivity extends BaseActivity {

    ApplicationManager applicationManager;
    private FrameLayout fragmentContainerChooser;
    private FrameLayout fragmentContainerViewer;


    ChooserContract.Presenter chooserPresenter;
    ViewerContract.Presenter viewerPresenter;


    public boolean isInLandscapeMode() {
        return inLandscapeMode;
    }

    boolean inLandscapeMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getSupportFragmentManager().getFragments().clear();

        inLandscapeMode = findViewById(R.id.fragment_container_two) != null;
        fragmentContainerChooser = findViewById(R.id.fragment_container_one);
        if (inLandscapeMode) {
            fragmentContainerViewer = findViewById(R.id.fragment_container_two);
        }

        if (((App) getApplication()).getApplicationManager() != null) {
            applicationManager = ((App) getApplication()).getApplicationManager();
        } else {
            applicationManager = new ApplicationManager(this);
            ((App) getApplication()).setApplicationManager(applicationManager);
        }


        chooserPresenter = new ChooserPresenter(applicationManager, getDatabase());

        if (inLandscapeMode) {
            viewerPresenter = new ViewerPresenter();
            FragmentViewer fragmentViewer = new FragmentViewer();

            if (getSupportFragmentManager().findFragmentById(R.id.fragment_container_two) != null) {
                 fragmentViewer = (FragmentViewer) getSupportFragmentManager().findFragmentById(R.id.fragment_container_two);
            }
            fragmentViewer.setPresenter(viewerPresenter);
            getSupportFragmentManager().beginTransaction().replace(fragmentContainerViewer.getId(), fragmentViewer).commit();
        }

        FragmentChooser fragmentChooser = new FragmentChooser();
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container_one) != null) {
            fragmentChooser = (FragmentChooser) getSupportFragmentManager().findFragmentById(R.id.fragment_container_one);
        }
        fragmentChooser.setPresenter(chooserPresenter);
        getSupportFragmentManager().beginTransaction().replace(fragmentContainerChooser.getId(), fragmentChooser).commit();

    }

    public AppDatabase getDatabase() {
        return ((App) getApplication()).getDatabase();
    }

    public ChooserContract.Presenter getChooserPresenter() {
        return chooserPresenter;
    }

    public ViewerContract.Presenter getViewerPresenter() {
        return viewerPresenter;
    }

}