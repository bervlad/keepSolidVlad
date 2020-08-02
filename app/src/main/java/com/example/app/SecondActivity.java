package com.example.app;


import android.os.Bundle;


import com.example.app.base.BaseActivity;
import com.example.app.fragment.FragmentViewer;
import com.example.app.model.ParcableModel;
import com.example.app.utils.listeners.Constants;


public class SecondActivity extends BaseActivity {
    private ParcableModel pmodel;

    private FragmentViewer fragmentViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initToolBarWithNav(getString(R.string.toolbar_title_second_activity));


        if (getIntent().getExtras() != null) {
            pmodel = getIntent().getParcelableExtra(Constants.EXTRA_MODEL);
        }

        String inputText = pmodel.toString() ;


        fragmentViewer = (FragmentViewer) getSupportFragmentManager().findFragmentById(R.id.fragment_two);
        fragmentViewer.setText(inputText);
        fragmentViewer.assignLink(pmodel.getSelflink());

    }




}