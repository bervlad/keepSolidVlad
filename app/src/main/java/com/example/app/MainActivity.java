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


//    private FragmentChooser fragmentChooser;
//    private FragmentViewer fragmentViewer;


    RecyclerView recyclerView;

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
            fragmentContainerViewer=findViewById(R.id.fragment_container_two);
        }

       // initToolBarWithNav (getString(R.string.toolbar_title_main_activity));


       // ApplicationManager applicationManager = new ApplicationManager(MainActivity.this);

       if ( ((App)getApplication()).getApplicationManager()!=null) {
           applicationManager=((App)getApplication()).getApplicationManager();
       } else {
           applicationManager = new ApplicationManager(this);
           ((App) getApplication()).setApplicationManager(applicationManager);
       }
        //setManager(applicationManager);

        chooserPresenter = new ChooserPresenter(applicationManager, getDatabase());

       // chooserPresenter = new ChooserPresenter(((App)getApplication()).getApplicationManager(), getDatabase());

            FragmentChooser fragmentChooser = new FragmentChooser();


            if (getSupportFragmentManager().findFragmentById(R.id.fragment_container_one)!=null) {
                fragmentChooser = (FragmentChooser) getSupportFragmentManager().findFragmentById(R.id.fragment_container_one);
            }

            fragmentChooser.setPresenter(chooserPresenter);
            getSupportFragmentManager().beginTransaction().replace(fragmentContainerChooser.getId(), fragmentChooser).commit();

            if (inLandscapeMode) {
                viewerPresenter = new ViewerPresenter();
                FragmentViewer fragmentViewer = new FragmentViewer();

                if (getSupportFragmentManager().findFragmentById(R.id.fragment_container_two)!=null) {
                    fragmentViewer= (FragmentViewer) getSupportFragmentManager().findFragmentById(R.id.fragment_container_two);
                }
                fragmentViewer.setPresenter(viewerPresenter);
                getSupportFragmentManager().beginTransaction().replace(fragmentContainerViewer.getId(), fragmentViewer).commit();
            }



 //       fragmentChooser = (FragmentChooser) getSupportFragmentManager().findFragmentById(R.id.fragment_one);
        //fragmentChooser.setDatabase(getDatabase());

//        if (inLandscapeMode) {
//            findViewById(R.id.btn_link).setVisibility(View.GONE);
//            fragmentViewer = (FragmentViewer) getSupportFragmentManager().findFragmentById(R.id.fragment_two);
//        }

//        OnHistoryForResultListener onHistoryForResultListener = new OnHistoryForResultListener() {
//
//            @Override
//            public void historyIconSelected() {
//                Intent explicitIntent = new Intent(MainActivity.this, ThirdActivity.class);
//                startActivity(explicitIntent);
//            }
//        };
//        super.setListener(onHistoryForResultListener);


//        ObjectSelectListener objectSelectListener = new ObjectSelectListener() {
//            @Override
//            public void selected(int num) {
//                displaySelected (num);
//            }
//
////            public void buttonSelected () {
////                if (inLandscapeMode) {
////                    fragmentViewer = (FragmentViewer) getSupportFragmentManager().findFragmentById(R.id.fragment_two);
////                    fragmentViewer.clearScreen();}
////            }
//        };
//        fragmentChooser.setObjectSelectListener(objectSelectListener);

//        if (getIntent().getExtras() != null) {
//            String title = getIntent().getStringExtra(Constants.EXTRA_TITLE);
//            chooserPresenter.searchVolumes(title);
////            fragmentChooser.performSearch(title);
//        }

    }


//    public void displaySelected (int num) {
//        ArrayList<VolumeModelItem> models = fragmentChooser.getModels();
//
//        if (inLandscapeMode) {
//            fragmentViewer.setText(models.get(num).toString());
//            fragmentViewer.assignLink(models.get(num).getSelflink());
//            findViewById(R.id.btn_link).setVisibility(View.VISIBLE);
//            findViewById(R.id.intent_data_text).setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
//
//        } else {
//            ParcableModel pmodel = new ParcableModel(
//                    models.get(num).getTitle(),
//                    models.get(num).getAuthors(),
//                    models.get(num).getPublisher(),
//                    models.get(num).getDescription(),
//                    models.get(num).getSelflink()
//            );
//            Intent explicitIntent = new Intent(MainActivity.this, SecondActivity.class);
//            explicitIntent.putExtra(Constants.EXTRA_MODEL, pmodel);
//            startActivity(explicitIntent);
//        }
//    }


    public AppDatabase getDatabase() {
        return ((App)getApplication()).getDatabase();
    }

    public ChooserContract.Presenter getChooserPresenter() {
        return chooserPresenter;
    }

    public ViewerContract.Presenter getViewerPresenter() {
        return viewerPresenter;
    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        List<Fragment> fragments = getSupportFragmentManager().getFragments();
//        for (Fragment fragment: fragments) {
//            getSupportFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
//        }
//    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        List<Fragment> fragments = getSupportFragmentManager().getFragments();
//        for (Fragment fragment: fragments) {
//            getSupportFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
//        }
//        super.onSaveInstanceState(outState);
//    }

    //    private void showNameToast(String name) {
//        Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();
//    }


}