package com.example.app.base;

import android.view.View;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.R;

public class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    public void initToolBar (String title) {
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle(title);
    }

    public void initToolBarWithNav (String title) {
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle(title);

        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
