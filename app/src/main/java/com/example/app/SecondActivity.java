package com.example.app;


import android.content.Intent;
import android.os.Bundle;


import com.example.app.base.BaseActivity;
import com.example.app.fragment.screens.viewer.FragmentViewer;
import com.example.app.model.ParcableModel;
import com.example.app.utils.listeners.Constants;
import com.example.app.utils.listeners.OnHistoryForResultListener;


public class SecondActivity extends BaseActivity {
    private ParcableModel pmodel;

    private FragmentViewer fragmentViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initToolBarWithNav(getString(R.string.toolbar_title_second_activity));

        OnHistoryForResultListener listener = new OnHistoryForResultListener() {
            @Override
            public void historyIconSelected() {
                Intent explicitIntent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(explicitIntent);
            }
        };

        super.setListener(listener);

        if (getIntent().getExtras() != null) {
            pmodel = getIntent().getParcelableExtra(Constants.EXTRA_MODEL);
        }

        String inputText = pmodel.toString();

        fragmentViewer = (FragmentViewer) getSupportFragmentManager().findFragmentById(R.id.fragment_two);
        fragmentViewer.setText(inputText);
        fragmentViewer.assignLink(pmodel.getSelflink());
        fragmentViewer.prepareForSearchItemHorizontal();

    }


}