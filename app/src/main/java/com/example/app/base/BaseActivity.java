package com.example.app.base;

import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.R;

public class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    public void initToolBar (String title) {
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle(title);
    }
}
