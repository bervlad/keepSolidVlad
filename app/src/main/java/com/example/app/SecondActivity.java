package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.app.base.BaseActivity;

public class SecondActivity extends BaseActivity {
    private String someString;
    private AppCompatButton okBtn;
    private AppCompatButton cancelBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        okBtn = findViewById(R.id.btn_ok);
        cancelBtn = findViewById(R.id.btn_cancel);

        if (getIntent().getExtras() != null) {
            someString = getIntent().getStringExtra(Constants.EXTRA_NAME);
            showNameToast(someString);
        }

        initToolBar(getString(R.string.toolbar_title_second_activity));
        setListeners();
    }

    private void showNameToast(String name) {
        Toast.makeText(SecondActivity.this, name, Toast.LENGTH_LONG).show();
    }

    private void setListeners() {

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
    }


}