package com.example.app.testfmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.app.R;
import com.example.app.base.BaseActivity;
import com.example.app.testfmanager.fragments.FirstFragment;
import com.example.app.testfmanager.fragments.SecondFragment;

public class FragmentActivity extends BaseActivity {

    private final static String LOG_TAG = FragmentActivity.class.getSimpleName();

    private AppCompatButton btnAdd;
    private AppCompatButton btnRemove;
    private AppCompatButton btnReplace;
    private SwitchCompat backstackSwitch;

    private FirstFragment fragmentOne;
    private SecondFragment fragmentTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        initViews();

        fragmentOne = FirstFragment.newInstance("Vlad", "Ber");
        fragmentTwo = new SecondFragment();

        setListeners();
        initToolBar(getString(R.string.app_name));

    }

    private void initViews() {
        btnAdd = findViewById(R.id.btn_add);
        btnRemove = findViewById(R.id.btn_remove);
        btnReplace = findViewById(R.id.btn_replace);
        backstackSwitch = findViewById(R.id.backstack_switch);

    }

    private void setListeners() {

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragmentA =  getSupportFragmentManager().findFragmentByTag("frag_one");
                if (fragmentA==null) {
                    addFragment(fragmentOne);
                }
            }
        });

        btnReplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(fragmentTwo);
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment(fragmentOne);
            }
        });
    }

    private void addFragment(Fragment fragment) {

            Log.d(LOG_TAG,"Hello");
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, fragment, "frag_one");
            if (backstackSwitch.isChecked()) {
                transaction.addToBackStack(null);
            }
            transaction.commit();


    }

    private void removeFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.remove(fragment);
        if (backstackSwitch.isChecked()) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        if (backstackSwitch.isChecked()) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

}