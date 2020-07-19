package com.example.app;

import android.content.Intent;
import android.os.Bundle;

import com.example.app.base.BaseActivity;
import com.example.app.collections.Car;

import java.util.ArrayList;

import fragment.FragmentChooser;
import fragment.FragmentViewer;
import utils.Constants;
import utils.listeners.ObjectSelectListener;

public class MainActivity extends BaseActivity {

//    private AppCompatEditText editText;
//    private AppCompatButton sendBtn;
//    private AppCompatTextView titleText;

    private FragmentChooser fragmentChooser;
    private FragmentViewer fragmentViewer;

    boolean inLandscapeMode;

    private ObjectSelectListener objectSelectListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initToolBar (getString(R.string.toolbar_title_main_activity));

        inLandscapeMode = findViewById(R.id.fragment_two) != null;

        fragmentChooser = (FragmentChooser) getSupportFragmentManager().findFragmentById(R.id.fragment_one);
        if (inLandscapeMode) {
            fragmentViewer = (FragmentViewer) getSupportFragmentManager().findFragmentById(R.id.fragment_two);
        }

        objectSelectListener = new ObjectSelectListener() {

            @Override
            public void btn1Selected() {
                displaySelected(0);
            }

            @Override
            public void btn2Selected() {
                displaySelected(1);
            }

            public void btn3Selected() {
                displaySelected(2);
            }

            public void btn4Selected() {
                displaySelected(3);
            }

            public void btn5Selected() {
                displaySelected(4);
            }

        };

        fragmentChooser.setObjectSelectListener(objectSelectListener);


//        setListeners();
        
    }

    private void displaySelected (int num) {

        if (inLandscapeMode) {
            ArrayList<Car> models = fragmentChooser.getModels();
            fragmentViewer.setText(models.get(num).toString());
        } else {
            ArrayList<Car> models = fragmentChooser.getModels();
            Intent explicitIntent = new Intent(MainActivity.this, SecondActivity.class);
            explicitIntent.putExtra(Constants.EXTRA_CAR, models.get(num));
            startActivity(explicitIntent);
        }
    }


//    private void setListeners() {
//
//        sendBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                handleSendBtn();
//            }
//        });
//    }

//    private void showNameToast(String name) {
//        Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();
//    }
//
//    private void handleSendBtn() {
//        if (TextUtils.isEmpty(editText.getText())) {
//            showNameToast("Empty input");
//            return;
//        }
//
//        Intent intent = new Intent();
//        String textForDisplay =  editText.getText().toString();
//        openSecondActivityForResult(textForDisplay);
//
//    }
//
//    public void openSecondActivityForResult(String text) {
//        Intent explicitIntent = new Intent(MainActivity.this, SecondActivity.class);
//        explicitIntent.putExtra(Constants.EXTRA_NAME, editText.getText().toString());
//        startActivityForResult(explicitIntent, Constants.NAME_REQUEST_CODE);
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == Constants.NAME_REQUEST_CODE) {
//            if (resultCode == -1) {
//                showNameToast("Success!");
//            }
//            else {
//                editText.setText("");
//            }
//        }
//    }


}