package com.example.app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppCompatEditText editText;
    private AppCompatButton sendBtn;
    private AppCompatTextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        editText = findViewById(R.id.et_name_input);
        sendBtn = findViewById(R.id.btn_send);
        titleText = findViewById(R.id.tv_input_activity);

        setListeners();
        
    }



    private void setListeners() {

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSendBtn();
            }
        });
    }

    private void showNameToast(String name) {
        Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();
    }

    private void handleSendBtn() {
        if (TextUtils.isEmpty(editText.getText())) {
            showNameToast("Empty input");
            return;
        }

        Intent intent = new Intent();
        String textForDisplay =  editText.getText().toString();
        openSecondActivityForResult(textForDisplay);
//        setResult(RESULT_OK, intent);
//        finish();
    }

    public void openSecondActivityForResult(String text) {
        Intent explicitIntent = new Intent(MainActivity.this, SecondActivity.class);
        explicitIntent.putExtra(Constants.EXTRA_NAME, editText.getText().toString());
        startActivityForResult(explicitIntent, Constants.NAME_REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.NAME_REQUEST_CODE) {
            if (resultCode == -1) {
                showNameToast("Success!");
            }
            else {
                editText.setText("");
            }
        }
    }


}