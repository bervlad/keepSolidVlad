package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.app.collections.Car;
import com.example.app.utils.Constants;

public class InputActivity extends AppCompatActivity {
    private AppCompatEditText carName;
    private AppCompatEditText carColor;
    private AppCompatEditText maxSpeed;
    private AppCompatEditText capacity;
    private AppCompatButton sendBtn;
    private AppCompatButton cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        carName = findViewById(R.id.car_name);
        carColor = findViewById(R.id.car_color);
        maxSpeed = findViewById(R.id.car_m_speed);
        capacity = findViewById(R.id.car_capacity);

        sendBtn = findViewById(R.id.btn_send);
        cancelBtn = findViewById(R.id.btn_cancel);

        setListeners();

    }

    private void setListeners() {

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSendBtn();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSendBtn();
            }
        });


    }

    private void handleSendBtn() {

        if (TextUtils.isEmpty(carName.getText()) ||
                TextUtils.isEmpty(carColor.getText()) ||
                TextUtils.isEmpty(maxSpeed.getText()) ||
                TextUtils.isEmpty(capacity.getText()))
        {
            Toast.makeText(InputActivity.this, "Empty Text", Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent();
            Car newCar = new Car(carName.getText().toString(),
                    carColor.getText().toString(),
                    Integer.parseInt(maxSpeed.getText().toString()),
                    Integer.parseInt(capacity.getText().toString() ));

            Toast.makeText(InputActivity.this, newCar.getName(), Toast.LENGTH_LONG).show();
            intent.putExtra(Constants.EXTRA_CAR, newCar);

            setResult(RESULT_OK, intent);
            finish();
        }



    }
}